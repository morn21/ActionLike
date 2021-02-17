package online.morn.actionlike.work.util.pro;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.morn.actionlike.work.util.conf.ActionConf;
import online.morn.actionlike.work.utils.Json;

/**
 * HTTP处理工具的扩展
 * @author Morn4ever 2015-10-27
 */
class HttpProExtend extends HttpProByName {

	/**
	 * 构造方法
	 * @param request
	 * @param response
	 */
	protected HttpProExtend(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * 设置编码
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		try {
			super.getRequest().setCharacterEncoding(encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		super.getResponse().setCharacterEncoding(encoding);
	}

	/**
	 * 获得统一资源命名(例:[scheme://host:port/dir]urn[?query#fragment])
	 * @return 统一资源命名(该值以"/"开始)
	 */
	public String getUrn() {
		// String urn =
		// super.getRequest().getServletPath();//这种方式如果请求"/"会出现"/index.jsp"的字样

		String uri = super.getUri();
		String dir = super.getDir();
		String urn = uri.replaceFirst(dir, "");

		if (urn.endsWith("/")) {// 这时候去找首页代替
			urn += ActionConf.INDEX;
		}

		return urn;
	}

	/**
	 * 获得查询内容(例:[scheme://host:port/dir/urn?]query[#fragment])
	 * @return 查询内容(保证不为null)
	 */
	public String getQuery() {
		String query = super.getRequest().getQueryString();
		if (query == null) {
			return "";
		}
		return query;
	}
	
	/**
	 * 获得WebRoot的绝对路径
	 * @return WebRoot的绝对路径
	 */
	public String getWebRoot(String url){
		return super.getContext().getRealPath(url);
	}
	
	/**
	 * 获得WebRoot的绝对路径(无参)
	 * @return WebRoot的绝对路径
	 */
	public String getWebRoot(){
		return this.getWebRoot("/");
	}

	/**
	 * 获得布尔参数
	 * @param name 参数名
	 * @return 参数内容(保证不为null)
	 */
	public boolean getBooleanPara(String name) {
		String stringPara = this.getStringPara(name);
		return Boolean.parseBoolean(stringPara);
	}

	/**
	 * 获得字节参数
	 * @param name 参数名
	 * @return 参数内容(保证不为null)
	 */
	public byte getBytePara(String name) {
		byte value = 0;
		String stringPara = this.getStringPara(name);
		try {
			value = Byte.parseByte(stringPara);
		} catch (NumberFormatException e) {}
		return value;
	}

	/**
	 * 获得短整形参数
	 * @param name 参数名
	 * @return 参数内容(保证不为null)
	 */
	public short getShortPara(String name) {
		short value = 0;
		String stringPara = this.getStringPara(name);
		try {
			value = Short.parseShort(stringPara);
		} catch (NumberFormatException e) {}
		return value;
	}

	/**
	 * 获得整形参数
	 * @param name 参数名
	 * @return 参数内容(保证不为null)
	 */
	public int getIntPara(String name) {
		int value = 0;
		String stringPara = this.getStringPara(name);
		try {
			value = Integer.parseInt(stringPara);
		} catch (NumberFormatException e) {}
		return value;
	}

	/**
	 * 获得长整形参数
	 * @param name 参数名
	 * @return 参数内容(保证不为null)
	 */
	public long getLongPara(String name) {
		long value = 0;
		String stringPara = this.getStringPara(name);
		try {
			value = Long.parseLong(stringPara);
		} catch (NumberFormatException e) {}
		return value;
	}

	/**
	 * 获得单精度浮点数参数
	 * @param name 参数名
	 * @return 参数内容(保证不为null)
	 */
	public float getFloatPara(String name) {
		float value = 0;
		String stringPara = this.getStringPara(name);
		try {
			value = Float.parseFloat(stringPara);
		} catch (NumberFormatException e) {}
		return value;
	}

	/**
	 * 获得双精度浮点数参数
	 * @param name 参数名
	 * @return 参数内容(保证不为null)
	 */
	public double getDoublePara(String name) {
		double value = 0;
		String stringPara = this.getStringPara(name);
		try {
			value = Double.parseDouble(stringPara);
		} catch (NumberFormatException e) {}
		return value;
	}

	/**
	 * 获得字符串参数
	 * @param name 参数名
	 * @return 参数内容(保证不为null)
	 */
	public String getStringPara(String name) {
		String stringPara = super.getPara(name);
		if (stringPara == null) {
			return "";
		}
		return stringPara;
	}

	/**
	 * 发送信息
	 * @param info
	 */
	public void sendInfo(String info){
		super.getResponse().setContentType("text/html");
		try {
			PrintWriter out = super.getResponse().getWriter();
			out.print(info);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送json
	 * @param info
	 * @throws IOException 
	 */
	public void sendJson(Object object){
		this.sendInfo(Json.toJson(object));
	}
	
	/**
	 * 发送给客户端一个图片
	 * @param image
	 */
	public void sendImg(RenderedImage image){
		try {
		    ImageIO.write(image, "JPEG", super.getResponse().getOutputStream());//将内存中的图片通过流动形式输出到客户端
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 设置Cookie
	 * @param name
	 * @param value
	 */
	public void setCook(String name, String value, int time){
		try {
			//将要存入Cookie的数据转码
			String cookieValue = URLEncoder.encode(value, ActionConf.ENCODING);
			
			Cookie cookie = new Cookie(name, cookieValue);
			if(time > 0){
				cookie.setMaxAge(time);
			}
			cookie.setPath("/");
			
			super.getResponse().addCookie(cookie);
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 设置临时Cookie
	 * @param name
	 * @param value
	 */
	public void setCook(String name, String value){
		this.setCook(name, value, 0);
	}
	
	/**
	 * 获得Cookie
	 * @param name
	 * @return
	 */
	public String getCook(String name){
		//要返回的 值
		String value = "";
		
		try {
			//获得cookie数组
			Cookie[] cookies = super.getRequest().getCookies();
			if(cookies != null){
				for(Cookie cookie : cookies){
					//Cookie名
				    String cookieName = cookie.getName();
				    if(cookieName.equals(name)){
				    	//从Cookie中获得数据
				    	String cookieValue = cookie.getValue();
				    	
				    	//将从Cookie中取出的数据解码
						value = URLDecoder.decode(cookieValue, ActionConf.ENCODING);
				    }
				}
			}
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
}
