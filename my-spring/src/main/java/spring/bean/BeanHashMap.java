package spring.bean;

import java.util.HashMap;
import java.util.Map;
//产生单例map类，这里用的是静态内部类
public class BeanHashMap {
	public static class Singleton{
		static Map<String,Object> beanDefinitionMap;
		static Map<String,Object> beanMap;
		static{
			beanDefinitionMap=new HashMap<String,Object>();
			beanMap=new HashMap<String,Object>();
		}
		public static Map<String,Object> getBeanDefinitionMap() {
			return beanDefinitionMap;
		}
		public static Map<String,Object> getBeanMap() {
			return beanMap;
		}
	}
	public static Map<String,Object> getBeanDefinitionMap(){
		return Singleton.getBeanDefinitionMap();
	}
	public static Map<String,Object> getBeanMap(){
		return Singleton.getBeanMap();
	}
}
