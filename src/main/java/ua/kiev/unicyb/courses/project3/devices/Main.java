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
 1.	—оздать файл XML и соответствующую ему схему XSD.
 2.	ѕри разработке XSD использовать простые и комплексные типы, перечислени€, шаблоны и предельные значени€, об€зательно использование атрибутов и типа ID.
 3.	—генерировать (создать) Java-класс, соответствующий данному описанию.
 4.	—оздать Java-приложение дл€ разбора XML-документа и инициализации коллекции объектов информацией из XML-файла. ƒл€ разбора использовать SAX, DOM и StAX парсеры. ƒл€ сортировки объектов использовать интерфейс Comparator.
 5.	ѕроизвести проверку XML-документа с привлечением XSD.
 6.	ќпределить метод, производ€щий преобразование разработанного XML-документа в документ, указанный в каждом задании.

  омпьютерные комплектующие имеют следующие характеристики:
 Х	Name Ц название комплектующего.
 Х	Origin Ц страна производства.
 Х	Price Ц цена (0 Ц n рублей).
 Х	Type (должно быть несколько) Ц периферийное либо нет, энергопотребление (ватт), наличие кулера (есть либо нет), группа комплектующих (устройства ввода-вывода, мультимедийные), порты (COM, USB, LPT).
 Х	Critical Ц критично ли наличие комплектующего дл€ работы компьютера.
  орневой элемент назвать Device.
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
