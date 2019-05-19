package cn.itcast.itcaststore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.User;

/**
 * Servlet Filter implementation class AdminPrivilegeFilter
 */
public class AdminPrivilegeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminPrivilegeFilter() {
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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp= (HttpServletResponse) response;

		//判断是否具有权限
		User user = (User) req.getSession().getAttribute("user");
		if (user != null && "超级用户".equals(user.getRole())) {
			//放行
			chain.doFilter(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/error/privilege.jsp");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
