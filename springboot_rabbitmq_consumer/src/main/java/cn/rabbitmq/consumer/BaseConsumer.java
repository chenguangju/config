package cn.rabbitmq.consumer;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.rabbitmq.client.Channel;

import cn.rabbitmq.entity.MessageDetail;
import cn.rabbitmq.util.FastJsonUtil;


public abstract class BaseConsumer implements Consumer{
	@Override
	@RabbitListener(queues="#{'${rabbitmq.listener.queue.name}'.split(',')}")
	//以下固定有两个参数，也可以只有message一个参数
	public void consume(Message s, Channel channel) {
		byte[] body = s.getBody();
		MessageDetail obj = FastJsonUtil.stringToMessage(body);
		String message = map.get(obj.getUuid());
		if (StringUtils.isBlank(message)) {
			map.put(obj.getUuid(), obj.getUuid());// 内存不够怎么办，定期清理
			try {
				//消费具体逻辑,子类实现
				logic(new String(body));
				//Delivery Tag 用来标识信道中投递的消息,RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag，
				//以便 Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。
				//RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增
				//basicAck 方法的第二个参数 multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认；如果为 true，
				//则额外将比第一个参数指定的 delivery tag 小的消息一并确认
				channel.basicAck(s.getMessageProperties().getDeliveryTag(), false);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					//当消费消息出现异常时，我们需要取消确认，这时我们可以使用 Channel 的 basicReject 方法。
					//第一个参数指定 delivery tag，第二个参数说明如何处理这个失败消息。
					//requeue 值为 true 表示该消息重新放回队列头，值为 false 表示放弃这条消息。
					//一般来说，如果是系统无法处理的异常，我们一般是将 requeue 设为 false，例如消息格式错误，再处理多少次也是异常。
					//调用第三方接口超时这类异常 requeue 应该设为 true。
					channel.basicReject(s.getMessageProperties().getDeliveryTag(),false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			try {
				//这里并不是出现异常，而是重复的消息是不会消费的，直接通知MQ删除
				channel.basicAck(s.getMessageProperties().getDeliveryTag(), false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected abstract void  logic(String message);

}
