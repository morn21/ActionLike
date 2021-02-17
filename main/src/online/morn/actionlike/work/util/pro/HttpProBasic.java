package online.morn.actionlike.work.util.pro;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HTTP处理工具基础
 * @author Morn4ever 2015-10-27
 */
class HttpProBasic {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**
	 * 构造方法
	 * @param request 请求对象
	 * @param response 响应对象
	 */
	protected HttpProBasic(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		
		/*
		//测试路径:																					http://127.0.0.1:8080/think/asdsa
		Tester.print("常用request参数测试_Protocol: " + request.getProtocol());						//think_Tester:常用request参数测试_Protocol: HTTP/1.1
	    Tester.print("常用request参数测试_Scheme: " + request.getScheme());							//think_Tester:常用request参数测试_Scheme: http
	    Tester.print("常用request参数测试_Server Name: " + request.getServerName());					//think_Tester:常用request参数测试_Server Name: 127.0.0.1
	    Tester.print("常用request参数测试_Server Port: " + request.getServerPort());					//think_Tester:常用request参数测试_Server Port: 8080
	    //Tester.print("常用request参数测试_Server Info: " + getServletConfig().getServletContext().getServerInfo());
	    Tester.print("常用request参数测试_Remote Addr: " + request.getRemoteAddr());					//think_Tester:常用request参数测试_Remote Addr: 127.0.0.1
	    Tester.print("常用request参数测试_Remote Host: " + request.getRemoteHost());					//think_Tester:常用request参数测试_Remote Host: 127.0.0.1
	    Tester.print("常用request参数测试_Character Encoding: " + request.getCharacterEncoding());	//think_Tester:常用request参数测试_Character Encoding: null
	    Tester.print("常用request参数测试_Content Length: " + request.getContentLength());			//think_Tester:常用request参数测试_Content Length: -1
	    Tester.print("常用request参数测试_Content Type: "+ request.getContentType());				//think_Tester:常用request参数测试_Content Type: null
	    Tester.print("常用request参数测试_Auth Type: " + request.getAuthType());						//think_Tester:常用request参数测试_Auth Type: null
	    Tester.print("常用request参数测试_HTTP Method: " + request.getMethod());						//think_Tester:常用request参数测试_HTTP Method: GET
	    Tester.print("常用request参数测试_Path Info: " + request.getPathInfo());						//think_Tester:常用request参数测试_Path Info: null
	    Tester.print("常用request参数测试_Path Trans: " + request.getPathTranslated());				//think_Tester:常用request参数测试_Path Trans: null
	    Tester.print("常用request参数测试_Query String: " + request.getQueryString());				//think_Tester:常用request参数测试_Query String: null
	    Tester.print("常用request参数测试_Remote User: " + request.getRemoteUser());					//think_Tester:常用request参数测试_Remote User: null
	    Tester.print("常用request参数测试_Session Id: " + request.getRequestedSessionId());			//think_Tester:常用request参数测试_Session Id: null
	    Tester.print("常用request参数测试_Request URI: " + request.getRequestURI());					//think_Tester:常用request参数测试_Request URI: /think/asdsa
	    Tester.print("常用request参数测试_Servlet Path: " + request.getServletPath());				//think_Tester:常用request参数测试_Servlet Path: /asdsa
	    
	    
	    */
	    /*
	    Tester.print("常用request参数测试_Accept: " + request.getHeader("Accept"));
	    Tester.print("常用request参数测试_Host: " + request.getHeader("Host"));
	    Tester.print("常用request参数测试_Referer : " + request.getHeader("Referer"));
	    Tester.print("常用request参数测试_Accept-Language : " + request.getHeader("Accept-Language"));
	    Tester.print("常用request参数测试_Accept-Encoding : " + request.getHeader("Accept-Encoding"));
	    Tester.print("常用request参数测试_User-Agent : " + request.getHeader("User-Agent"));
	    Tester.print("常用request参数测试_Connection : " + request.getHeader("Connection"));
	    Tester.print("常用request参数测试_Cookie : " + request.getHeader("Cookie"));
	    */
	    //Tester.print("常用request参数测试_Created : " + session.getCreationTime());
	    //Tester.print("常用request参数测试_LastAccessed : " + session.getLastAccessedTime());
	}
	
	/**
	 * 获得请求对象
	 * @return 请求对象
	 */
	public HttpServletRequest getRequest(){
		return this.request;
	}
	
	/**
	 * 获得响应对象
	 * @return 响应对象
	 */
	public HttpServletResponse getResponse(){
		return this.response;
	}
	
	/**
	 * 获得会话
	 * @return 响应对象
	 */
	public HttpSession getSession(){
		return this.request.getSession();
	}
	
	/**
	 * 获得上下文对象
	 * @return 上下文对象
	 */
	public ServletContext getContext(){
		return this.getRequest().getServletContext();
	}
}
