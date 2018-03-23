package cn.rabbitmq.config;

import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.rabbitmq.util.ExchangeFactory;
import cn.rabbitmq.util.PropertiesValueUtil;
@Configuration

public  class DefaultRabbitMQConfig {
	
	@Autowired
	public PropertiesValueUtil propUtil;
	
	@Autowired
	private ExchangeFactory factory;

	@SuppressWarnings("static-access")
	@Bean
	public AbstractExchange defaultExchange() {
		return factory.getExchange(propUtil.getExchangeType(),
				propUtil.getExchangeName(),propUtil.isDurable()
				,propUtil.isAutoDelete());
	}
	
	
	@Bean
	public Queue defaultQueue() {
	    return new Queue(propUtil.getQueueName(), propUtil.isDurable());
	}
	
	
	@Bean
	public Binding defaultBinding(AbstractExchange defaultExchange,Queue defaultQueue) {
		if(defaultExchange!=null){
			return getBinding(defaultExchange,propUtil.getTopicKey(),propUtil.getDirectKey(),defaultQueue);
		}
	    return null;
	}
	
	
	
	public Binding getBinding(AbstractExchange defaultExchange, String topicKey, String drecitKey, Queue queue) {
		if(defaultExchange instanceof TopicExchange){
			return BindingBuilder.bind(queue).to((TopicExchange)defaultExchange).with(topicKey);
		}
		if(defaultExchange instanceof FanoutExchange){
			return BindingBuilder.bind(queue).to((FanoutExchange)defaultExchange);
		}
		if(defaultExchange instanceof DirectExchange){
			return BindingBuilder.bind(queue).to((DirectExchange)defaultExchange).with(drecitKey);
		}
		return null;
	}
	
}
