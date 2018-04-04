package spring.applicationcontext;


import spring.beanfactory.SpringBeanFactory;

public class SpringApplicationContext {
	
	
	private  SpringBeanFactory springBeanFactory;

	public SpringBeanFactory getSpringBeanFactory() {
		return springBeanFactory;
	}

	public void setSpringBeanFactory(SpringBeanFactory springBeanFactory) {
		this.springBeanFactory = springBeanFactory;
	}

	public  Object getBean(String name) {
		return springBeanFactory.getBean(name);
	}
}
