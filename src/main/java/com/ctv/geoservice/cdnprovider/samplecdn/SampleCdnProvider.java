package com.ctv.geoservice.cdnprovider.samplecdn;

import com.ctv.geoservice.cdnprovider.CdnProvider;
import com.ctv.geoservice.geolocalize.GeoserviceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("samplecdn")
public class SampleCdnProvider implements CdnProvider {

    private static final Logger log = LoggerFactory.getLogger(SampleCdnProvider.class);

    private static final String cdnName = "SAMPLE CDN";

    @Override
    public String getName() {
        return cdnName;
    }

    @Override
    public void addGeolocalizationRules(Map<String, List<String>> newRules) throws GeoserviceException {
        throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.UNIMPLEMENTED_METHOD, "Esta es una implementación de ejemplo que nunca debería ser usada en entornos productivos.");
    }

    @Override
    public Map<String, List<String>> getGeolocalizationRules() throws GeoserviceException {
        throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.UNIMPLEMENTED_METHOD, "Esta es una implementación de ejemplo que nunca debería ser usada en entornos productivos.");
    }

    @Override
    public String getSingleGeolocalizationRule(String resource) throws GeoserviceException {
        throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.UNIMPLEMENTED_METHOD, "Esta es una implementación de ejemplo que nunca debería ser usada en entornos productivos.");
    }
}
