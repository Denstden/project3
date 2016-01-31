//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.31 at 02:31:25 AM EET 
//


package ua.kiev.unicyb.courses.project3.devices.model.devices;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import ua.kiev.unicyb.courses.project3.devices.model.types.TypePorts;
import ua.kiev.unicyb.courses.project3.devices.model.types.TypePorts;


/**
 * <p>Java class for tType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isPeripheral" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="consumption" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="cooler" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deviceGroup" type="{http://devices.com/Device}typeDeviceGroup"/>
 *         &lt;element name="ports" type="{http://types.com/Device}typePorts"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tType", propOrder = {
    "isPeripheral",
    "consumption",
    "cooler",
    "deviceGroup",
    "ports"
})
public class TType {

    protected boolean isPeripheral;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger consumption;
    protected boolean cooler;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TypeDeviceGroup deviceGroup;
    @XmlElement(required = true)
    protected TypePorts ports;

    /**
     * Gets the value of the isPeripheral property.
     * 
     */
    public boolean isIsPeripheral() {
        return isPeripheral;
    }

    /**
     * Sets the value of the isPeripheral property.
     * 
     */
    public void setIsPeripheral(boolean value) {
        this.isPeripheral = value;
    }

    /**
     * Gets the value of the consumption property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getConsumption() {
        return consumption;
    }

    /**
     * Sets the value of the consumption property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setConsumption(BigInteger value) {
        this.consumption = value;
    }

    /**
     * Gets the value of the cooler property.
     * 
     */
    public boolean isCooler() {
        return cooler;
    }

    /**
     * Sets the value of the cooler property.
     * 
     */
    public void setCooler(boolean value) {
        this.cooler = value;
    }

    /**
     * Gets the value of the deviceGroup property.
     * 
     * @return
     *     possible object is
     *     {@link TypeDeviceGroup }
     *     
     */
    public TypeDeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    /**
     * Sets the value of the deviceGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeDeviceGroup }
     *     
     */
    public void setDeviceGroup(TypeDeviceGroup value) {
        this.deviceGroup = value;
    }

    /**
     * Gets the value of the ports property.
     * 
     * @return
     *     possible object is
     *     {@link TypePorts }
     *     
     */
    public TypePorts getPorts() {
        return ports;
    }

    /**
     * Sets the value of the ports property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypePorts }
     *     
     */
    public void setPorts(TypePorts value) {
        this.ports = value;
    }

    @Override
    public String toString() {
        return "TType{" +
                "isPeripheral=" + isPeripheral +
                ", consumption=" + consumption +
                ", cooler=" + cooler +
                ", deviceGroup=" + deviceGroup +
                ", ports=" + ports +
                '}';
    }
}
