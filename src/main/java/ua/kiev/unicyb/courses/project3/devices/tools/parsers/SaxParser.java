package ua.kiev.unicyb.courses.project3.devices.tools.parsers;

import ua.kiev.unicyb.courses.project3.devices.model.devices.OriginType;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TType;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TypeDeviceGroup;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TypeDevices;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.kiev.unicyb.courses.project3.devices.model.types.TypePorts;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TypeDevices;

import java.math.BigInteger;

/**
 * Sax parser for the XML file.
 */
public class SaxParser extends DefaultHandler {
    private TypeDevices devices = new TypeDevices();
    private TypeDevices.Device device;
    int current =0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName){
            case "devices": {
                current=1;
                break;
            }
            case "device": {
                device = new TypeDevices.Device();
                device.setId(attributes.getValue(0));
                current = 2;
            }
            case "name":{
                current = 3;
                break;
            }
            case "origin":{
                current = 4;
                break;
            }
            case "price":{
                current = 5;
                break;
            }
            case "type":{
                device.setType(new TType());
                current = 6;
                break;
            }
            case "isPeripheral":{
                current = 7;
                break;
            }
            case "consumption":{
                current = 8;
                break;
            }
            case "cooler":{
                current = 9;
                break;
            }
            case "deviceGroup":{
                current = 10;
                break;
            }
            case "ports":{
                device.getType().setPorts(new TypePorts());
                current = 11;
                break;
            }
            case "types:COM":{
                current = 12;
                break;
            }
            case "types:LPT":{
                current = 13;
                break;
            }
            case "types:USB":{
                current = 14;
                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "device": {
                devices.getDevice().add(device);
            }
        }
        current = 0;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length);
        switch (current) {
            case 3: {
                device.setName(text);
                current = 0;
                break;
            }
            case 4: {
                device.setOrigin(OriginType.fromValue(text));
                current = 0;
                break;
            }
            case 5: {
                device.setPrice(BigInteger.valueOf(Long.valueOf(text)));
                current = 0;
                break;
            }
            case 7: {
                device.getType().setIsPeripheral(Boolean.valueOf(text));
                current = 0;
                break;
            }
            case 8: {
                device.getType().setConsumption(BigInteger.valueOf(Long.valueOf(text)));

                current = 0;
                break;
            }
            case 9: {
                device.getType().setCooler(Boolean.valueOf(text));
                current = 0;
                break;
            }
            case 10: {
                device.getType().setDeviceGroup(TypeDeviceGroup.fromValue(text));
                current = 0;
                break;
            }
            case 12: {
                device.getType().getPorts().setCOM(Boolean.valueOf(text));
                current = 0;
                break;
            }
            case 13: {
                device.getType().getPorts().setLPT(Boolean.valueOf(text));
                current = 0;
                break;
            }
            case 14: {
                device.getType().getPorts().setUSB(Boolean.valueOf(text));
                current = 0;
                break;
            }

        }
    }

    public TypeDevices getDevices() {
        return devices;
    }
}
