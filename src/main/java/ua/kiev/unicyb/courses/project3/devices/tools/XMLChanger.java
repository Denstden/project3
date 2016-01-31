package ua.kiev.unicyb.courses.project3.devices.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

/**
 * Class XMLChanger allows to change the XML file.
 */
public class XMLChanger {

    /**
     * Change the XML file.
     *
     * @param xmlFile path to XML file
     * @param id id of element which need to change
     * @param tagName tag which need to change
     * @param value new value of tag
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static void changeXML(String xmlFile, String id, String tagName, String value) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);
        DocumentBuilder dombuilder = factory.newDocumentBuilder();
        org.w3c.dom.Document w3cDocument  = dombuilder.parse(xmlFile);
        DOMBuilder jdomBuilder = new DOMBuilder();
        Document jdomDocument = jdomBuilder.build(w3cDocument);
        Element root = jdomDocument.getRootElement();

        List<Element> listCards = root.getChildren("device", Namespace.getNamespace("http://devices.com/Device"));

        for (Element card : listCards) {

            if (card.getAttributeValue("id").equals(id)) {
                Element childTag = card.getChild(tagName, Namespace.getNamespace("http://devices.com/Device"));
                childTag.setText(value);
            }
        }
        XMLOutputter serializer = new XMLOutputter();
        serializer.output(jdomDocument, new FileOutputStream(xmlFile));
    }
}
