import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.File;

public class XmlXStreamTest {
    public static void main(String[] args) {
        // StaxDriver
        XStream xStream = new XStream(new StaxDriver());

        // DomDriver
        // XStream xstream = new XStream(new DomDriver());

        // JDomDriver
        // XStream xstream = new XStream(new JDomDriver());

        // JsonHierarchicalStreamDriver
        // XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
        // public HierarchicalStreamWriter createWriter(Writer writer) {
        // return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
        // }
        //
        // });

        XmlXStreamBean xmlXStreamBean = new XmlXStreamBean();
        String xml = xStream.toXML(xmlXStreamBean);
        System.out.println(xml);
        //解决xStream:Security framework of XStream not initialized, XStream is probably vulnerable
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{XmlXStreamBean.class});
        //将别名与xml名字对应
        xStream.alias("xml", XmlXStreamBean.class);
        xmlXStreamBean = (XmlXStreamBean) xStream.fromXML(new File("src/main/resources/xmltest.xml"));
        System.out.println(xmlXStreamBean);
    }
}

class XmlXStreamBean {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Event;

    @Override
    public String toString() {
        return "XmlXStreamBean{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                ", Event='" + Event + '\'' +
                '}';
    }
}
