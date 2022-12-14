/*
 *  Copyright (c) 2022 Agile Content S.A. All rights reserved.
 */

package com.ctv.geoservice.cdnprovider.fastly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.ctv.geoservice.geolocalize.CdnResponseException;
import com.ctv.geoservice.geolocalize.GeoserviceException;

@Service
public class FastlyApiService {

  private static final Logger log = LoggerFactory.getLogger(FastlyApiService.class);

  @Value("${application.fastly.api.url}")
  private String urlEntry;

  @Value("${application.fastly.api.token}")
  private String apiToken;

  @Value("${application.fastly.api.serviceid}")
  private String serviceId;

  @Value("${application.fastly.api.dictionaryid}")
  private String dictionaryId;

  /**
   * This method returns a json formatted String that represents the set of rules specified in
   * <code>newRules</code> in the valid Fastly upsert format
   * (https://docs.fastly.com/api/config#dictionary_item_dc826ce1255a7c42bc48eb204eed8f7f)
   *
   * @param newRules Map whose key is the path of a resource and whose value is a list of country
   *                 codes for which its visualization is permitted or prohibited.
   * @return json formatted String that represents the set of rules specified in
   * <code>newRules</code> in the valid Fastly upsert format
   * (https://docs.fastly.com/api/config#dictionary_item_dc826ce1255a7c42bc48eb204eed8f7f)
   * @throws JSONException
   */
  protected static String formatRulesForBatchUpdate(Map<String, List<String>> newRules)
      throws JSONException {
    // Root of the json object that will be returned in String format.
    JSONObject json = new JSONObject();

    // value for the "items"
    JSONArray items = new JSONArray();

    newRules.entrySet().stream().forEach(entry -> {
      JSONObject item = new JSONObject();
      try {
        item.put("op", "upsert");
        String key = entry.getKey().replace("/deliverty/main/", "/");
        //Guardamos en el diccionario la key sin el /resources
        key = key.replace("resources/", "");
        item.put("item_key", key);
        item.put("item_value", entry.getValue().stream().collect(Collectors.joining(",")));
      } catch (JSONException e) {
        log.error(e.getMessage());
        e.printStackTrace();
      }

      items.put(item);
    });

    json.put("items", items);

    log.info("Se ha generado el siguiente JSON para hacer update a FASTLY:");
    log.info(json.toString());
    return json.toString();
  }

