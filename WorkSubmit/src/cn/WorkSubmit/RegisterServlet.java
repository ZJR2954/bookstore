package cn.WorkSubmit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		if(username!=null && email!=null && username!="" && email!="") {
		UsersDao usersDao=new UsersDao();
		User user=new User();
		user.setUsername(username);
		user.setEmail(email);
		usersDao.insert(user);
		response.sendRedirect("/WorkSubmit/login.html");
		}else {
			response.getWriter().print("请填写正确的用户名和邮箱,注册失败！<br /><a href='/WorkSubmit/register.html'>再试一次</ a>");
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
