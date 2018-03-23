package cn.rabbitmq.send;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.rabbitmq.RetrySendCache;
import cn.rabbitmq.test.Message;
import cn.rabbitmq.util.FastJsonUtil;
import cn.rabbitmq.util.PropertiesValueUtil;
@Component
public class MessageSender  implements ConfirmCallback,SenderInterface{
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	static RetrySendCache cache=new RetrySendCache();
	
	
	@PostConstruct
    public void init() {
		rabbitTemplate.setConfirmCallback(this);
    }

	public SendStatusMessage send(Object message,String exchangeName,String key){
		try {
			cache.setSenderInfo(this,exchangeName,key);
			String uuid = UUID.randomUUID().toString();
			cache.put(uuid, message);
			Message msg=new Message(message,uuid);
			rabbitTemplate.convertAndSend(exchangeName, key, FastJsonUtil.objectToString(msg), new CorrelationData(uuid));
		} catch (AmqpException e) {
			e.printStackTrace();
			return new SendStatusMessage(false, "");
		}
		
		return new SendStatusMessage(true, "");
		
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) { 
			System.err.println("发送失败------: " + cause);
		}else{
			cache.remove(correlationData.getId());
		}
		
	}
}
