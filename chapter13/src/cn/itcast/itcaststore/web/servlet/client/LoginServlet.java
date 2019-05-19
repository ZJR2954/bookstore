package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		UserService service=new UserService();
		try {
			User user = service.login(username,password);
			request.getSession().setAttribute("user", user);
			String role=user.getRole();
			if("超级用户".equals(role)) {
				response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
				return;
			}else {
				response.sendRedirect(request.getContextPath()+"/myAccount");
				return;
			}
		} catch (LoginException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			request.setAttribute("register_message",e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			return;
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
