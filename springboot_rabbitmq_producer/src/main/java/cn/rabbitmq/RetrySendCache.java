package cn.rabbitmq;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.rabbitmq.send.MessageSender;
import cn.rabbitmq.send.SendStatusMessage;


public class RetrySendCache {
	private MessageSender messageSender;
	private ConcurrentHashMap<String,MessageWithTime> map=new ConcurrentHashMap();
	private String exchangeName;
	private String key;

	public int getSize(){
		return map.size();
	}
	
	public RetrySendCache(){
		startRetry();
	}
	
	private static class MessageWithTime{
		private long time;
		private Object message;
		public MessageWithTime(long time,Object message){
			this.time=time;
			this.message=message;
		}
		public long getTime() {
			return time;
		}
		public Object getMessage() {
			return message;
		}
		
	}
	public void setSenderInfo(MessageSender messageSender, String exchangeName, String key){
		this.messageSender=messageSender;
		this.exchangeName=exchangeName;
		this.key=key;
	}
	
	public void put(String id,Object message){
		map.put(id, new MessageWithTime(System.currentTimeMillis(), message));
	}
	
	public void remove(String id){
		map.remove(id);
	}
	
	public void startRetry(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(60*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for(String key  : map.keySet()){
						long now=System.currentTimeMillis();
						MessageWithTime messageWithTime=map.get(key);
						//超过三分钟没有ack直接删除本地缓存的message,重试两次
						if(messageWithTime.getTime()+3*60*1000<=now){
							remove(key);
						}else if(messageWithTime.getTime()+60*1000<=now){
							SendStatusMessage message = messageSender.send(messageWithTime.getMessage(),exchangeName,key);
							if(message.isFlag()){
								remove(key);
							}
						}
					}
				}
			}
		}).start();
		
		/*newFixedThreadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(60*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for(String key  : map.keySet()){
						long now=System.currentTimeMillis();
						MessageWithTime messageWithTime=map.get(key);
						//超过三分钟没有ack直接删除本地缓存的message
						if(messageWithTime.getTime()+3*60*1000<=now){
							remove(key);
						}else if(messageWithTime.getTime()+60*1000<=now){
							SendStatusMessage message = messageSender.send(messageWithTime.getMessage());
							if(message.isFlag()){
								remove(key);
							}
						}
					}
				}
			}
		});*/
	}
}
