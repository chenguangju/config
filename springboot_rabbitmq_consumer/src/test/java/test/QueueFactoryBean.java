package cn.rabbitmq.test;

import java.lang.reflect.Constructor;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.FactoryBean;

public class QueueFactoryBean implements FactoryBean<Queue>{
	 private  Class<?> queueClass;
	 	
	@Override
	public Queue getObject() throws Exception {
		@SuppressWarnings("unchecked")
		Constructor<Queue> constructor = (Constructor<Queue>) queueClass.getConstructor(String.class,boolean.class);
		return (Queue) constructor.newInstance("myQ",true);
	}

	@Override
	public Class<?> getObjectType() {
		return queueClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}


	public Class<?> getQueueClass() {
		return queueClass;
	}

	public void setQueueClass(Class<?> queueClass) {
		this.queueClass = queueClass;
	}
	
}
