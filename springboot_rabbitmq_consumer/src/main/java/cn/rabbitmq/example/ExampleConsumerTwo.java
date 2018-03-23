package cn.rabbitmq.example;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import cn.rabbitmq.consumer.Consumer;
import cn.rabbitmq.entity.MessageDetail;
import cn.rabbitmq.util.FastJsonUtil;
@Component
public class ExampleConsumerTwo implements Consumer{
	//第一种情况见{@link ExampleConsumerOne}
	//这里如果有多个消费，监听不同的队列，实现Consumer接口，
	//@1 如果在一个类中，多个消费，不同的消费直接复制方法，并且命名不同，监听注解不同，即可实现多消费者。
	//@2 不是同一个类中，多个消费（一个类一个消费者），直接每个类实现Consumer接口即可
	//下面是同一个类中多个消费
	
	@Override
	//@RabbitListener(queues="listQueue")
	public void consume(Message s, Channel channel) {
		//当然这里可以自己定制，按生产者的规则来，如果不需要ACK就很简单了
		byte[] body = s.getBody();
		MessageDetail obj = FastJsonUtil.stringToMessage(body);
		String message = map.get(obj.getUuid());
		if (StringUtils.isBlank(message)) {
			map.put(obj.getUuid(), obj.getUuid());// 内存不够怎么办，定期清理
			try {
				//
				System.out.println("业务逻辑在这里");
				channel.basicAck(s.getMessageProperties().getDeliveryTag(), false);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					channel.basicReject(s.getMessageProperties().getDeliveryTag(),false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			try {
				channel.basicAck(s.getMessageProperties().getDeliveryTag(), false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	//@RabbitListener(queues="consumeMoney")
	public void consumeMoney(Message s, Channel channel) {
		//当然这里可以自己定制，按生产者的规则来，如果不需要ACK就很简单了
		byte[] body = s.getBody();
		MessageDetail obj = FastJsonUtil.stringToMessage(body);
		String message = map.get(obj.getUuid());
		if (StringUtils.isBlank(message)) {
			map.put(obj.getUuid(), obj.getUuid());// 内存不够怎么办，定期清理
			try {
				//
				System.out.println("业务逻辑在这里");
				channel.basicAck(s.getMessageProperties().getDeliveryTag(), false);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					channel.basicReject(s.getMessageProperties().getDeliveryTag(),false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			try {
				channel.basicAck(s.getMessageProperties().getDeliveryTag(), false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

}
