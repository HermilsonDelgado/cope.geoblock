package com.ctv.geoservice.geolocalize;

public class CdnResponseException extends GeoserviceException {

    private int responseStatus;

    public CdnResponseException(GeoserviceExceptionType type, int responseStatus) {
        super(type);
        this.responseStatus = responseStatus;
    }

    public CdnResponseException(GeoserviceExceptionType type, String message, int responseStatus) {
        super(type, message);
        this.responseStatus = responseStatus;
    }

    public CdnResponseException(GeoserviceExceptionType type, String message, Throwable throwable, int responseStatus) {
        super(type, message, throwable);
        this.responseStatus = responseStatus;
    }

    public CdnResponseException(GeoserviceExceptionType type, Throwable throwable, int responseStatus) {
        super(type, throwable);
        this.responseStatus = responseStatus;
    }

    public int getResponseStatus() {
        return responseStatus;
    }
}
