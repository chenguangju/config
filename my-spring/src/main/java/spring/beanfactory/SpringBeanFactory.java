package spring.beanfactory;

import java.util.HashMap;

import spring.bean.BeanHashMap;
//基础Ioc，包含两个单例map
public class SpringBeanFactory {
//实例化Bean map，BeanHashMap初始化单例map
private  HashMap<String,Object> beanMap
=(HashMap<String, Object>) BeanHashMap.getBeanMap();
//bean定义map,BeanHashMap初始化单例map
private  HashMap<String,Object> beanDefinitionMap
=(HashMap<String, Object>) BeanHashMap.getBeanDefinitionMap();
	
	public  Object getBean(String name) {
		return beanMap.get(name);
	}
	
	public  Object beanDefinitionMap(String name) {
		return beanDefinitionMap.get(name);
	}

	public HashMap<String, Object> getBeanMap() {
		return beanMap;
	}

	public HashMap<String, Object> getBeanDefinitionMap() {
		return beanDefinitionMap;
	}



	


	
	
	
}
