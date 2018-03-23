package cn.rabbitmq.example;

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

import cn.rabbitmq.config.RabbitConfig;
import cn.rabbitmq.config.DefaultRabbitMQConfig;
import cn.rabbitmq.util.PropertiesValueUtil;
@Configuration
public class ExampleRabbitConfig extends DefaultRabbitMQConfig implements RabbitConfig {
	
	@Bean
	public Queue queue() {
		 return new Queue("queue1", propUtil.isDurable());
	}
	
	
	@Bean
	public Binding binding(AbstractExchange defaultExchange, Queue queue) {
		if(defaultExchange!=null){
			return super.getBinding(defaultExchange,propUtil.getTopicKey(),propUtil.getDirectKey(),queue);
		}
		return null;
	}


}
