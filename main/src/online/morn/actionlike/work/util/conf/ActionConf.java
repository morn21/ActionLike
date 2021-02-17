package online.morn.actionlike.work.util.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件
 * @author Morn4ever 2015-10-27
 */
public class ActionConf {
	public static String CLASSROOT = "";
	public static String JSPROOT = "jsp";
	public static String ENCODING = "utf-8";
	public static String INDEX = "index"; 

	static {
		InputStream in = ActionConf.class.getClassLoader().getResourceAsStream("action.properties");
		Properties pro =  new Properties();
		
		try {
			pro.load(in);
			CLASSROOT = pro.getProperty("classroot");
			JSPROOT = pro.getProperty("jsproot");
			ENCODING = pro.getProperty("encoding");
			INDEX = pro.getProperty("index");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
