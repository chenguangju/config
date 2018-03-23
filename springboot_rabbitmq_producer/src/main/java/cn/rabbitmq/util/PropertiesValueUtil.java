package cn.rabbitmq.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesValueUtil {
	
	@Value("${rabbitmq.exchange.type}")//交换机类型
	private String exchangeType;
	@Value("${rabbitmq.exchange.name}")//交换机名称
	private String exchangeName;
	@Value("${rabbitmq.durable}")//是否持久化（交换机和队列）
	private boolean durable;
	@Value("${rabbitmq.exchange.autoDelete}")//是否自动删除（交换机）
	private boolean autoDelete;
	
	@Value("${rabbitmq.queue.name}")//队列名称
	private String queueName;
	@Value("${rabbitmq.queue.routingkey}")//topic类型交换机routingkey
	private String topicKey;
	
	
	
	
	public String getExchangeType() {
		return exchangeType;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public boolean isDurable() {
		return durable;
	}
	public boolean isAutoDelete() {
		return autoDelete;
	}
	public String getQueueName() {
		return queueName;
	}
	public String getTopicKey() {
		return topicKey;
	}
	
	
	
	
	
	
	
	
	
	
	
}
