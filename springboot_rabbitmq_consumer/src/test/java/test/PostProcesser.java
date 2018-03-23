package cn.rabbitmq.test;

import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
//@Component
public class PostProcesser implements BeanPostProcessor{
	
	private Object  defaultExchange;
	private Object  queue;
	private boolean flag=true;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(null!=defaultExchange && null!=queue){
			if(flag){
				getBinding((AbstractExchange)defaultExchange,"item",(Queue)queue);
				flag=false;
			}
			
		}
		if(beanName.equals("defaultExchange")){
			this.defaultExchange=bean;
			return bean;
		}
		if(beanName.equals("myFactory")){
			this.queue=bean;
			return bean;
		}
		return bean;
	}
	
	public Binding getBinding(AbstractExchange defaultExchange, String Key,  Queue queue) {
		if(defaultExchange instanceof TopicExchange){
			return BindingBuilder.bind(queue).to((TopicExchange)defaultExchange).with(Key);
		}
		if(defaultExchange instanceof FanoutExchange){
			return BindingBuilder.bind(queue).to((FanoutExchange)defaultExchange);
		}
		if(defaultExchange instanceof DirectExchange){
			return BindingBuilder.bind(queue).to((DirectExchange)defaultExchange).with(Key);
		}
		return null;
	}
}
