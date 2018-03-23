package cn.rabbitmq.config;

import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;

public interface RabbitConfig {

		//想自己自定义自己去实现,如果定义多个在自己的config按此类代码多写几套
		public  Queue queue();
		public Binding binding(AbstractExchange defaultExchange,Queue queue);
}
