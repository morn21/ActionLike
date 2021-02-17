package online.morn.actionlike.work.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json工具
 * @author Morn4ever 2015-10-29
 */
public class Json {
	
	/**
	 * 使用递归 将对象转换为json字符串
	 * @param sendObject
	 * @return
	 */
	public static String toJson(Object sendObject){
		
		//返回的字符串构造器
		StringBuilder returnBuilder = new StringBuilder();
		
		//如果类型类 List
		if(sendObject instanceof List){
			List<?> list = (List<?>)sendObject;
			returnBuilder.append("[");
			int no = 0;//行标
			for(Object getObject : list){
				if(no > 0){
					returnBuilder.append(",");
				}
				returnBuilder.append(toJson(getObject));
				no++;
			}
			returnBuilder.append("]");
		}
		//如果类型类 Map
		else if(sendObject instanceof Map){
			Map<?,?> map = (Map<?,?>)sendObject;
			returnBuilder.append("{");
			int no = 0;//行标
			for(Object key : map.keySet()){
				if(no > 0){
					returnBuilder.append(",");
				}
				Object getObject = map.get(key);
				returnBuilder.append("\"");
				returnBuilder.append(key);
				returnBuilder.append("\":");
				returnBuilder.append(toJson(getObject));
				no++;
			}
			returnBuilder.append("}");
		}
		//如果类型类 Number Boolean Character String Date
		else if(sendObject instanceof Number || sendObject instanceof Boolean 
				|| sendObject instanceof Character ||sendObject instanceof String 
				|| sendObject instanceof Date){
			returnBuilder.append("\"");
			returnBuilder.append(sendObject.toString());
			returnBuilder.append("\"");
		}
		//如果类型类为null
		else if(sendObject == null){
			returnBuilder.append("null");
		}
		//如果类型类 不是以上类型 那就先当做模型对象来处理吧
		else{
			returnBuilder.append("{");
			int no = 0;//行标
			for(Method method : sendObject.getClass().getMethods()){
				String methodName = method.getName();
				if((methodName.startsWith("get") || methodName.startsWith("is") ) && !methodName.equals("getClass")){
					try {
						//Tester.print("methodName:" + methodName);
						Object getObject = method.invoke(sendObject);
						if(no > 0){
							returnBuilder.append(",");
						}
						returnBuilder.append("\"");
						if(methodName.startsWith("get")){
							returnBuilder.append(methodName.substring(3,4).toLowerCase());
							returnBuilder.append(methodName.substring(4,methodName.length()));	
						}
						else if(methodName.startsWith("is")){
							returnBuilder.append(methodName.substring(2,3).toLowerCase());
							returnBuilder.append(methodName.substring(3,methodName.length()));
						}
						returnBuilder.append("\":");
						returnBuilder.append(toJson(getObject));
						no++;
					} catch (IllegalArgumentException e) {
						//e.printStackTrace();
					} catch (IllegalAccessException e) {
						//e.printStackTrace();
					} catch (InvocationTargetException e) {
						//e.printStackTrace();
					}
				}
			}
			returnBuilder.append("}");
		}
		return returnBuilder.toString();
	}
	
	/**
	 * 将json字符串转换为对象
	 * @param jsonString
	 * @return
	 */
	public static Map<String,String> toMap(String jsonString){
		//字符串 为 null 
		if(jsonString == null || jsonString.equals("") || jsonString.equals("null")){
			return null;
		}
		
		Map<String,String> returnMap = new HashMap<String,String>();//要返回的Map
		
		String innerJsonString = "";//不带{}的json字符串
		int bracketL = jsonString.indexOf("{");//左大括弧坐标
		if(bracketL != -1){//如果找到左大括弧坐标
			int bracketR = jsonString.indexOf("}");//右大括弧坐标
			if(bracketR != -1){//如果找到右大括弧坐标
				if(bracketR - bracketL > 1){
					innerJsonString = jsonString.substring(bracketL + 1, bracketR);
				}
			}
		}
		
		for(String item : innerJsonString.split(",")){//用,分割出并遍历内存json字符串数组
			String[] nameValue = item.split(":");//用：分割出json名和值
			if(nameValue.length > 1){
				String name = nameValue[0].replaceAll("\"|'", "");
				String value = nameValue[1].replaceAll("\"|'", "");
				returnMap.put(name, value);
			}
		}
		return returnMap;
	}
	
	/**
	 * 将数组字符串转换为对象
	 * @param arrayString
	 * @return
	 */
	public static List<String> toList(String arrayString){
		
		//字符串 为 null 
		if(arrayString == null || arrayString.equals("") || arrayString.equals("null")){
			return null;
		}
		
		List<String> returnList = new ArrayList<String>();//要返回的List
		
		String innerArray = "";//不带[]的Array字符串
		int bracketL = arrayString.indexOf("[");//左大括弧坐标
		if(bracketL != -1){//如果找到左大括弧坐标
			int bracketR = arrayString.indexOf("]");//右大括弧坐标
			if(bracketR != -1){//如果找到右大括弧坐标
				if(bracketR - bracketL > 1){
					innerArray = arrayString.substring(bracketL + 1, bracketR);
				}
			}
		}
		
		for(String paraString : innerArray.split(",")){//用,分割出并遍历内存array字符串数组
			String para = paraString.replaceAll("\"|'", "");
			if(!para.equals("")){//不为空就添加
				returnList.add(para);
			}
		}
		return returnList;
	}
	
	/**
	 * 使用递归 将json字符串转换为对象
	 * @return
	 *//*
	public static Object toObject(String jsonString){
		jsonString = "{\"50\":100,\"arr\":[\"123\",{\"\":key,},\"1111\"]}";
		
			int da = jsonString.indexOf("{");
		int zhong = jsonString.indexOf("[");
		
		Tester.print("da:" + da);
		Tester.print("zhong:" + zhong);
		
		if(da < zhong){
			int da = jsonString.indexOf("{");
			int zhong = jsonString.indexOf("[");
		}
		else{
			
		}
		
		
		boolean start = false;
		boolean subStart = false;
		Tester.print("jsonString.length():"+jsonString.length());
		for(int i = 0 ; i < jsonString.length() ; i++){
			char aChar = jsonString.charAt(i);
			if(aChar == '{'){
				new HashMap<String, String>();
				start = true;
			}
			else if(aChar == '['){
				new ArrayList<String>();
				start = true;
			}
			
			if(start){
				if(aChar == '{'){
				}
				else if(aChar == '['){
				}
			}
			
			Tester.print(aChar);
		}
		return new Object();
	}*/
}
