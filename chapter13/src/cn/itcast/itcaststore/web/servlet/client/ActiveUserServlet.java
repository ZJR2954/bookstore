package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.exception.ActiveUserException;
import cn.itcast.itcaststore.service.UserService;

/**
 * Servlet implementation class ActiveUserServlet
 */
public class ActiveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取激活码
		String activeCode=request.getParameter("activeCode");
		UserService service=new UserService();
		try {
			service.activeUser(activeCode);
			response.sendRedirect(request.getContextPath()+"/client/activesuccess.jsp");
		} catch (ActiveUserException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			String message=e.getMessage();
			response.getWriter().print(message);
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
