package online.morn.actionlike.work.util.reflect;

import java.lang.reflect.Method;

/**
 * 反射类
 * @author Morn4ever 2015-10-27
 */
public class ReflectClass {
	
	private Class<?> reflect_class;
	
	/**
	 * 构造方法
	 * @param reflectClassName
	 * @throws ClassNotFoundException 
	 */
	public ReflectClass(String paraName) throws ClassNotFoundException{
		this.reflect_class = Class.forName(paraName);
	}
	
	/**
	 * 构造方法
	 * @param reflectClass
	 */
	public ReflectClass(Class<?> paraClass){
		this.reflect_class = paraClass;
	}
	
	/**
	 * 创建对象
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Object newObject() throws InstantiationException, IllegalAccessException{
		if(this.reflect_class != null){
			return this.reflect_class.newInstance();
		}
		return null;
	}
	
	/**
	 * 获得方法
	 * @param name
	 * @param parameterTypes
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public Method getMethod(String paraName, Class<?>... paraClassList) throws SecurityException, NoSuchMethodException{
		if(this.reflect_class != null){
			return this.reflect_class.getMethod(paraName, paraClassList);
		}
		return null;
	}
	
	/**
	 * 获得方法集合
	 * @return
	 */
	public Method[] getMethods(){
		return this.reflect_class.getMethods();
	}
}
