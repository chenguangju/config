package spring.listener;

import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.DocumentException;

import spring.Dom4jForXml;
import spring.applicationcontext.SpringApplicationContext;
import spring.bean.InstanceBean;
import spring.beanfactory.SpringBeanFactory;

public class SpringListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		try {
			//获取web.xml的配置为初始化参数，得到需要加载的xml的绝对路径
			String filePath=getFilePath("configLoaction",sce);
			//最最重要的方法容器加载初始化、实例化就靠它
			refresh(filePath,sce);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void refresh(String filePath,ServletContextEvent sce) throws Exception {
		//加载xml与初始化IOC
		SpringBeanFactory beanFactory = Dom4jForXml.resolveXml(filePath);
		//实例化bean与依赖注入
		InstanceBean.instance(beanFactory);
		//设置Context与beanFactory
		initContext(beanFactory,sce);
	}

	private void initContext(SpringBeanFactory beanFactory, ServletContextEvent sce) {
		SpringApplicationContext context=new SpringApplicationContext();
		//设置SpringApplicationContext的SpringBeanFactory属性
		context.setSpringBeanFactory(beanFactory);
		ServletContext servletContext = sce.getServletContext();
		//把SpringApplicationContext设置到servlet上下文中
		servletContext.setAttribute("SpringApplicationContext", context);
	}

	private String getFilePath(String name,ServletContextEvent sce) {
		String config = sce.getServletContext().getInitParameter(name);
		URL url = null;
		if(config.startsWith("classpath")) {
			url = SpringListener.class.getClassLoader().getResource(config.split(":")[1]);
		}else {
			url = SpringListener.class.getClassLoader().getResource(config);
		}
		return url.getPath();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
