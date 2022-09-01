package com.ctv.geoservice.geolocalize;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctv.geoservice.cdnprovider.CdnProvider;
import com.ctv.geoservice.cdnprovider.CdnProviderManager;
import com.ctv.geoservice.dealings.DealingsService;
import com.ctv.geoservice.dealings.DealsManager;

@Service
public class GeolocalizationService {

  private static final Logger log = LoggerFactory.getLogger(GeolocalizationService.class);

  @Autowired
  private GeoserviceProjectConfig applicationProperties;

  @Autowired
  private GeoserviceProjectConfig geoserviceConfiguration;

  @Autowired
  private CdnProviderManager cdnProviderManager;

  @Autowired
  private DealsManager dealsManager;

  /**
   * This method performs a fully rebuild of the geolocalize rules set on the CDN provider. This
   * means:
   * <ol>
   *     <li>Clean all the geolocalize rules from the CDN provider</li>
   *     <li>Generate a new fresh full set of geolocalize rules for all of the existing assets on the CMS</li>
   *     <li>Upload the new set of rules to the CDN provider</li>
   * </ol>
   *
   * @throws Exception
   */
  public void refreshGeolocalizationRules() throws Exception {
    log.info("Performing a full refresh of all the deals from the following deliveries: {}",
        geoserviceConfiguration.getDeliveries().stream()
            .map(GeoserviceProjectConfig.Deliveries::getDelivery)
            .collect(Collectors.joining(", ")));

    for (GeoserviceProjectConfig.Deliveries delivery : geoserviceConfiguration.getDeliveries()) {
      log.info("Delivery '{}'({}): {} - {}", delivery.getName(), delivery.getDelivery(),
          delivery.getCdnProvider(), delivery.getDeals());

      CdnProvider cdnProvider = cdnProviderManager.getCdnProvider(delivery.getCdnProvider());
      DealingsService dealing = dealsManager.getDealingService(delivery.getDeals());

      // Generating the new list of country code permissions for each resource in the CDN
      Map<String, List<String>> updatedGeolocalizationRules = dealing.getGeolocalizationRules(
          delivery.getDelivery(), delivery.getCommercialModelType(), delivery.getUseType());

      // btorres de momento lo comentamos hasta asegurar que las reglas estan OK
      cdnProvider.addGeolocalizationRules(updatedGeolocalizationRules);

      log.info("Submited {} geolocalize rules to '{}' CDN",
          updatedGeolocalizationRules == null ? 0 : updatedGeolocalizationRules.size(),
          cdnProvider.getName());
    }
  }

  public Object getGeolocalizationRules() throws GeoserviceException, Exception {
    Map<String, Object> allRules = new HashMap<>();
    for (GeoserviceProjectConfig.Deliveries delivery : geoserviceConfiguration.getDeliveries()) {
      CdnProvider cdnProvider = cdnProviderManager.getCdnProvider(delivery.getCdnProvider());
      Map<String, List<String>> deliveryRules = cdnProvider.getGeolocalizationRules();
      allRules.put(delivery.getDelivery(), deliveryRules);
    }
    return allRules;
  }

  public Object getGeolocalizationRulesForDelivery(String delivery)
      throws GeoserviceException, Exception {
    List<Object> rules = new LinkedList<>();
    GeoserviceProjectConfig.Deliveries deliveryObj = geoserviceConfiguration.getDeliveries()
        .stream().filter(deliveryInList -> deliveryInList.getDelivery().equalsIgnoreCase(delivery))
        .findFirst().orElseThrow(() -> new GeoserviceException(
            GeoserviceException.GeoserviceExceptionType.UNKNOWN_DELIVERY,
            "The specified delivery: '" + delivery
                + "' is missing. Please, add it to Geoservice's configuration."));
    CdnProvider cdnProvider = cdnProviderManager.getCdnProvider(deliveryObj.getCdnProvider());
    Map<String, List<String>> deliveryRules = cdnProvider.getGeolocalizationRules();
    rules.add(deliveryRules);
    return rules;
  }

  /**
   * Returns the value for the geolocalization rule being applied to the resource specified by
   * <code>resourcePath</code> within the given <code>delivery</code>.
   *
   * @param delivery
   * @param resourcePath
   * @return
   * @throws Exception
   */
  public String getSingleGeolocalizationRule(String delivery, String resourcePath)
      throws Exception {
    GeoserviceProjectConfig.Deliveries deliveryObj = geoserviceConfiguration.getDeliveries()
        .stream().filter(deliveryInList -> deliveryInList.getDelivery().equalsIgnoreCase(delivery))
        .findFirst().orElseThrow(() -> new GeoserviceException(
            GeoserviceException.GeoserviceExceptionType.UNKNOWN_DELIVERY,
            "The specified delivery: '" + delivery
                + "' is missing. Please, add it to Geoservice's configuration."));
    CdnProvider cdnProvider = cdnProviderManager.getCdnProvider(deliveryObj.getCdnProvider());
    return cdnProvider.getSingleGeolocalizationRule(resourcePath);
  }
}
