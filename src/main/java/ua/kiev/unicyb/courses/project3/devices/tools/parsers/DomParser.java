package ua.kiev.unicyb.courses.project3.devices.tools.parsers;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ua.kiev.unicyb.courses.project3.devices.model.devices.OriginType;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TType;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TypeDeviceGroup;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TypeDevices;
import ua.kiev.unicyb.courses.project3.devices.model.types.TypePorts;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.DOMBuilder;
import org.xml.sax.SAXException;
import ua.kiev.unicyb.courses.project3.devices.model.devices.TypeDevices;

import javax.xml.parsers.DocumentBuilder;

/**
 * Dom parser for the XML file.
 */
public class DomParser {
    private static final Namespace DEFAULT_NAMESPACE = Namespace.getNamespace("http://devices.com/Device");
    private static final Namespace MY_NAMESPACE = Namespace.getNamespace("http://types.com/Device");
    private TypeDevices typeDevices = new TypeDevices();

    /**
     * Parse XML file.
     *
     * @param fileName path to the XML file
     * @return TypeDevices - list of Devices
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public TypeDevices parse(String fileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);
        DocumentBuilder dombuilder = factory.newDocumentBuilder();
        org.w3c.dom.Document w3cDocument = dombuilder.parse(fileName);
        DOMBuilder jdomBuilder = new DOMBuilder();
        Document jdomDocument = jdomBuilder.build(w3cDocument);
        Element root = jdomDocument.getRootElement();

        List<Element> devices = root.getChildren("device", DEFAULT_NAMESPACE);

        for (Element device : devices) {

            TypeDevices.Device newDevice = new TypeDevices.Device();
            newDevice.setId(device.getAttributeValue("id"));
            newDevice.setName(device.getChildText("name", DEFAULT_NAMESPACE));
            newDevice.setOrigin(OriginType.fromValue(device.getChildText("origin", DEFAULT_NAMESPACE)));
            newDevice.setPrice(BigInteger.valueOf(Long.valueOf(device.getChildText("price", DEFAULT_NAMESPACE))));
            newDevice.setCritical(Boolean.valueOf(device.getChildText("critical", DEFAULT_NAMESPACE)));

            Element type = device.getChild("type", DEFAULT_NAMESPACE);
            newDevice.setType(new TType());
            newDevice.getType().setIsPeripheral(Boolean.valueOf(type.getChildText("isPeripheral", DEFAULT_NAMESPACE)));
            newDevice.getType().setConsumption(BigInteger.valueOf(Long.valueOf(type.getChildText("consumption", DEFAULT_NAMESPACE))));
            newDevice.getType().setCooler(Boolean.valueOf(type.getChildText("cooler", DEFAULT_NAMESPACE)));
            newDevice.getType().setDeviceGroup(TypeDeviceGroup.fromValue(type.getChildText("deviceGroup", DEFAULT_NAMESPACE)));

            Element ports = type.getChild("ports", DEFAULT_NAMESPACE);
            newDevice.getType().setPorts(new TypePorts());
            newDevice.getType().getPorts().setCOM(Boolean.valueOf(ports.getChildText("COM", MY_NAMESPACE)));
            newDevice.getType().getPorts().setLPT(Boolean.valueOf(ports.getChildText("LPT", MY_NAMESPACE)));
            newDevice.getType().getPorts().setUSB(Boolean.valueOf(ports.getChildText("USB", MY_NAMESPACE)));

            typeDevices.getDevice().add(newDevice);
        }
        return typeDevices;
    }
}
