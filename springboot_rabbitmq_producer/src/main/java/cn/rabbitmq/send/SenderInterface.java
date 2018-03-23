package cn.rabbitmq.send;

public interface SenderInterface {

	public SendStatusMessage send(Object message,String exchangeName,String key);
}
