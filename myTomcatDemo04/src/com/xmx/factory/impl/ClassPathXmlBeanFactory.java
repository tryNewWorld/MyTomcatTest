package com.xmx.factory.impl;

import com.xmx.factory.BeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlBeanFactory implements BeanFactory {
    private Map<String, Object> map = new HashMap<>();

    public ClassPathXmlBeanFactory() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("application.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            assert is != null;
            Document document = builder.parse(is);
            NodeList nodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String key = element.getAttribute("id");
                String clazz = element.getAttribute("class");
                Object value = Class.forName(clazz).newInstance();
                map.put(key, value);
            }
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String id = element.getAttribute("id");
                Object mainObj = map.get(id);
                NodeList childNodes = element.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node item = childNodes.item(j);
                    if(item.getNodeType() == Node.ELEMENT_NODE && "property".equals(item.getNodeName())) {
                        Element e = (Element) item;
                        String fieldName = e.getAttribute("name");
                        String key = e.getAttribute("ref");
                        Object obj = map.get(key);
                        Field field = mainObj.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        field.set(mainObj, obj);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        return map.get(beanName);
    }
}
