package com.ctv.geoservice.geolocalize;

import com.ctv.deliverty.client.SimpleDelegateFactory;
import com.ctv.deliverty.common.ICategoryDelegate;
import com.ctv.deliverty.common.IContentDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeoserviceAppConfig {

    private static final Logger log = LoggerFactory.getLogger(GeoserviceAppConfig.class);

    @Bean
    public IContentDelegate getContentDelegate() throws GeoserviceException {
        try {
            return new SimpleDelegateFactory().getContentDelegate();
        } catch (Exception e) {
            throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.COULD_NOT_CONNECT_TO_CMS, e);
        }
    }

    @Bean
    public ICategoryDelegate getCategoryDelegate() throws GeoserviceException {
        try {
            return new SimpleDelegateFactory().getCategoryDelegate();
        } catch (Exception e) {
            throw new GeoserviceException(GeoserviceException.GeoserviceExceptionType.COULD_NOT_CONNECT_TO_CMS, e);
        }
    }

}