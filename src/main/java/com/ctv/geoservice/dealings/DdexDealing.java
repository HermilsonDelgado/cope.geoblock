package com.ctv.geoservice.dealings;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ctv.deliverty.common.ICategoryDelegate;
import com.ctv.deliverty.common.IContentDelegate;
import com.ctv.deliverty.common.exception.CMSException;
import com.ctv.deliverty.common.utils.ContentCriteria;
import com.ctv.deliverty.common.utils.Criteria;
import com.ctv.deliverty.common.utils.IRelationNode;
import com.ctv.deliverty.common.utils.Node;
import com.ctv.deliverty.common.vo.CategoryVO;
import com.ctv.deliverty.common.vo.ContentRelationVO;
import com.ctv.deliverty.common.vo.ContentVO;
import com.ctv.deliverty.common.vo.PageVO;
import com.ctv.geoservice.geolocalize.GeoserviceException;
import com.ctv.geoservice.geolocalize.GeoserviceProjectConfig;
import com.ctv.publisher.model.releasedeal.Deal;
import com.ctv.publisher.model.releasedeal.ReleaseDeal;
import com.ctv.publisher.model.releasedeal.ValidityPeriod;

@Component("ddex")
public class DdexDealing implements DealingsService {

  private static final Logger log = LoggerFactory.getLogger(DdexDealing.class);

  @Autowired
  private GeoserviceProjectConfig geoserviceProjectConfig;

  @Autowired
  private IContentDelegate contentDelegate;

  @Autowired
  private ICategoryDelegate categoryDelegate;

  @Value("${application.deals.ddex.field}")
  private String ddexTypologyField;

  /**
   * Locale the ASSETs are stored in.
   */
  private String locale;

  /**
   * Returns a map...
   *
   * @param deliveryUid
   * @param commercialModelType
   * @param useType
   * @return
   * @throws GeoserviceException
   */
  @Override
  public Map<String, List<String>> getGeolocalizationRules(String deliveryUid,
      String commercialModelType, String useType) throws GeoserviceException {

    Map<String, List<String>> geolocalizationRules = new HashMap<>();

    // Establecemos los criterios para la recuperación de los contenidos: ASSETs con destino de publicación <code>deliveryUid</code>.
    Criteria criteria = new Criteria();
    criteria.addState("PUB");
    criteria.addCategory("DELIVERY", getCategoryId(deliveryUid));

    criteria.addField("AS_GEOBLOCK", "true");
    criteria.addCategory("AS_CATTYPE", getCategoryId("AS_ASD_VID"));

    ContentCriteria ccriteria = new ContentCriteria(true, true, true, false, false);

    // Drilldown para obtener los ASSET_DATA_VIDEOS
    IRelationNode assetNode = new Node("ASSET", null, null, ccriteria);
    Criteria criteriaRelation = new Criteria();
    ContentCriteria ccriteriaRelation = new ContentCriteria(true, true, true, false);
    IRelationNode assetDataVideoNode = new Node("ASSET_DATA_VIDEO", "AS_ASD_VID", criteriaRelation,
        ccriteriaRelation);
    assetNode.addChild(assetDataVideoNode);

    int index = 0;
    PageVO page = getGeolocalizableAssets(index, criteria, ccriteria, assetNode);
    log.info("Se han recuperado {} ASSETs que hay que geolocalizar.", page.getTotalItems());
    page.getContents().stream().forEach(c -> log.info("asset-> " + c));
    while (index < page.getTotalItems()) {

      log.info("Pedimos los elementos con índice: {}", index);
      List<ContentVO> pageContents = page.getContents();
      for (ContentVO asset : pageContents) {
        try {
          // Objeto (lista) en el que guardaremos la lista de países para los que se permite o denega el acceso al recurso
          List<String> territoryCodePermissions = new LinkedList<String>();

          // Obtenemos la lista de resources que tiene el asset (rutas de los ficheros)
          List<Object> asdFiles = ((List<ContentRelationVO>) asset.getContentRelations()
              .get("AS_ASD_VID")).stream()
              .map(ContentRelationVO::getToContent)
              .map(relatedContent -> relatedContent.getFields().get("ASD_FILE"))
              .collect(Collectors.toList());
          log.debug("Asset '{}' has the following resources: {}", asset.toString(),
              asdFiles.stream().map(o -> ((String) o)).collect(Collectors.joining(", ")));

          // Añadimos las entradas de los recursos y sus permisos según territorio al objeto de retorno
          asdFiles.stream().map(o -> (String) o)
              .forEach(
                  asdFile -> geolocalizationRules.put(asdFile, Arrays.asList("!ES", "!CO")));

        } catch (Exception e) {
          log.warn(
              "No se ha podido calcular las reglas de geolocalización del contenido '{}' por el siguiente motivo: '{}' Omitimos este y pasamos al siguiente contenido.",
              asset == null ? "null" : asset.toString(), e.getMessage());
        }
      }
      // incrementamos el índice para obtener la siguiente página
      index += pageContents.size();
      page = getGeolocalizableAssets(index, criteria, ccriteria, assetNode);
    }
    return geolocalizationRules;
  }

