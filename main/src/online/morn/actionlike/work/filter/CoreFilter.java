package online.morn.actionlike.work.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.morn.actionlike.work.server.CoreServer;
import online.morn.actionlike.work.test.Tester;
import online.morn.actionlike.work.util.conf.ActionConf;
import online.morn.actionlike.work.util.pro.HttpPro;

/**
 * 核心过滤器
 * @author Morn4ever 2015-10-27
 */
public class CoreFilter implements Filter{
	
	/**
	 * 执行实际的过滤工作
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {

		Tester.print("请求路过核心过滤器");
		
		//获得request response
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		
		//创建http工具
		HttpPro httpPro = new HttpPro(request, response);
		httpPro.setEncoding(ActionConf.ENCODING);
		
		//设置http工具
		request.setAttribute("httpPro", httpPro);
		
		//创建核心服务对象
		CoreServer coreServer = new CoreServer(httpPro);
		
		if(coreServer.continueRun()){
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * 服务器调用一次init为服务准备过滤器
	 */
	public void init(FilterConfig config) throws ServletException {
		Tester.print("初始化核心过滤器");
	}
	
	/**
	 * 指出过滤器已结束服务
	 */
	public void destroy() {
		Tester.print("结束核心过滤器");
	}
}
