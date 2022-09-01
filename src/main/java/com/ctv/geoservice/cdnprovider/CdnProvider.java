package com.ctv.geoservice.cdnprovider;

import com.ctv.geoservice.geolocalize.GeoserviceException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public interface CdnProvider {
    /**
     * Returns the CDN Provider Name
     *
     * @return the CDN Provider Name
     */
    String getName();

    /**
     * Adds the geolocalize rules specified in <code>newRules</code> to the set of existing rules in the CDN provider.
     * Every pair of <code>newRules</code> key-value dictionary is expected to hold the path of a resource (key) and the list of those country codes for where the resource is permitted or banned (country code preceded with "!").
     * An example of an entry in which we would be permitting access to the Bohemian Rhapsody videoclip to Spain and Colombia users, and forbidding it for Brasil users follows:
     *  <code>newRules.put("/videoclips/queen/bohemian-rhapsody.mp3", new LinkedList<String>(Arrays.asList("ES", "CO", "!BR");</code>
     *
     * @param newRules map whose key represents an asset (typically the URL path of a resource) and whose value is a list of country codes for which its playback is banned (country code preceded with "!") or permitted.
     */
    void addGeolocalizationRules(Map<String, List<String>> newRules) throws GeoserviceException, Exception;

    /**
     * Gets the geolocalize rules that the CDN provider is currently applying.
     *
     * @return A map containing the geolocalize rules that the CDN provider is currently applying. Each map key contains the path of a resource whilst it value contains a list of permitted or banned country codes.
     */
    Map<String, List<String>> getGeolocalizationRules() throws GeoserviceException, Exception;


    /**
     * Queries the CDN for a single resource path and returns its geolocalization rule (if exists).
     * @param resource identifier for the resource being searched.
     * @return geolocalization rule for the specified <code>resource</code>.
     */
    String getSingleGeolocalizationRule(String resource) throws GeoserviceException, Exception;
}
