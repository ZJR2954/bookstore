package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.itcaststore.dao.UserDao;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.service.UserService;
import cn.itcast.itcaststore.utils.ActivateCodeUtils;

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
		//校验username是否重名
		String username=request.getParameter("username");
		UserDao userdao=new UserDao();
		try {
			User hasUser = userdao.findUserByUsername(username);
			if(hasUser!=null) {
				request.setAttribute("usernameError", "这个用户名已存在！");
				request.getRequestDispatcher("/client/register.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		//校验验证码
		String userCode=request.getParameter("userCode");
		String serverCode=(String)request.getSession().getAttribute("serverCode");
		if(!serverCode.equalsIgnoreCase(userCode)) {
			request.setAttribute("codeError", "输入的验证码错误");
			request.getRequestDispatcher("/client/register.jsp").forward(request, response);
			return;
		}
		//封装参数到user
		User user=new User();
		try {
			BeanUtils.populate(user,request.getParameterMap());
			//封装激活参数，通过工具类得到激活参数
			user.setActiveCode(ActivateCodeUtils.createActivateCode());
			user.setRegistTime(new Date());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//调用UserService的注册方法
		UserService service=new UserService();
		try {
			service.register(user);
			response.sendRedirect(request.getContextPath()+"/client/registersuccess.jsp");
		} catch (RegisterException e) {
			String error=e.getMessage();
			response.getWriter().write(error);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
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