  /**
   * Returns a {@link PageVO} containing all the ASSETs that should be geolocalized.
   *
   * @param index              índice del elemento a partir del cual se quieren obtener los
   *                           siguientes contenidos de la página.
   * @param criteria
   * @param ccriteria
   * @param assetDataVideoNode
   * @return a {@link PageVO} containing all the ASSETs that should be geolocalized.
   */
  private PageVO getGeolocalizableAssets(int index, Criteria criteria, ContentCriteria ccriteria,
      IRelationNode assetDataVideoNode) throws GeoserviceException {
    try {
      PageVO page = contentDelegate.getContents("ASSET", index, 10, null, locale, criteria,
          ccriteria, assetDataVideoNode);
      return page;
    } catch (CMSException e) {
      throw new GeoserviceException(
          GeoserviceException.GeoserviceExceptionType.ERROR_RETRIEVING_CONTENTS_FROM_CMS,
          "Se ha producido un error al intentar obtener los assets del CMS.", e);
    }
  }

  /**
   * Returns the Id of a category in the CMS.
   *
   * @param categoryUid of the category whose ID is returned.
   * @return the Id of a category in the CMS.
   */
  private Number getCategoryId(String categoryUid) {
    CategoryVO category = categoryDelegate.getCategory(categoryUid,
        geoserviceProjectConfig.getLocale());
    return category.getId();
  }

  /**
   * Construye un {@link ReleaseDeal} a partir del XML en formato {@link String} correspondiente a
   * un contenido DDEX.
   *
   * @param ddexStringXml String que contiene los deals en formato DDEX que se quieren convertir en
   *                      {@link ReleaseDeal}.
   * @return {@link ReleaseDeal} object containing all the information in
   * <code>ddexStringXml</code>.
   * @throws JAXBException if the <code>ddexStringXml</code> is not in a valid DDEX format.
   */
  private ReleaseDeal readListDealXml(String ddexStringXml) throws JAXBException {
    StringReader reader = new StringReader(ddexStringXml);
    JAXBContext jaxbContext = JAXBContext.newInstance(ReleaseDeal.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    return (ReleaseDeal) jaxbUnmarshaller.unmarshal(reader);
  }

  /**
   * Retorna <code>true</code> si el <code>releaseDeal</code> otorga permisos para la reproducción
   * del recurso dentro del territorio indicado por <code>territoryCode</code>, segun el modelo
   * comercial indicado por <code>commercialModelType</code> y según el tipo de uso indicado por
   * <code>useType</code>.
   *
   * @param releaseDeal         Objeto que contiene la información de los deals de un recurso sobre
   *                            el recurso.
   * @param territoryCode       código del territorio para el que queremos saber si el releaseDeal
   *                            otorga permisos sobre el recurso.
   * @param commercialModelType tipo de modelo comercial para el que queremos saber si el
   *                            releaseDeal otorga permisos sobre el recurso.
   * @param useType             tipo de uso para el que queremos saber si el releaseDeal otorga
   *                            permisos sobre el recurso.
   * @return <code>true</code> si el <code>releaseDeal</code> otorga permisos para la reproducción
   * del recurso dentro del territorio indicado por <code>territoryCode</code>, segun el modelo
   * comercial indicado por <code>commercialModelType</code> y según el tipo de uso indicado por
   * <code>useType</code>; <code>false</code> en cualquier otro caso.
   */
  private boolean hasDealRights(ReleaseDeal releaseDeal, String territoryCode,
      String commercialModelType,
      String useType) {
    if (releaseDeal != null) {
      Calendar now = Calendar.getInstance();
      List<Deal> deals = releaseDeal.getDeal().stream()
          // filtramos los deal con los atributos que necesitamos a
          // null
          .filter(d -> d.getDealTerms() != null)
          .filter(d -> d.getDealTerms().getTerritoryCode() != null)
          .filter(d -> d.getDealTerms().getCommercialModelType() != null)
          .filter(d -> d.getDealTerms().getUsage() != null
              && d.getDealTerms().getUsage().getUseType() != null)
          .filter(d -> d.getDealTerms().getValidityPeriod() != null
              && getStartDate(d.getDealTerms().getValidityPeriod()) != null)
//					// filtramos las condicines
          .filter(d -> territoryCode.equalsIgnoreCase(d.getDealTerms().getTerritoryCode()))
          .filter(
              d -> commercialModelType.equalsIgnoreCase(d.getDealTerms().getCommercialModelType()))
          .filter(d -> d.getDealTerms().getUsage().getUseType().contains(useType))
          .filter(d -> now
              .compareTo(getStartDate(d.getDealTerms().getValidityPeriod()).toGregorianCalendar())
              >= 0)
          // controlamos que EndDate no sea a null si es null la
          .filter(d -> (getEndDate(d.getDealTerms().getValidityPeriod()) != null && now
              .compareTo(getEndDate(d.getDealTerms().getValidityPeriod()).toGregorianCalendar())
              <= 0)
              || getEndDate(d.getDealTerms().getValidityPeriod()) == null)
          .collect(Collectors.toList());
      return !deals.isEmpty();
    }
    return false;
  }

  private XMLGregorianCalendar getStartDate(ValidityPeriod vp) {
    if (vp.getStartDate() != null) {
      return vp.getStartDate();
    } else if (vp.getStartDateTime() != null) {
      return vp.getStartDateTime();
    } else {
      return null;
    }
  }

  private XMLGregorianCalendar getEndDate(ValidityPeriod vp) {
    if (vp.getEndDate() != null) {
      return vp.getEndDate();
    } else if (vp.getEndDateTime() != null) {
      return vp.getEndDateTime();
    } else {
      return null;
    }
  }

}