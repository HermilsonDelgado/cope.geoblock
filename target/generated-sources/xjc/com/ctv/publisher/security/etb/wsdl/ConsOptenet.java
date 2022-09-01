//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.08.30 a las 02:25:04 PM COT 
//


package com.ctv.publisher.security.etb.wsdl;

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
 *         &lt;element name="NumConexion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "numConexion"
})
@XmlRootElement(name = "Cons_Optenet")
public class ConsOptenet {

    @XmlElement(name = "NumConexion")
    protected String numConexion;

    /**
     * Obtiene el valor de la propiedad numConexion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumConexion() {
        return numConexion;
    }

    /**
     * Define el valor de la propiedad numConexion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumConexion(String value) {
        this.numConexion = value;
    }

}
