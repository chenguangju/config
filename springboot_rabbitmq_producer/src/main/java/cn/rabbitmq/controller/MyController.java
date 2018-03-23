package cn.rabbitmq.controller;


import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.rabbitmq.send.MessageSender;

@RestController
public class MyController {
	static AtomicInteger i=new AtomicInteger(0);
	@Autowired
    private MessageSender messageSender;
	
	@RequestMapping(value="/test")
	public void test(String test){
		synchronized (this) {
			System.err.println(i.incrementAndGet());
		}
		messageSender.send("test","topic","item");
	}
	
}
