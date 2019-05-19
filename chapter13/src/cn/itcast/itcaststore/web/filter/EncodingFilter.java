package cn.itcast.itcaststore.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
//����������������Ŀ����������������
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletResponse myResponse=(HttpServletResponse) response;
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletRequest myRequest = new MyRequest(httpServletRequest);
		response.setContentType("text/html;charset=utf-8");
		// pass the request along the filter chain
		chain.doFilter(myRequest, myResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

class MyRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	private boolean hasEncode;
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
		// TODO �Զ����ɵĹ��캯�����
	}
	public Map<String,String[]> getParameterMap() {
		String method=request.getMethod();	
		if(method.equalsIgnoreCase("post")) {
			//post����ʽ
			try {
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			}catch(UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else if(method.equalsIgnoreCase("get")) {
			//get����ʽ
			Map<String,String[]> parameterMap=request.getParameterMap();
			if(!hasEncode) {
				//ֻ����һ��
				for(String parameterName : parameterMap.keySet()) {
					String[] values=parameterMap.get(parameterName);
					if(values != null) {
						for(int i=0;i<values.length;i++) {
								String value=values[i];
								try {
									//ͨ��String�Ĺ��췽��ת��
									values[i]=new String(value.getBytes("ISO-8859-1"),"utf-8");
									
								} catch (UnsupportedEncodingException e) {
									// TODO �Զ����ɵ� catch ��
									e.printStackTrace();
								}
						}
					}
				}
				hasEncode=true;
			}
			return parameterMap;
		}
		return super.getParameterMap();
	}
	public String getParameter(String name) {
		Map<String,String[]> parameterMap=getParameterMap();
		String[] values=parameterMap.get(name);
		if (values == null) {
			return null;
		}
		return values[0];
	}
	public String[] getParameterValues(String name) {
		Map<String,String[]> parameterMap=getParameterMap();
		String[] values=parameterMap.get(name);
		return values;
	}
}