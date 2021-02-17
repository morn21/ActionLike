package online.morn.actionlike.work.util.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import online.morn.actionlike.work.test.Tester;

/**
 * 反射对象
 * @author Morn4ever 2015-10-27
 */
public class ReflectObject {
	
	private ReflectClass reflectClass;
	private Object reflect_object;
	
	private HashMap<String,Class<?>> setMethodParaHash;
	
	/**
	 * 构造方法
	 * @param reflectClassName 反射类名
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public ReflectObject(String paraName) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		this.reflectClass = new ReflectClass(paraName);
		this.reflect_object = this.reflectClass.newObject();
	}
	
	/**
	 * 构造方法
	 * @param reflectClass 反射类对象
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public ReflectObject(Class<?> paraClass) throws InstantiationException, IllegalAccessException{
		this.reflectClass = new ReflectClass(paraClass);
		this.reflect_object = this.reflectClass.newObject();
	}
	
	/**
	 * 构造方法
	 * @param paraReflectClass 反射类对象(自创建类型)
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public ReflectObject(ReflectClass paraReflectClass) throws InstantiationException, IllegalAccessException{
		this.reflectClass = paraReflectClass;
		this.reflect_object = this.reflectClass.newObject();
	}
	
	/**
	 * 获得对象
	 * @return
	 */
	public Object getObject(){
		return this.reflect_object;
	}
	
	/**
	 * 调用方法
	 * @param methodName
	 * @param paras
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public Object callMethod(String paraName, ReflectPara... reflectParaList) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		if(this.reflect_object != null){
			
			Class<?>[] paraClassList = new Class<?>[reflectParaList.length];
			Object[] paraObjectList = new Object[reflectParaList.length];
			
			for(int i = 0 ; i < reflectParaList.length ; i++){
				paraClassList[i] = reflectParaList[i].getParaClass();
				paraObjectList[i] = reflectParaList[i].getParaObject();
			}
			
			Method method = this.reflectClass.getMethod(paraName, paraClassList);
			
			if(method != null){
				return method.invoke(this.reflect_object, paraObjectList);
			}
		}
		
		return null;
	}
	
	/**
	 * 转换set方法名
	 * @param paraName
	 * @return
	 */
	private String switchSetMethodName(String paraName){
		//创建方法名字符串的容器 并根据字段名 转换出方法名
		StringBuffer setMethodNameBuff  = new StringBuffer("set");
		setMethodNameBuff.append(paraName.substring(0, 1).toUpperCase());
		setMethodNameBuff.append(paraName.substring(1, paraName.length()));
		return setMethodNameBuff.toString();
	}
	
	/**
	 * 查找set方法的参数类型
	 * @param paraName
	 * @return
	 */
	public Class<?> findSetMethodParaClass(String paraName){
		
		//初始化方法参数哈希
		if(this.setMethodParaHash == null){
			this.setMethodParaHash = new HashMap<String,Class<?>>();
			for(Method method : reflectClass.getMethods()){
				String methodName = method.getName();
				//如果是set方法 并且 有且只有一个参数 并且 没有被存储的方法名
				if(methodName.startsWith("set") && method.getParameterTypes().length == 1 && this.setMethodParaHash.get(methodName) == null){
					this.setMethodParaHash.put(methodName, method.getParameterTypes()[0]);
				}
				
			}
		}
		
		String setMethodName = this.switchSetMethodName(paraName);
		
		return this.setMethodParaHash.get(setMethodName);
	}
	
	/**
	 * 调用set方法
	 * @param paraName
	 * @param paraClass
	 * @param paraObject
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
	public void callSetMethod(String paraName, Class<?> paraClass, Object paraObject) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		
		String setMethodName = this.switchSetMethodName(paraName);
		
		Tester.print("		setMethodName:"+setMethodName + "	参数:"+ paraClass.getName());
		
		this.callMethod(setMethodName, ReflectPara.newPara(paraClass, paraObject));
		
	}
}
