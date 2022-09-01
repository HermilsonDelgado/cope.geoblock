Geoservice Readme
=================

Local
-----

### RUN

```$ mvn spring-boot:run -P local```

### DEBUG:

#### Sin CharlesProxy
```$ mvn clean package -P local && java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar target/geoservice.war```

#### Con CharlesProxy 
Útil para debugar las peticiones http (al CMS) y https (a las CDN).

Para habilitar la captura del tráfico HTTPS en Charles es necesario instalar el certificado en el keystore de Java ([ver enlace](https://stackoverflow.com/questions/47808298/charles-monitor-terminal-request/47828844#47828844)):

```$ sudo keytool -import -alias charles -keystore cacerts -file ~/Descargas/charles-ssl-proxying-certificate.cer```

Una vez instalado, ejecutar el proyecto con los parámetros https.proxyHost y https.proxyPort:

```$ mvn clean package -P local && java -Dhttp.proxyHost=localhost -Dhttp.proxyPort=8888 -Dhttps.proxyHost=localhost -Dhttps.proxyPort=8888 -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar target/geoservice.war```


then, debug as _remote java application_ on port 8000 in your desired IDE.

Packaging
---------
```mvn clean package -P dev```


