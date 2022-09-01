//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.08.30 a las 02:25:05 PM COT 
//


package com.ctv.publisher.model.releasedeal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}CommercialModelType"/&gt;
 *         &lt;element ref="{}Usage"/&gt;
 *         &lt;element ref="{}TerritoryCode"/&gt;
 *         &lt;element ref="{}PriceInformation" minOccurs="0"/&gt;
 *         &lt;element ref="{}ValidityPeriod"/&gt;
 *         &lt;element ref="{}ReleaseDisplayStartDate"/&gt;
 *         &lt;element ref="{}TrackListingPreviewStartDate"/&gt;
 *         &lt;element ref="{}CoverArtPreviewStartDate"/&gt;
 *         &lt;element ref="{}ClipPreviewStartDate"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "commercialModelType",
    "usage",
    "territoryCode",
    "priceInformation",
    "validityPeriod",
    "releaseDisplayStartDate",
    "trackListingPreviewStartDate",
    "coverArtPreviewStartDate",
    "clipPreviewStartDate"
})
@XmlRootElement(name = "DealTerms")
public class DealTerms {

    @XmlElement(name = "CommercialModelType", required = true)
    protected String commercialModelType;
    @XmlElement(name = "Usage", required = true)
    protected Usage usage;
    @XmlElement(name = "TerritoryCode", required = true)
    protected String territoryCode;
    @XmlElement(name = "PriceInformation")
    protected PriceInformation priceInformation;
    @XmlElement(name = "ValidityPeriod", required = true)
    protected ValidityPeriod validityPeriod;
    @XmlElement(name = "ReleaseDisplayStartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar releaseDisplayStartDate;
    @XmlElement(name = "TrackListingPreviewStartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar trackListingPreviewStartDate;
    @XmlElement(name = "CoverArtPreviewStartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar coverArtPreviewStartDate;
    @XmlElement(name = "ClipPreviewStartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar clipPreviewStartDate;

    /**
     * Obtiene el valor de la propiedad commercialModelType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommercialModelType() {
        return commercialModelType;
    }

    /**
     * Define el valor de la propiedad commercialModelType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommercialModelType(String value) {
        this.commercialModelType = value;
    }

    /**
     * Obtiene el valor de la propiedad usage.
     * 
     * @return
     *     possible object is
     *     {@link Usage }
     *     
     */
    public Usage getUsage() {
        return usage;
    }

    /**
     * Define el valor de la propiedad usage.
     * 
     * @param value
     *     allowed object is
     *     {@link Usage }
     *     
     */
    public void setUsage(Usage value) {
        this.usage = value;
    }

    /**
     * Obtiene el valor de la propiedad territoryCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerritoryCode() {
        return territoryCode;
    }

    /**
     * Define el valor de la propiedad territoryCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerritoryCode(String value) {
        this.territoryCode = value;
    }

    /**
     * Obtiene el valor de la propiedad priceInformation.
     * 
     * @return
     *     possible object is
     *     {@link PriceInformation }
     *     
     */
    public PriceInformation getPriceInformation() {
        return priceInformation;
    }

    /**
     * Define el valor de la propiedad priceInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceInformation }
     *     
     */
    public void setPriceInformation(PriceInformation value) {
        this.priceInformation = value;
    }

    /**
     * Obtiene el valor de la propiedad validityPeriod.
     * 
     * @return
     *     possible object is
     *     {@link ValidityPeriod }
     *     
     */
    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * Define el valor de la propiedad validityPeriod.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidityPeriod }
     *     
     */
    public void setValidityPeriod(ValidityPeriod value) {
        this.validityPeriod = value;
    }

    /**
     * Obtiene el valor de la propiedad releaseDisplayStartDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReleaseDisplayStartDate() {
        return releaseDisplayStartDate;
    }

    /**
     * Define el valor de la propiedad releaseDisplayStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReleaseDisplayStartDate(XMLGregorianCalendar value) {
        this.releaseDisplayStartDate = value;
    }

    /**
     * Obtiene el valor de la propiedad trackListingPreviewStartDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTrackListingPreviewStartDate() {
        return trackListingPreviewStartDate;
    }

    /**
     * Define el valor de la propiedad trackListingPreviewStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTrackListingPreviewStartDate(XMLGregorianCalendar value) {
        this.trackListingPreviewStartDate = value;
    }

    /**
     * Obtiene el valor de la propiedad coverArtPreviewStartDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCoverArtPreviewStartDate() {
        return coverArtPreviewStartDate;
    }

    /**
     * Define el valor de la propiedad coverArtPreviewStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCoverArtPreviewStartDate(XMLGregorianCalendar value) {
        this.coverArtPreviewStartDate = value;
    }

    /**
     * Obtiene el valor de la propiedad clipPreviewStartDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClipPreviewStartDate() {
        return clipPreviewStartDate;
    }

    /**
     * Define el valor de la propiedad clipPreviewStartDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClipPreviewStartDate(XMLGregorianCalendar value) {
        this.clipPreviewStartDate = value;
    }

}
