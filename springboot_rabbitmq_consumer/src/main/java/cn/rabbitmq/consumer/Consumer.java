package cn.rabbitmq.consumer;

import java.util.Map;

import org.springframework.amqp.core.Message;

import com.rabbitmq.client.Channel;

import cn.rabbitmq.util.IDCache;
public interface Consumer {

	Map<String, String> map = IDCache.getMap();
	
	public void consume(Message s,Channel channel);
}
