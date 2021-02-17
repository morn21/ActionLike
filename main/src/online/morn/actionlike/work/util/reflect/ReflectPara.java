package online.morn.actionlike.work.util.reflect;

/**
 * 反射参数
 * @author Morn4ever 2015-10-27
 */
public class ReflectPara {
	
	private Class<?> paraClass;
	private Object paraObject;
	
	public static ReflectPara newPara(Class<?> paraClass, Object paraObject){
		ReflectPara reflectPara = new ReflectPara();
		reflectPara.setParaClass(paraClass);
		reflectPara.setParaObject(paraObject);
		return reflectPara;
	}
	
	public Class<?> getParaClass() {
		return paraClass;
	}
	public void setParaClass(Class<?> paraClass) {
		this.paraClass = paraClass;
	}
	public Object getParaObject() {
		return paraObject;
	}
	public void setParaObject(Object paraObject) {
		this.paraObject = paraObject;
	}
}
