package online.morn.actionlike.work.util.pro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP处理工具
 * @author Morn4ever 2015-10-27
 */
public class HttpPro extends HttpProExtend{
	
	/**
	 * 构造方法
	 * @param request 请求对象
	 * @param response 响应对象
	 */
	public HttpPro(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		//this.debug();//调试
	}
			
	/**
	 * 调试
	 */
/*	private void debug(){
		Debug.getObject().print(" para:").print(super.getPara("para")).ln();
		Debug.getObject().print(" stringPara:").print(super.getStringPara("string")).ln();
		Debug.getObject().print(" intPara:").print(super.getIntPara("int")).ln();
		Debug.getObject().print(" longPara:").print(super.getLongPara("long")).ln();
		Debug.getObject().print(" booleanPara:").print(super.getBooleanPara("boolean")).ln();
		Debug.getObject().print(" floatPara:").print(super.getFloatPara("float")).ln();
		Debug.getObject().print(" doublePara:").print(super.getDoublePara("double")).ln();
		
		Tester.print(" protocol:"+ super.getProtocol());
		Tester.print(" url:" + super.getUrl());
		Tester.print(" scheme:" + super.getScheme());
		Tester.print(" host:" + super.getHost());
		Tester.print(" port:" + super.getPort());
		Tester.print(" uri:" + super.getUri());
		Tester.print(" dir:" + super.getDir());
		Tester.print(" urn:" + super.getUrn());
		Tester.print(" query:" + super.getQuery());
	}*/
}
