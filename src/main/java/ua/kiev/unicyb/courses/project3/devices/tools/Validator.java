package ua.kiev.unicyb.courses.project3.devices.tools;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;

/**
 * Implementation of class ErrorHandler for Validator.
 */
class MyErrorHandler implements ErrorHandler {

    boolean flag = true;

    @Override
    public void warning(SAXParseException e) {
        System.err.println("warning: " + getLineAddress(e) + "-" + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        flag = false;
        System.err.println((getLineAddress(e) + " - " + e.getMessage()));
    }

    @Override
    public void fatalError(SAXParseException e) {
        flag = false;
        System.err.println(getLineAddress(e) + " - " + e.getMessage());
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}

/**
 * Class Validator allows to validate XML file by XSD file.
 */
public class Validator {

    /**
     * Validate xmlFile by xsdFile.
     *
     * @param xmlFile path to XML file
     * @param xsdFile path to XSD file
     * @return true if xmlFile is valid, another - false
     */
    public static boolean validate(String xmlFile, String xsdFile) throws SAXException, IOException {
        DOMParser parser = new DOMParser();
        MyErrorHandler handler = new MyErrorHandler();

        parser.setErrorHandler(handler);
        parser.setFeature("http://xml.org/sax/features/validation", true);
        parser.setFeature("http://apache.org/xml/features/validation/schema", true);
        parser.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
        parser.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", xsdFile);

        parser.parse(xmlFile);
        return handler.flag;
    }

}
