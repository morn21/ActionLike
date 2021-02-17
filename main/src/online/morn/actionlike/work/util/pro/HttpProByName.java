package online.morn.actionlike.work.util.pro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP处理工具别名处理
 * @author Morn4ever 2015-10-27
 */
class HttpProByName extends HttpProBasic{

	/**
	 * 构造方法
	 * @param request 请求对象
	 * @param response 响应对象
	 */
	protected HttpProByName(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	/**
	 * 获得请求协议(如:HTTP/1.1)
	 * @return 协议版本
	 */
	public String getProtocol(){
		return super.getRequest().getProtocol();
	}
	
	/**
	 * 获得统一资源定位器(例:url[?query#fragment])
	 * @return 统一资源定位器
	 */
	public String getUrl(){
		return super.getRequest().getRequestURL().toString();
	}
	
	/**
	 * 获得请求协议(例:scheme[://host:port/dir/urn?query#fragment])
	 * @return 协议内容
	 */
	public String getScheme(){
		return super.getRequest().getScheme();
	}
	
	/**
	 * 获得请求主机(例:[scheme://]host[:port/dir/urn?query#fragment])
	 * @return 主机名
	 */
	public String getHost(){
		return super.getRequest().getServerName();
	}
	
	/**
	 * 获得请求端口号(例:[scheme://host:]port[dir/urn?query#fragment])
	 * @return 端口号
	 */
	public int getPort(){
		return super.getRequest().getServerPort();
	}
	
	/**
	 * 获得统一资源标识符(例:[scheme://host:port]uri[?query#fragment])
	 * @return 统一资源标识符(该值以"/"开始)
	 */
	public String getUri(){
		return super.getRequest().getRequestURI();
	}
	
	/**
	 * 获得统一资源标识符的目录(例:[scheme://host:port]dir[urn?query#fragment])
	 * @return 统一资源标识符的目录(该值以"/"开始)
	 */
	public String getDir(){
		return super.getRequest().getContextPath();
	}
	
	/**
	 * 页面转发
	 * @param url
	 */
	public void forward(String url){
		try {
			super.getRequest().getRequestDispatcher(url).forward(super.getRequest(), super.getResponse());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 页面包含
	 * @param url
	 */
	public void include(String url){
		try {
			super.getRequest().getRequestDispatcher(url).include(super.getRequest(), super.getResponse());
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 重定向
	 * @param url
	 */
	public void redirect(String url){
		try {
			super.getResponse().sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得请求IP地址
	 * @return IP
	 */
	public String getIp(){
		return super.getRequest().getRemoteAddr();
	}
	
	/**
	 * 获得请求头信息
	 * @param name 请求头名
	 * @return 请求头内容
	 */
	public String getHeader(String name){
		return super.getRequest().getHeader(name);
	}
	
	/**
	 * 获得参数名列表
	 * @return
	 */
	public List<String> getParaNames(){
		List<String> paraNames = new ArrayList<String>();
		
		Enumeration<String> paramNames = super.getRequest().getParameterNames();
	    while(paramNames.hasMoreElements()) {
	    	String paraName = (String)paramNames.nextElement();
	    	paraNames.add(paraName);
	    }
	    
		return paraNames;
	}
	
	/**
	 * 获得参数
	 * @param name 参数名
	 * @return 参数内容(可能为null)
	 */
	public String getPara(String name){
		return super.getRequest().getParameter(name);
	}
	
	/**
	 * 设置属性
	 * @param name 属性名
	 * @param o 属性内容
	 */
	public void setAttr(String name, Object o){
		super.getRequest().setAttribute(name, o);
	}
	
	/**
	 * 获得属性内容
	 * @param name 属性名
	 * @return 属性内容
	 */
	public Object getAttr(String name){
		return super.getRequest().getAttribute(name);
	}
	
	/**
	 * 清除属性
	 * @param name 属性名
	 */
	public void clearAttr(String name){
		super.getRequest().removeAttribute(name);
	}
	
	/**
	 * 设置会话
	 * @param name 会话名
	 * @param o 会话内容
	 */
	public void setSess(String name, Object o){
		super.getRequest().getSession().setAttribute(name, o);
	}
	
	/**
	 * 获得会话内容
	 * @param name 会话名
	 * @return 会话内容
	 */
	public Object getSess(String name){
		return super.getRequest().getSession().getAttribute(name);
	}
	
	/**
	 * 清除会话
	 * @param name 会话名
	 */
	public void clearSess(String name){
		super.getRequest().getSession().removeAttribute(name);
	}
	
	/**
	 * 发送错误
	 * @param sc
	 */
	public void sendError(int sc){
		try {
			super.getResponse().sendError(sc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
