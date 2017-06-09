package com.chinasoft.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
/**
 * request请求参数工具类
 * @author Administrator
 *
 * @param <T>
 */
public class RequestUtil<T> {
	HttpServletRequest req;

	
	public RequestUtil(HttpServletRequest req) {
		super();
		this.req = req;
	}
	public  String getString(String key){
		String value=req.getParameter(key);
		if(value==null){
			return "";
		}
		return value;
	}
	public  int getInt(String key){
		String value=req.getParameter(key);
		if(value!=null && !value.equals("") ){
			return Integer.valueOf(value);
		}
		return 0;
	}
	
	/**
	 * 通过传入相应获取的参数数组，得到相应的map对象
	 * @param str
	 * @return
	 */
	public Map<String,String> getMap(String[] str){
		Map<String,String> map=new HashMap<String,String>();
		for (String key : str) {
			String value=getString(key);
			map.put(key, value);
		}
		return map;
	}
	/**
	 * 将前端传入的所有值转换为bean对象
	 * @param t
	 * @return
	 */
	
	public  T toBean(Class t){
		JSONObject obj=new JSONObject();
		//获取前台传入的所有参数的name属性
		Enumeration enums=req.getParameterNames();
		while(enums.hasMoreElements()){
			//获取其中的某一个name属性
			String key=enums.nextElement().toString();
			String value=getString(key);
			obj.put(key, value);
		}
		return (T) JSONObject.toBean(obj, t);
	}
	/**
	 * 根据想要获得name值，并将其转换为bean对象
	 * @param t
	 * @param str
	 * @return
	 */
	public T toBean(Class t,String[] str){	
		JSONObject obj=new JSONObject();
		for (String key : str) {
			String value=getString(key);
			if(isNumeric(value)){
				obj.put(key, Integer.valueOf(value));
			}else {
				obj.put(key,value);
			}
						
		}
		return (T) JSONObject.toBean(obj, t);
	}
	
	public  boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){	   
			   if (!Character.isDigit(str.charAt(i))){
			    return false;
			   }
		  }
		  return true;
	}
}
