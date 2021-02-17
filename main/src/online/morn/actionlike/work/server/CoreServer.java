package online.morn.actionlike.work.server;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import online.morn.actionlike.work.test.Tester;
import online.morn.actionlike.work.util.conf.ActionConf;
import online.morn.actionlike.work.util.pro.HttpPro;
import online.morn.actionlike.work.util.reflect.ReflectObject;
import online.morn.actionlike.work.utils.Urn;

/**
 * 核心服务
 * @author Morn4ever 2015-10-27
 */
public class CoreServer {
	
	private HttpPro httpPro;

	public CoreServer(HttpPro httpPro){
		this.httpPro = httpPro;
	}
	
	/**
	 * 转换基础数据类型
	 * @param typeClass
	 * @param value
	 * @return
	 */
	private Object switchBasicDataType(Class<?> typeClass, String value){
		if(typeClass == int.class || typeClass == Integer.class){
			int object = 0;
			try {
				object = Integer.parseInt(value);
			} catch (NumberFormatException e) {}
			return object;
		}
		else if(typeClass == String.class){
			return value;
		}
		else if(typeClass == float.class || typeClass == Float.class){
			float object = 0;
			try {
				object = Float.parseFloat(value);
			} catch (NumberFormatException e) {}
			return object;									
		}
		else if(typeClass == boolean.class || typeClass == Boolean.class){
			return Boolean.parseBoolean(value);
		}
		else if(typeClass == char.class || typeClass == Character.class){
			char object = 0;
			try{
				object = value.charAt(0);
			} catch(StringIndexOutOfBoundsException e){}
			return object;
		}
		else if(typeClass == byte.class || typeClass == Byte.class){
			byte object = 0;
			try {
				object = Byte.parseByte(value);
			} catch (NumberFormatException e) {}
			return object;
		}
		else if(typeClass == short.class || typeClass == Short.class){
			short object = 0;
			try {
				object = Short.parseShort(value);
			} catch (NumberFormatException e) {}
			return object;
		}
		else if(typeClass == long.class || typeClass == Long.class){
			long object = 0;
			try {
				object = Long.parseLong(value);
			} catch (NumberFormatException e) {}
			return object;
		}
		else if(typeClass == double.class || typeClass == Double.class){
			double object = 0;
			try {
				object = Double.parseDouble(value);
			} catch (NumberFormatException e) {}
			return object;
		}
		
		return null;
	}
	
	/**
	 * 继续运行
	 * @param httpPro
	 * @return
	 */
	public boolean continueRun(){
		//获得请求 统一资源命名
		String requestUrn = this.httpPro.getUrn();
		
		//如果存在.后缀名
		if(requestUrn.indexOf(".") != -1){
			return true;
		}
		
		//获得标准 请求的统一资源命名
		String standardRequestUrn = Urn.getStandardUrn(requestUrn);
		
		List<String> urnList = Urn.getUrnList(standardRequestUrn);
		
		//如果标准请求命名源过小 就增加一个长度
		if(urnList.size() == 1){
			urnList.add(ActionConf.INDEX);
		}
		
		//请求包名列表
		List<String> packageList = new ArrayList<String>();
		for(int i = 0 ; i < urnList.size() - 2 ; i++){
			packageList.add(urnList.get(i));
			//urnList.remove(i);
		}
		
		//请求类名哈希
		HashMap<String,String> callHash = new HashMap<String,String>();
		
		//拼接类名
		StringBuilder classNameBuilder = new StringBuilder();
		String className = urnList.get(urnList.size() - 2);
		classNameBuilder.append(className.substring(0, 1).toUpperCase());
		classNameBuilder.append(className.substring(1, className.length()));
		classNameBuilder.append("Action");
		callHash.put("class", classNameBuilder.toString());
		callHash.put("function", urnList.get(urnList.size() - 1));
		
		//拼接类路径
		StringBuilder classPathBuilder = new StringBuilder(ActionConf.CLASSROOT);
		for(String aPackageName : packageList){
			classPathBuilder.append(".").append(aPackageName);
		}
		classPathBuilder.append(".").append(callHash.get("class"));
		
		Tester.print("类名:" + classPathBuilder.toString());
		
		
		//要调用的类对象
		ReflectObject callObject = null;
		try {
			callObject = new ReflectObject(classPathBuilder.toString());
		} catch (ClassNotFoundException e) {//类对象没有找到
			//e.printStackTrace();
		} catch (InstantiationException e) {//类实例化异常(类没有找到)
			e.printStackTrace();
		} catch (IllegalAccessException e) {//安全权限异常，如:调用了无权限访问的类
			e.printStackTrace();
		}
		
		if(callObject != null){
		
			//设置 处理方法
			try {
				callObject.callSetMethod("httpPro", HttpPro.class, this.httpPro);
			} catch (SecurityException e) {//安全权限异常，如:调用了无权限访问方法
				e.printStackTrace();
			} catch (IllegalArgumentException e) {//非法参数异常
				e.printStackTrace();
			} catch (NoSuchMethodException e) {//方法调用异常(方法没有找到)
				//e.printStackTrace();
			} catch (IllegalAccessException e) {//安全权限异常，如:调用了无权限访问的类
				e.printStackTrace();
			} catch (InvocationTargetException e) {//调用目标异常
				e.printStackTrace();
			}
			
			//遍历请求的参数名
			for(String paraName : this.httpPro.getParaNames()){
				Class<?> paraClass = callObject.findSetMethodParaClass(paraName);
				
				//找到对应的set方法
				if(paraClass != null){
					
					Object paraObject = this.switchBasicDataType(paraClass, this.httpPro.getStringPara(paraName));
					
					//转换出了对应的参数值
					if(paraObject != null){
						try {
							callObject.callSetMethod(paraName, paraClass, paraObject);
						} catch (SecurityException e) {//安全权限异常，如:调用了无权限访问方法
							e.printStackTrace();
						} catch (IllegalArgumentException e) {//非法参数异常
							e.printStackTrace();
						} catch (NoSuchMethodException e) {//方法调用异常(方法没有找到)
							e.printStackTrace();
						} catch (IllegalAccessException e) {//安全权限异常，如:调用了无权限访问的类
							e.printStackTrace();
						} catch (InvocationTargetException e) {//调用目标异常
							e.printStackTrace();
						}
					}
				}
			}
			
			//调用执行方法
			String result = null;
			try {
				result = (String)callObject.callMethod(callHash.get("function"));
			} catch (SecurityException e) {//安全权限异常，如:调用了无权限访问方法
				e.printStackTrace();
			} catch (IllegalArgumentException e) {//非法参数异常
				e.printStackTrace();
			} catch (NoSuchMethodException e) {//方法调用异常(方法没有找到)
				e.printStackTrace();
			} catch (IllegalAccessException e) {//安全权限异常，如:调用了无权限访问的类
				e.printStackTrace();
			} catch (InvocationTargetException e) {//调用目标异常
				e.printStackTrace();
			}
			if(result == null){
				return false;
			}
			else{
				if(result.equals("continue")){
					Tester.print("结果为:continue");
				}
				else{
					Tester.print("结果为:" + result);
					return false;
				}
			}
		}
		
		
		//类执行完毕
		Tester.print("那我们就找jsp吧");
		
		//计算jsp的路径
		StringBuilder jspNameBuilder = new StringBuilder(ActionConf.JSPROOT);
		jspNameBuilder.append(standardRequestUrn);
		jspNameBuilder.append(".jsp");
		
		Tester.print("请求的jsp:" + jspNameBuilder.toString());
		this.httpPro.forward(jspNameBuilder.toString());
		
		return false;
	}
}
