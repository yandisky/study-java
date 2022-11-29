import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

public class XmlDom4jTest {
    public static void main(String[] args) {
        Document document = load("xmltest.xml");
        Element element = document.getRootElement();
        for (Iterator i = element.elementIterator(); i.hasNext(); ) {
            Element element1 = (Element) i.next();
            System.out.println(element1.getName() + ":" + element1.getText());
        }
    }

    private static Document load(String filename) {
        Document document = null;
        SAXReader saxReader = new SAXReader();
        try {
            document = saxReader.read(new File("src/main/resources/" + filename));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
