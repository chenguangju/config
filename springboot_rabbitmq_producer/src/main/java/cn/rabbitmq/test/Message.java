package cn.rabbitmq.test;

public class Message {
	
	private Object message;
	private String uuid;
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Message(Object message, String uuid) {
		this.message = message;
		this.uuid = uuid;
	}
	
	

}
