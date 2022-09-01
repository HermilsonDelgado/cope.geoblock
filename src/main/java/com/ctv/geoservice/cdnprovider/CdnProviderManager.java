package com.ctv.geoservice.cdnprovider;

import com.ctv.geoservice.geolocalize.GeoserviceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CdnProviderManager {

    @Resource(name = "fastly")
    private CdnProvider fastlyCDN;

    @Resource(name = "samplecdn")
    private CdnProvider sampleCDN;

    public CdnProvider getCdnProvider(String name) throws GeoserviceException {
        switch (name) {
            case "fastly":
                return fastlyCDN;
            case "samplecdn":
                return sampleCDN;
            default:
                throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.UNKNOWN_CDN_PROVIDER, "There is no implementation for the CDN provider with name '" + name + "'");
        }
    }

}
