package softuni.parser;

import jdk.internal.org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;

public interface XMLParser {

    <T> T readFromXml(Class<T> classes, String fileName) throws JAXBException, SAXException;
    <T> void writeToXML(T object, String fileName) throws JAXBException;
}