  /**
   * Update DictionaryItem inbatch for given service, dictionary ID and key/value pairs for items.
   * (https://docs.fastly.com/api/config#dictionary_item_dc826ce1255a7c42bc48eb204eed8f7f)
   *
   * @param newRules
   * @throws GeoserviceException
   * @throws JSONException
   */
  public void performBatchUpdate(Map<String, List<String>> newRules)
      throws GeoserviceException, JSONException {
    String batchUpdateUrl =
        urlEntry + "service/" + serviceId + "/dictionary/" + dictionaryId + "/items";
    try {
      URL url = new URL(batchUpdateUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/json;");
      connection.setRequestProperty("Accept", "application/json");
      // No podemos usar connection.setRequestMethod
      connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
      connection.setRequestMethod("POST");

      connection.setRequestProperty("Fastly-Key", apiToken);

      OutputStream os = connection.getOutputStream();
      String json = formatRulesForBatchUpdate(newRules);
      os.write(json.getBytes("UTF-8"));
      os.close();

      StringBuilder sb = new StringBuilder();
      // Get the response code
      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
          sb.append(line + "\n");
        }
        br.close();
        log.info("Output message sent by FASTLY in response to the BATCH UPDATE:");
        log.info(sb.toString());
      } else {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(connection.getErrorStream(), "UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
          sb.append(line + "\n");
        }
        br.close();
        log.info("Output message sent by FASTLY in response to the BATCH UPDATE:");
        log.info(sb.toString());

        String errorMessage = null;
        try {
          JSONObject responseJson = new JSONObject(sb.toString());
          errorMessage = responseJson.getString("msg") + ": " + responseJson.getString("detail");
        } catch (JSONException e) {
          errorMessage = sb.toString();
        }
        throw new GeoserviceException(
            GeoserviceException.GeoserviceExceptionType.CDN_HTTP_RESPONSE_ERROR,
            "Se ha enviado el batch con toda la informaci??n a FASTLY pero la CDN ha respondido la peticion http con un status code '"
                + responseCode + "' y el siguiente mensaje : '" + errorMessage + "'");
      }
    } catch (MalformedURLException e) {
      log.error(
          "Se ha producido un error al generar la URL para atacar a FASTLY, muy probablemente debido a que faltan algunos par??metros de configuraci??n (o estos son inv??lidos). La URL que se ha generado y que est?? fallando es: '{}', mensaje del error: '{}'",
          batchUpdateUrl, e.getMessage(), e);
      throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.CONFIGURATION_ERROR,
          "Se ha producido un error a la hora de generar la URL a la que tenemos que acceder de FASTLY. La URL es: '"
              + batchUpdateUrl + "'",
          e);
    } catch (IOException e) {
      throw new GeoserviceException(
          GeoserviceException.GeoserviceExceptionType.COULD_NOT_CONNECT_TO_CDN,
          "No se ha podido conectar con FASTLY. Mensaje del error: '" + e.getMessage() + "'", e);
    }
  }

  /**
   * M??todo para obtener un mapa con todas las reglas de geolocalizaci??n que estan siendo usadas en
   * Fastly.
   *
   * @return
   * @throws GeoserviceException
   * @throws JSONException
   */
  public Map<String, List<String>> getDictionaryItems() throws GeoserviceException, JSONException {
    Map<String, List<String>> currentRules = new HashMap<>();
    String getDictionaryItemsUrl =
        urlEntry + "service/" + serviceId + "/dictionary/" + dictionaryId + "/items";
    HttpURLConnection fastlyHttpConnection = openFastlyHttpConnection(HttpMethod.GET,
        getDictionaryItemsUrl);
    String fastlyResponse = getFastlyResponse(fastlyHttpConnection);
    JSONArray response = new JSONArray(fastlyResponse);

    for (int i = 0; i < response.length(); i++) {
      JSONObject item = (JSONObject) response.getJSONObject(i);
      currentRules.put(item.getString("item_key"),
          Arrays.asList(item.getString("item_value").split(",")));
    }

    return currentRules;
  }

  /**
   * Este m??todo retorna un objeto de tipo {@link HttpURLConnection} con los headers y par??metros
   * b??sicos necesarios para realizar una petici??n HTTP a la api de FASTLY. El siguiente paso ser??a
   * llamar la funci??n {@link FastlyApiService#getDictionaryItems()} con el objeto retornado por
   * ??ste m??todo.
   *
   * @param method    {@link HttpMethod} que se desea para la petici??n HTTP.
   * @param urlString URL (de fastly) a la que se har?? la petici??n HTTP.
   * @return {@link HttpURLConnection} con los headers y par??metros b??sicos necesarios para realizar
   * una petici??n HTTP a la api de FASTLY.
   * @throws GeoserviceException
   */
  private HttpURLConnection openFastlyHttpConnection(HttpMethod method, String urlString)
      throws GeoserviceException {
    try {
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      switch (method) {
        case GET:
          connection.setRequestMethod("GET");
          break;
        case PATCH:
          // No podemos usar connection.setRequestMethod() porque el
          // m??todo no est?? soportado
          connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
          connection.setRequestMethod("POST");

          break;
      }
      // en cualquier caso de peticion HTTP, siempre esperamos un mensaje
      // de retorno (output)
      connection.setDoInput(true);

      // siempre es necesario enviar el token de fastly
      connection.setRequestProperty("Fastly-Key", apiToken);

      return connection;
    } catch (MalformedURLException e) {
      log.error(
          "Se ha producido un error al generar la URL para atacar a FASTLY, muy probablemente debido a que faltan algunos par??metros de configuraci??n (o estos son inv??lidos). La URL que se ha generado y que est?? fallando es: '{}', mensaje del error: '{}'",
          urlString, e.getMessage(), e);
      throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.CONFIGURATION_ERROR,
          "Se ha producido un error a la hora de generar la URL a la que tenemos que acceder de FASTLY. La URL es: '"
              + urlString + "'",
          e);
    } catch (ProtocolException e) {
      log.error(
          "Se ha producido un error al intentar crear una peticion HTTP con el protocolo '{}'. Nunca se deber??a llegar aqu??, revise el c??digo y aseg??rese que el m??todo especificado es un m??todo HTTP v??lido para HttpUrlConnection.",
          method.toString());
      throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.DEVELOPMENT_ERROR,
          "Se ha producido un error al intentar crear una peticion HTTP con el protocolo '"
              + method.toString()
              + "'. Nunca se deber??a llegar aqu??, revise el c??digo y aseg??rese que el m??todo especificado es un m??todo HTTP v??lido para HttpUrlConnection.",
          e);
    } catch (IOException e) {
      throw new GeoserviceException(
          GeoserviceException.GeoserviceExceptionType.COULD_NOT_CONNECT_TO_CDN,
          "No se ha podido conectar con FASTLY. Mensaje del error: '" + e.getMessage() + "'", e);
    }
  }

  /**
   * Realiza la petici??n HTTP a la api de FASTLY y retorna la respuesta como String.
   *
   * @param connection {@link HttpURLConnection} correspondiente a una petici??n hecha a alguna URL
   *                   de Fastly (por ejemplo, usando el m??todo
   *                   {@link FastlyApiService#openFastlyHttpConnection(HttpMethod, String)}.
   * @return {@link String} con la respuesta proporcionada por FASTLY.
   * @throws GeoserviceException
   */
  private String getFastlyResponse(HttpURLConnection connection) throws GeoserviceException {
    try {
      // Actually commit the request by calling getResponseCode()
      // (https://stackoverflow.com/questions/2151359/java-httpurlconnection-doesnt-connect-when-i-call-connect)
      int responseCode = connection.getResponseCode();

      // Obtenemos la respuesta de fastly
      StringBuilder sb = new StringBuilder();
      BufferedReader br = new BufferedReader(
          new InputStreamReader(responseCode == HttpURLConnection.HTTP_OK
              ? connection.getInputStream() : connection.getErrorStream()));
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line + "\n");
      }
      br.close();
      log.info("Output message sent by FASTLY:");
      log.info(sb.toString());

      // Analizamos la respuesta y devolvemos lo que interesa
      if (responseCode != HttpURLConnection.HTTP_OK) {
        String errorMessage = null;
        try {
          JSONObject responseJson = new JSONObject(sb.toString());
          errorMessage = responseJson.getString("msg") + ": " + responseJson.getString("detail");
        } catch (JSONException e) {
          errorMessage = sb.toString();
        }
        throw new CdnResponseException(
            GeoserviceException.GeoserviceExceptionType.CDN_HTTP_RESPONSE_ERROR,
            "Se ha enviado una petici??n a FASTLY pero la CDN ha respondido con un status code '"
                + responseCode + "' y el siguiente mensaje : '" + errorMessage + "'",
            responseCode);
      }
      return sb.toString();
    } catch (IOException e) {
      throw new GeoserviceException(
          GeoserviceException.GeoserviceExceptionType.COULD_NOT_CONNECT_TO_CDN,
          "No se ha podido conectar con FASTLY. Mensaje del error: '" + e.getMessage() + "'", e);
    }
  }

  /**
   * Retrieve a single DictionaryItem from a dictionary
   *
   * @param resource identifier of the resource
   * @return the value corresponding to the resource in the dictionary
   * @throws JSONException
   */
  public JSONObject getSingleDictionaryItem(String resource)
      throws GeoserviceException, JSONException {
    String url =
        urlEntry + "service/" + serviceId + "/dictionary/" + dictionaryId + "/item/" + resource;
    HttpURLConnection fastlyHttpConnection = openFastlyHttpConnection(HttpMethod.GET, url);
    String fastlyResponse = getFastlyResponse(fastlyHttpConnection);
    JSONObject response = new JSONObject(fastlyResponse);
    return response;
  }
}
