package cn.rabbitmq.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IDCache {

	public static ConcurrentHashMap<String, String> map=new ConcurrentHashMap<>();
	
	public static Map<String,String> getMap(){
		return map;
	}
}
