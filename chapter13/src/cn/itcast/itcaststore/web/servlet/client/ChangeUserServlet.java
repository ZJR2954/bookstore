package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.UserService;

/**
 * Servlet implementation class ChangeUserServlet
 */
public class ChangeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=(User) request.getSession().getAttribute("user");
		User nuser=new User();
		nuser.setGender(request.getParameter("gender"));
		nuser.setPassword(request.getParameter("password"));
		nuser.setTelephone(request.getParameter("telephone"));
		request.getSession().setAttribute("nuser", nuser);
		UserService service=new UserService();
		service.changeUser(user,nuser);
		//É¾³ýsession£¬ÖØÐÂµÇÂ½
		request.getSession().invalidate();
		String flag=request.getParameter("flag");
		if(flag==null||flag.trim().isEmpty()) {
		response.sendRedirect(request.getContextPath()+"/client/login.jsp");
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
