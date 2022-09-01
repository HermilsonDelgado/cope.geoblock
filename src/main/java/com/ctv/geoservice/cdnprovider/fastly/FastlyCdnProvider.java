/*
 *  Copyright (c) 2022 Agile Content S.A. All rights reserved.
 */

package com.ctv.geoservice.cdnprovider.fastly;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctv.geoservice.cdnprovider.CdnProvider;
import com.ctv.geoservice.geolocalize.GeoserviceException;

@Component("fastly")
public class FastlyCdnProvider implements CdnProvider {

  private static final Logger log = LoggerFactory.getLogger(FastlyCdnProvider.class);

  private static final String cdnName = "FASTLY";

  @Autowired
  private FastlyApiService apiService;

  @Override
  public String getName() {
    return cdnName;
  }

  @Override
  public void addGeolocalizationRules(Map<String, List<String>> newRules)
      throws GeoserviceException, JSONException {
    log.info("Adding {} more rules to the CDN '{}' dictionary...",
        (newRules != null ? newRules.size() : 0), cdnName);

    apiService.performBatchUpdate(newRules);
  }

  @Override
  public Map<String, List<String>> getGeolocalizationRules()
      throws GeoserviceException, JSONException {
    return apiService.getDictionaryItems();
  }

  @Override
  public String getSingleGeolocalizationRule(String resource)
      throws GeoserviceException, JSONException {
    return apiService.getSingleDictionaryItem(resource).getString("item_value");
  }

}
