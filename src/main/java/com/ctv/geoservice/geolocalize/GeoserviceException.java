package com.ctv.geoservice.geolocalize;

public class GeoserviceException extends Exception {

    public enum GeoserviceExceptionType {
        COULD_NOT_CONNECT_TO_CMS("No se ha podido conectar con el CMS."),
        COULD_NOT_CONNECT_TO_CDN("No se ha podido conectar con la CDN."),
        ERROR_RETRIEVING_CONTENTS_FROM_CMS("No se han podido obtener contenidos del CMS."),
        UNKNOWN_CDN_PROVIDER("Proveedor de CDN desconocido."),
        UNKNOWN_DEALING_PROTOCOL("Protocolo de deals desconocido."),
        UNIMPLEMENTED_METHOD("El método o servicio que se está intentando usar no está implementado."),
        CONFIGURATION_ERROR("Error en los ficheros de configuracion."),
        CDN_HTTP_RESPONSE_ERROR("Se ha producido un error al hacer la petición HTTP a la CDN."),
        DEVELOPMENT_ERROR("Error de desarrollo. Este error indica que alguna parte del desarrollo de esta aplicación es incorrecta y hay que revisar el código antes de subir a producción."),
        UNKNOWN_DELIVERY("El delivery no esta registrado en la configuración de la aplicación.");

        private final String message;

        GeoserviceExceptionType(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

    private static GeoserviceExceptionType type;


    public GeoserviceException(GeoserviceExceptionType type) {
        super();
        this.type = type;
    }

    public GeoserviceException(GeoserviceExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public GeoserviceException(GeoserviceExceptionType type, String message, Throwable throwable) {
        super(message, throwable);
        this.type = type;
    }

    public GeoserviceException(GeoserviceExceptionType type, Throwable throwable) {
        super(throwable);
        this.type = type;
    }

    @Override
    public String getMessage() {
        if (super.getMessage() != null && !("".compareTo(super.getMessage()) == 0)) return super.getMessage();
        else return type.toString();
    }
}
