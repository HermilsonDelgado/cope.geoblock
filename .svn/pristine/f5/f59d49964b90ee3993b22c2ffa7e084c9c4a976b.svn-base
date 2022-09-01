package com.ctv.geoservice.geolocalize;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties(prefix = "configuration")
@Configuration
public class GeoserviceProjectConfig {

    private String locale;

    private List<Deliveries> deliveries;

    public GeoserviceProjectConfig() {
    }

    public GeoserviceProjectConfig(String locale, List<Deliveries> deliveries) {
        this.locale = locale;
        this.deliveries = deliveries;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<Deliveries> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Deliveries> deliveries) {
        this.deliveries = deliveries;
    }


    public static class Deliveries {

        private String name;
        private String delivery;
        private String cdnProvider;
        private String deals;
        private String commercialModelType;
        private String useType;

        public Deliveries() { }

        public Deliveries(String name, String delivery, String cdnProvider, String deals, String commercialModelType, String useType) {
            this.name = name;
            this.delivery = delivery;
            this.cdnProvider = cdnProvider;
            this.deals = deals;
            this.commercialModelType = commercialModelType;
            this.useType = useType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public String getCdnProvider() {
            return cdnProvider;
        }

        public void setCdnProvider(String cdnProvider) {
            this.cdnProvider = cdnProvider;
        }

        public String getDeals() {
            return deals;
        }

        public void setDeals(String deals) {
            this.deals = deals;
        }

        public String getCommercialModelType() {
            return commercialModelType;
        }

        public void setCommercialModelType(String commercialModelType) {
            this.commercialModelType = commercialModelType;
        }

        public String getUseType() {
            return useType;
        }

        public void setUseType(String useType) {
            this.useType = useType;
        }
    }
}
