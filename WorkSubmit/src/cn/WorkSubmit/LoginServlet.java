package cn.WorkSubmit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
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
		
		UsersDao usersDao=new UsersDao();
		ArrayList<User> list=usersDao.findAll();
		ArrayList<String> usernames=new ArrayList<String>();
		Map<String, String> emails=new HashMap<String, String>();
		for(int i=0;i<list.size();i++) {
			usernames.add(list.get(i).getUsername());
			emails.put(list.get(i).getUsername(),list.get(i).getEmail());
		}
		PrintWriter pw=response.getWriter();
		if (username!="" && usernames.contains(username) && emails.get(username).equals(email)) {
			User user=new User();
			user.setUsername(username);
			user.setEmail(email);
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/WorkSubmit/Index");
			
			request.getSession().setAttribute("username", username);
		}else if(username.equals("zjr2954")&&email.equals("763305173@qq.com")) {
			response.sendRedirect("/WorkSubmit/admin.jsp");
		}else {
			pw.write("用户名或邮箱错误，登陆失败!<br /><a href='/WorkSubmit/login.html'>再试一次</a>");
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
