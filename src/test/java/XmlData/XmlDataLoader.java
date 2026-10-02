package XmlData;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class XmlDataLoader {

    public static <T> Map<String, T> loadData(
            String filePath,
            Class<T> clazz) {

        Map<String, T> dataMap = new HashMap<>();

        try {

            File file = new File(filePath);

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document document =
                    builder.parse(file);

            document.getDocumentElement().normalize();

            NodeList nodeList =
                    document.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String dataSetName =
                            element.getNodeName();

                    T object =
                            clazz.getDeclaredConstructor()
                                    .newInstance();

                    Field[] fields =
                            clazz.getDeclaredFields();

                    for (Field field : fields) {

                        NodeList fieldNode =
                                element.getElementsByTagName(
                                        field.getName()
                                );

                        if (fieldNode.getLength() > 0) {

                            field.setAccessible(true);

                            field.set(
                                    object,
                                    fieldNode.item(0)
                                            .getTextContent()
                            );
                        }
                    }

                    dataMap.put(dataSetName, object);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return dataMap;
    }
}