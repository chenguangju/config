package cn.rabbitmq.entity;

public class MessageDetail {
	
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
	public MessageDetail(Object message, String uuid) {
		this.message = message;
		this.uuid = uuid;
	}
	public MessageDetail() {
	}
	
	

}
