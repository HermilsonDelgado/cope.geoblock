package com.ctv.geoservice.dealings;

import com.ctv.geoservice.geolocalize.GeoserviceException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("sampledealing")
public class SampleDealing implements DealingsService {
    @Override
    public Map<String, List<String>> getGeolocalizationRules(String deliveryUid, String commercialModelType, String useType) throws GeoserviceException {
        throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.UNIMPLEMENTED_METHOD, "Esta es una implementación de ejemplo que nunca debería ser usada en entornos productivos.");
    }
}
