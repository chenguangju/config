package cn.rabbitmq.test;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.beans.factory.FactoryBean;

public class BindingFactoryBean implements FactoryBean<Binding>{
	 private  Class<?> bindingClass;
	 	
	@Override
	public Binding getObject() throws Exception {
		@SuppressWarnings("unchecked")
		Constructor<Binding> constructor =
		(Constructor<Binding>) bindingClass.getConstructor(String.class,DestinationType.class,
				String.class,String.class,Map.class);
		return (Binding) constructor.newInstance("myQ",DestinationType.QUEUE,"topic","item.*",null);
	}

	@Override
	public Class<?> getObjectType() {
		return bindingClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Class<?> getBindingClass() {
		return bindingClass;
	}

	public void setBindingClass(Class<?> bindingClass) {
		this.bindingClass = bindingClass;
	}


	
}
