package cn.rabbitmq.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesValueUtil {

	@Value("${rabbitmq.exchange.type}")
	private String exchangeType;
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	@Value("${rabbitmq.durable}")
	private boolean durable;
	@Value("${rabbitmq.exchange.autoDelete}")
	private boolean autoDelete;
	
	
	@Value("${rabbitmq.queue.name}")
	private String queueName;
	
	@Value("${rabbitmq.topic.queue.routingkey}")
	private String topicKey;
	@Value("${rabbitmq.direct.queue.routingkey}")
	private String directKey;
	
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
	public String getDirectKey() {
		return directKey;
	}
	
	
	
	
}
