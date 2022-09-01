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
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{}DealTerms"/&gt;
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
    "dealTerms"
})
@XmlRootElement(name = "Deal")
public class Deal {

    @XmlElement(name = "DealTerms", required = true)
    protected DealTerms dealTerms;

    /**
     * Obtiene el valor de la propiedad dealTerms.
     * 
     * @return
     *     possible object is
     *     {@link DealTerms }
     *     
     */
    public DealTerms getDealTerms() {
        return dealTerms;
    }

    /**
     * Define el valor de la propiedad dealTerms.
     * 
     * @param value
     *     allowed object is
     *     {@link DealTerms }
     *     
     */
    public void setDealTerms(DealTerms value) {
        this.dealTerms = value;
    }

}
