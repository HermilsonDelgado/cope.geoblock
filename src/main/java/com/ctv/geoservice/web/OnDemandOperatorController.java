package com.ctv.geoservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctv.geoservice.geolocalize.GeolocalizationService;

/**
 * Controller for on-demand operations
 */
@Controller
@RequestMapping("/ondemand")
public class OnDemandOperatorController extends GeoServiceAbstractController {

  private static final Logger log = LoggerFactory.getLogger(OnDemandOperatorController.class);

  @Autowired
  GeolocalizationService geolocalizationService;

  /**
   * Método para re-generar las reglas de geolocalización de todos los deliveries a sus respectivas
   * CDNs.
   *
   * @return
   * @throws Exception
   */
  @RequestMapping("/sendGeoBlockedAssets")
  @ResponseBody // no retornamos template, solo json autoexplicativo
  private String regenerateDeals() throws Exception {
    geolocalizationService.refreshGeolocalizationRules();
    return "Everything went OK";
  }

  /**
   * Retorna la lista de reglas de geolocalización de todos los deliveries.
   *
   * @return la lista de reglas de geolocalización de todos los deliveries.
   * @throws Exception
   */
  @RequestMapping(value = "/getGeoBlockEntries", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  private Object getGeolocalizationRules() throws Exception {
    return geolocalizationService.getGeolocalizationRules();
  }

}
