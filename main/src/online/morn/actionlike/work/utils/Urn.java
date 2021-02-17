package online.morn.actionlike.work.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一资源命名处理
 * @author Morn4ever 2015-10-27
 */
public class Urn {
	/**
	 * 获得标准统一资源标识符
	 * @param uri 统一资源标识符
	 * @return 统一资源标识符
	 */
	public static String getStandardUrn(String urn){
		
		urn = "/" + urn;
		urn = urn.replaceAll("/{2,}", "/");//去除双斜杠
		
		//Tester.print("去除双斜杠后的统一资源命名:" + uri);
		
		return urn;
	}
	
	/**
	 * 获得统一资源标识符列表
	 * @param uri 统一资源标识符
	 * @return 统一资源标识符列表
	 */
	public static List<String> getUrnList(String urn){
		
		String[] urnArray = urn.split("/");
		
		ArrayList<String> urnList = new ArrayList<String>();
		for(String urnItem : urnArray){
			if(!urnItem.equals("")){
				urnList.add(urnItem);
			}
		}
		return urnList;
	}
}
