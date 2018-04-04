package spring;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import spring.bean.BeanHashMap;
import spring.bean.BeanInfo;
import spring.bean.InstanceBean;
import spring.beanfactory.SpringBeanFactory;

public class Dom4jForXml {

	public static  SpringBeanFactory resolveXml(String filePath) throws DocumentException {
		SAXReader reader= new SAXReader();
		//生成Document
		Document doc = reader.read(new File(filePath));
		//获取根
		Element rootElement = doc.getRootElement();
		//解析
		return listNode(rootElement);
	}
	
	
	//递归方法，一层一层去解析，这里的SpringBeanFactory是IOC容器，包含两个单例map一个是
	//bean定义map，用于存放解析xml结果的id与class映射，另一个是实例化map用于存放
	//实例化的bean的name与对象的映射，这里只是解析，只用到第一个map
	@SuppressWarnings("unchecked")
	private static  SpringBeanFactory listNode(Element rootElement) {
		List<Attribute> list=rootElement.attributes();
		SpringBeanFactory beanFactory=new SpringBeanFactory();
		Map beanDefinitionMap = beanFactory.getBeanDefinitionMap();
		//解析信息对象，只有两个属性一个id一个class
		BeanInfo beanInfo=new BeanInfo();
		for (Attribute attribute2 : list) {
			if(attribute2.getName().equals("id")) {
				beanInfo.setId(attribute2.getValue());
				beanDefinitionMap.put(attribute2.getValue(), beanInfo);
			}
			if(attribute2.getName().equals("class")) {
				beanInfo.setClazz(attribute2.getValue());
			}
		}
		//获取当前元素的子元素
		Iterator elementIterator = rootElement.elementIterator();
		while(elementIterator.hasNext()) {
			Element next = (Element) elementIterator.next();
			//递归解析
			listNode(next);
		}
		//返回的是ioc容器，已经包含了解析xml得到的bean信息
		return beanFactory;
		
	}
}
