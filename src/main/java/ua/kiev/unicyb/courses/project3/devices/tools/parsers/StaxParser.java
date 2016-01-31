package ua.kiev.unicyb.courses.project3.devices.tools.parsers;

import ua.kiev.unicyb.courses.project3.devices.model.devices.OriginType;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TType;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TypeDeviceGroup;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TypeDevices;
import ua.kiev.unicyb.courses.project3.devices.model.types.TypePorts;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Iterator;

/**
 * Stax parser for the XML file.
 */
public class StaxParser {
    private TypeDevices devices = new TypeDevices();
    private TypeDevices.Device device;
    int current =0;

    private void startElement(XMLEvent event){
        StartElement startElement = event.asStartElement();
        String qName = startElement.getName().getLocalPart();

        switch (qName){
            case "devices": {
                current = 1;
                break;
            }
            case "device":{
                device = new TypeDevices.Device();
                Iterator<Attribute> attributeIterator = startElement.getAttributes();
                device.setId(attributeIterator.next().getValue());
                current = 2;
                break;
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
            case "COM":{
                current = 12;
                break;
            }
            case "LPT":{
                current = 13;
                break;
            }
            case "USB":{
                current = 14;
                break;
            }
        }
    }

    private void characters(XMLEvent event){
        Characters characters = event.asCharacters();
        String text = characters.getData();

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

    private void endElement(XMLEvent event){
        EndElement endElement = event.asEndElement();
        if (endElement.getName().getLocalPart().equalsIgnoreCase("device")){
            devices.getDevice().add(device);
        }
        current = 0;
    }

    private void processEvent(XMLEvent event){
        switch (event.getEventType()) {
            case XMLStreamConstants.START_ELEMENT:
                startElement(event);
                break;
            case XMLStreamConstants.CHARACTERS:
                characters(event);
                break;
            case XMLStreamConstants.END_ELEMENT:
                endElement(event);
                break;
        }
    }

    public TypeDevices parse(String filename) throws FileNotFoundException, XMLStreamException {
        devices = new TypeDevices();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(new FileReader(filename));
        while (reader.hasNext()){
            XMLEvent event = reader.nextEvent();
            processEvent(event);
        }
        return  devices;
    }
}
