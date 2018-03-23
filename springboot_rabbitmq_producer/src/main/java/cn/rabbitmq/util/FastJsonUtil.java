package cn.rabbitmq.util;

import com.alibaba.fastjson.JSON;

public class FastJsonUtil {

	public static String objectToString(Object obj){
		if(null!=obj){
			String jsonString = JSON.toJSONString(obj);
			return jsonString;
		}
		return null;
	}
}
