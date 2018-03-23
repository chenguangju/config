package cn.rabbitmq.util;

import java.lang.reflect.Constructor;

import org.springframework.amqp.core.AbstractExchange;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class ExchangeFactory {

	public static AbstractExchange getExchange(String exchangeType,
			String exchangeName,boolean durable,boolean autoDelete){
		AbstractExchange obj=null;
		try {
			Class<?> clazz = Class.forName(exchangeType);
			Constructor<?> constructor = clazz.getConstructor(String.class, boolean.class, boolean.class);
			obj = (AbstractExchange)constructor.newInstance(exchangeName,durable,autoDelete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
