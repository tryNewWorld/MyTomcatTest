package com.xmx.myssm.factory.impl;

import com.xmx.myssm.exception.BeanFactoryException;
import com.xmx.myssm.factory.BeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlBeanFactory implements BeanFactory {
    private Map<String, Object> map = new HashMap<>();

    public ClassPathXmlBeanFactory(String path) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            assert is != null;
            Document document = builder.parse(is);
            NodeList nodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element item = (Element) nodeList.item(i);
                String id = item.getAttribute("id");
                String className = item.getAttribute("class");
                map.put(id, Class.forName(className).newInstance());
            }
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element item = (Element) nodeList.item(i);
                String id = item.getAttribute("id");
                Object mainObj = map.get(id);
                NodeList childNodes = item.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node node = childNodes.item(j);
                    if(node.getNodeType() == Node.ELEMENT_NODE && "property".equals(node.getNodeName())) {
                        Element element = (Element) node;
                        String fieldName = element.getAttribute("name");
                        String objId = element.getAttribute("rel");
                        Field field = mainObj.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        field.set(mainObj, map.get(objId));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeanFactoryException("bean factory create is fail...");
        }
    }

    @Override
    public Object getBean(String beanName) {
        return map.get(beanName);
    }
}
