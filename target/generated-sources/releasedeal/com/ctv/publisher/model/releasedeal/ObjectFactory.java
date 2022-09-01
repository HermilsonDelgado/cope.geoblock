//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.08.30 a las 02:25:05 PM COT 
//


package com.ctv.publisher.model.releasedeal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ctv.publisher.model.releasedeal package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UseType_QNAME = new QName("", "UseType");
    private final static QName _StartDate_QNAME = new QName("", "StartDate");
    private final static QName _StartDateTime_QNAME = new QName("", "StartDateTime");
    private final static QName _EndDate_QNAME = new QName("", "EndDate");
    private final static QName _EndDateTime_QNAME = new QName("", "EndDateTime");
    private final static QName _CommercialModelType_QNAME = new QName("", "CommercialModelType");
    private final static QName _TerritoryCode_QNAME = new QName("", "TerritoryCode");
    private final static QName _ReleaseDisplayStartDate_QNAME = new QName("", "ReleaseDisplayStartDate");
    private final static QName _TrackListingPreviewStartDate_QNAME = new QName("", "TrackListingPreviewStartDate");
    private final static QName _CoverArtPreviewStartDate_QNAME = new QName("", "CoverArtPreviewStartDate");
    private final static QName _ClipPreviewStartDate_QNAME = new QName("", "ClipPreviewStartDate");
    private final static QName _DealReleaseReference_QNAME = new QName("", "DealReleaseReference");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ctv.publisher.model.releasedeal
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Usage }
     * 
     */
    public Usage createUsage() {
        return new Usage();
    }

    /**
     * Create an instance of {@link ValidityPeriod }
     * 
     */
    public ValidityPeriod createValidityPeriod() {
        return new ValidityPeriod();
    }

    /**
     * Create an instance of {@link DealTerms }
     * 
     */
    public DealTerms createDealTerms() {
        return new DealTerms();
    }

    /**
     * Create an instance of {@link PriceInformation }
     * 
     */
    public PriceInformation createPriceInformation() {
        return new PriceInformation();
    }

    /**
     * Create an instance of {@link PriceType }
     * 
     */
    public PriceType createPriceType() {
        return new PriceType();
    }

    /**
     * Create an instance of {@link Deal }
     * 
     */
    public Deal createDeal() {
        return new Deal();
    }

    /**
     * Create an instance of {@link ReleaseDeal }
     * 
     */
    public ReleaseDeal createReleaseDeal() {
        return new ReleaseDeal();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UseType")
    public JAXBElement<String> createUseType(String value) {
        return new JAXBElement<String>(_UseType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "StartDate")
    public JAXBElement<XMLGregorianCalendar> createStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StartDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "StartDateTime")
    public JAXBElement<XMLGregorianCalendar> createStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StartDateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "EndDate")
    public JAXBElement<XMLGregorianCalendar> createEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_EndDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "EndDateTime")
    public JAXBElement<XMLGregorianCalendar> createEndDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_EndDateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CommercialModelType")
    public JAXBElement<String> createCommercialModelType(String value) {
        return new JAXBElement<String>(_CommercialModelType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TerritoryCode")
    public JAXBElement<String> createTerritoryCode(String value) {
        return new JAXBElement<String>(_TerritoryCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ReleaseDisplayStartDate")
    public JAXBElement<XMLGregorianCalendar> createReleaseDisplayStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReleaseDisplayStartDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TrackListingPreviewStartDate")
    public JAXBElement<XMLGregorianCalendar> createTrackListingPreviewStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TrackListingPreviewStartDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CoverArtPreviewStartDate")
    public JAXBElement<XMLGregorianCalendar> createCoverArtPreviewStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CoverArtPreviewStartDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ClipPreviewStartDate")
    public JAXBElement<XMLGregorianCalendar> createClipPreviewStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClipPreviewStartDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DealReleaseReference")
    public JAXBElement<String> createDealReleaseReference(String value) {
        return new JAXBElement<String>(_DealReleaseReference_QNAME, String.class, null, value);
    }

}
