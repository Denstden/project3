package ua.kiev.unicyb.courses.project3.devices;

import ua.kiev.unicyb.courses.project3.devices.model.devices.TypeDevices;
import org.xml.sax.SAXException;
import ua.kiev.unicyb.courses.project3.devices.tools.parsers.DomParser;
import ua.kiev.unicyb.courses.project3.devices.tools.parsers.SaxParser;
import ua.kiev.unicyb.courses.project3.devices.tools.parsers.StaxParser;
import ua.kiev.unicyb.courses.project3.devices.tools.Validator;
import ua.kiev.unicyb.courses.project3.devices.tools.XMLChanger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 1.	������� ���� XML � ��������������� ��� ����� XSD.
 2.	��� ���������� XSD ������������ ������� � ����������� ����, ������������, ������� � ���������� ��������, ����������� ������������� ��������� � ���� ID.
 3.	������������� (�������) Java-�����, ��������������� ������� ��������.
 4.	������� Java-���������� ��� ������� XML-��������� � ������������� ��������� �������� ����������� �� XML-�����. ��� ������� ������������ SAX, DOM � StAX �������. ��� ���������� �������� ������������ ��������� Comparator.
 5.	���������� �������� XML-��������� � ������������ XSD.
 6.	���������� �����, ������������ �������������� �������������� XML-��������� � ��������, ��������� � ������ �������.

 ������������ ������������� ����� ��������� ��������������:
 �	Name � �������� ��������������.
 �	Origin � ������ ������������.
 �	Price � ���� (0 � n ������).
 �	Type (������ ���� ���������) � ������������ ���� ���, ����������������� (����), ������� ������ (���� ���� ���), ������ ������������� (���������� �����-������, ��������������), ����� (COM, USB, LPT).
 �	Critical � �������� �� ������� �������������� ��� ������ ����������.
 �������� ������� ������� Device.
 */
public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        TypeDevices devices;

        //Dom parser
        System.out.println("Dom parser:");
        DomParser domParser = new DomParser();
        devices = domParser.parse("src/main/resources/xml/devices.xml");
        devices.getDevice().forEach(System.out::println);
        System.out.println();

        //Sax parser
        System.out.println("Sax parser:");
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        SaxParser parser = new SaxParser();
        saxParser.parse("src/main/resources/xml/devices.xml", parser);
        devices = parser.getDevices();
        devices.getDevice().forEach(System.out::println);
        System.out.println();

        //Stax parser
        System.out.println("Stax parser:");
        StaxParser staxParser = new StaxParser();
        devices = staxParser.parse("src/main/resources/xml/devices.xml");
        devices.getDevice().forEach(System.out::println);
        System.out.println();

        //XML changer
        System.out.println("XML changer:");
        XMLChanger.changeXML("src/main/resources/xml/devices.xml", "d002", "price", "14000");
        devices = staxParser.parse("src/main/resources/xml/devices.xml");
        devices.getDevice().forEach(System.out::println);
        System.out.println();

        //Validator
        System.out.println("Validator:");
        System.out.println(Validator.validate("src/main/resources/xml/devices.xml", "src/main/resources/xml/devices.xsd"));
        System.out.println();
    }
}
