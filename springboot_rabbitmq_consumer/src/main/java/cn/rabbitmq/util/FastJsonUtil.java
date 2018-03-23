package cn.rabbitmq.util;



import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.rabbitmq.entity.MessageDetail;

public class FastJsonUtil {

	public static String objectToString(Object obj){
		if(null!=obj){
			String jsonString = JSON.toJSONString(obj);
			return jsonString;
		}
		return null;
	}
	
	public static Object stringToObject(String msg){
		if(!StringUtils.isBlank(msg)){
			Object obj = JSON.parse(msg);
			return obj;
		}
		return null;
	}
	
	public static MessageDetail stringToMessage(byte[] msg){
		if(null!=msg && msg.length>0){
			MessageDetail message = JSON.parseObject(msg, MessageDetail.class);
			return message;
		}
		return null;
	}
}
