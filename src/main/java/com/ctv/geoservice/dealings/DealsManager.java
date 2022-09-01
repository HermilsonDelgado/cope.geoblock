package com.ctv.geoservice.dealings;

import com.ctv.geoservice.geolocalize.GeoserviceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DealsManager {

    @Resource(name = "ddex")
    private DealingsService ddexDealing;

    @Resource(name = "sampledealing")
    private DealingsService sampleDealing;

    public DealingsService getDealingService(String name) throws GeoserviceException {
        switch (name) {
            case "ddex":
                return ddexDealing;
            case "sampledealing":
                return sampleDealing;
            default:
                throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.UNKNOWN_DEALING_PROTOCOL, "There is no implementation for the specified dealing protocol: '" + name + "'");
        }
    }
}
