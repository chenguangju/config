package cn.rabbitmq.example;

import org.springframework.stereotype.Component;

import cn.rabbitmq.consumer.BaseConsumer;
@Component
public class ExampleConsumerOne extends BaseConsumer{
	//第一种情况如果一个工程只有一个消费者，则继承BaseConsumer直接写逻辑即可
	//第二种一个工程有多个消费，监听不同的队列，参见类{@link ExampleConsumerTwo}
	@Override
	protected void logic(String message) {
		System.out.println("666666666666666666666666");
	}

}
