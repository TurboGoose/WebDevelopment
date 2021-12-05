package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import reflections.ReflectionHelper;

public class SaxHandler extends DefaultHandler {
    private static final String CLASS_TAG = "class";
    private String element = null;
    private Object object = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!CLASS_TAG.equals(qName)) {
            element = qName;
        } else {
            String classname = attributes.getValue(0);
            object = ReflectionHelper.createInstance(classname);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (element != null) {
            String value = new String(ch, start, length);
            ReflectionHelper.setFieldValue(object, element, value);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = null;
    }

    public Object getObject() {
        return object;
    }
}
