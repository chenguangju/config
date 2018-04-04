package spring.bean;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import spring.beanfactory.SpringBeanFactory;


public class InstanceBean {

	
	public static void instance(SpringBeanFactory beanFactory) throws Exception {
		HashMap<String,Object> map 
		     = (HashMap<String, Object>) beanFactory.getBeanMap();
		HashMap<String, Object> beanDefinitionMap 
		     = (HashMap<String, Object>) beanFactory.getBeanDefinitionMap();
		//获取beanDefinitionMap中所有的值，及class集合用于实例化
		Set<Entry<String, Object>> entrySet = beanDefinitionMap.entrySet();
		//实例化所有的Bean
		instanceBeansAndInject(entrySet,false,map);
		//根据set方法注入
		instanceBeansAndInject(entrySet,true,map);
		
	}
	//实例化与注入，和spring 依赖注入不同
	private static void instanceBeansAndInject(Set<Entry<String, Object>> entrySet, boolean flag,
			HashMap<String,Object> map) throws Exception {
		//遍历class的值，利用反射进行实例化
		for (Entry<String, Object> entry : entrySet) {
			BeanInfo value = (BeanInfo) entry.getValue();
			String clazz = value.getClazz();
			Class<?> entryClazz = Class.forName(clazz);
			Object newInstance = entryClazz.newInstance();
			//flag为false只进行实例化，这里没有注入，实例化UserController与
			//UserService,但是UserController的userService的属性为null
			if(flag==false) {
				map.put(value.getId(), newInstance);
			}else {
				//flag为true进行依赖注入(根据set方法)
				Method[] methods = entryClazz.getMethods();
				for (Method method : methods) {
					String name = method.getName();
					//根据set方法进行注入
					if(name.startsWith("set")) {
						//获取要依赖的对象
						Object object = map.get(getBeanNameInMap(method.getParameters()));
						if(object==null) {
							continue;
						}
						//注入，执行set方法,注入后UserController的userService的属性
						//已经存在值
						method.invoke(newInstance, object);
						//在实例化bean的map，put进注入后的对象
						map.put(value.getId(), newInstance);
					}
				}
			}
			
		}
		
	}
	//这里利用反射获取，set方法的参数类型，转换为Bean的name
	private static String getBeanNameInMap(Parameter[] parameters) {
		String qianStr="";
		String houStr="";
		for (Parameter parameter : parameters) {
			Class<?> type = parameter.getType();
			houStr=type.getSimpleName().substring(1);
			qianStr=type.getSimpleName().substring(0, 1);
		}
		return qianStr.toLowerCase()+houStr;
	}

}
;