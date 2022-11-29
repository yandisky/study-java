import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.util.Stack;

public class XmlTest {
    public static void main(String[] args) {
        // DOM、SAX、JDOM、DOM4J
        sax();
    }

    private static void dom() {
        long beginTime = System.currentTimeMillis();
        File file = new File("src/main/resources/xmltest.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList nodeList = document.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println(node.getNodeName() + ":" + node.getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("运行时间:" + (System.currentTimeMillis() - beginTime));
    }

    private static void sax() {
        class MyXMLReader extends DefaultHandler {
            Stack tags = new Stack();

            MyXMLReader() {
                super();
            }

            public void characters(char[] ch, int start, int length) throws SAXException {
                System.out.println("characters:");
                String tag = (String) tags.peek();
                System.out.println("tag:" + tag);
                System.out.println("char:" + new String(ch, start, length));
            }

            public void startElement(String uri, String localName, String qName, Attributes attrs) {
                System.out.println("startElement:");
                System.out.println(uri + ":" + localName + ":" + qName);
                tags.push(qName);
            }
        }

        long beginTime = System.currentTimeMillis();
        File file = new File("src/main/resources/xmltest.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            MyXMLReader reader = new MyXMLReader();
            saxParser.parse(file, reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("运行时间:" + (System.currentTimeMillis() - beginTime));
    }
}
