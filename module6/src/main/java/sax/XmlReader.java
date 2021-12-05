package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlReader {
    public Object read(String path) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();
            saxParser.parse(path, handler);

            return handler.getObject();
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }
}
