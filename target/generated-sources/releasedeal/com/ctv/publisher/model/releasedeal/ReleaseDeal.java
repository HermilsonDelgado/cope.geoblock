//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.08.30 a las 02:25:05 PM COT 
//


package com.ctv.publisher.model.releasedeal;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}DealReleaseReference"/&gt;
 *         &lt;element ref="{}Deal" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "dealReleaseReference",
    "deal"
})
@XmlRootElement(name = "ReleaseDeal")
public class ReleaseDeal {

    @XmlElement(name = "DealReleaseReference", required = true)
    protected String dealReleaseReference;
    @XmlElement(name = "Deal")
    protected List<Deal> deal;

    /**
     * Obtiene el valor de la propiedad dealReleaseReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealReleaseReference() {
        return dealReleaseReference;
    }

    /**
     * Define el valor de la propiedad dealReleaseReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealReleaseReference(String value) {
        this.dealReleaseReference = value;
    }

    /**
     * Gets the value of the deal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Deal }
     * 
     * 
     */
    public List<Deal> getDeal() {
        if (deal == null) {
            deal = new ArrayList<Deal>();
        }
        return this.deal;
    }

}
