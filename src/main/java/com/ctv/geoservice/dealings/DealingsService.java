package com.ctv.geoservice.dealings;

import com.ctv.geoservice.geolocalize.GeoserviceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DealingsService {

    /**
     * This method retrieves all the ASSETs on the CMS and calculates their commercial permissions per country.
     * @param deliveryUid Only ASSETs on the specified <code>deliveryUid</code> delivery will be processed.
     * @param commercialModelType
     * @param useType
     * @return a Map containing the permissions for each country every single resource has in the CMS.
     */
    Map<String, List<String>> getGeolocalizationRules(String deliveryUid, String commercialModelType, String useType) throws GeoserviceException;
}
