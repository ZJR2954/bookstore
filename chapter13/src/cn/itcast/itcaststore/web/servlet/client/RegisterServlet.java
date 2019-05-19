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
		//У��username�Ƿ�����
		String username=request.getParameter("username");
		UserDao userdao=new UserDao();
		try {
			User hasUser = userdao.findUserByUsername(username);
			if(hasUser!=null) {
				request.setAttribute("usernameError", "����û����Ѵ��ڣ�");
				request.getRequestDispatcher("/client/register.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		
		//У����֤��
		String userCode=request.getParameter("userCode");
		String serverCode=(String)request.getSession().getAttribute("serverCode");
		if(!serverCode.equalsIgnoreCase(userCode)) {
			request.setAttribute("codeError", "�������֤�����");
			request.getRequestDispatcher("/client/register.jsp").forward(request, response);
			return;
		}
		//��װ������user
		User user=new User();
		try {
			BeanUtils.populate(user,request.getParameterMap());
			//��װ���������ͨ��������õ��������
			user.setActiveCode(ActivateCodeUtils.createActivateCode());
			user.setRegistTime(new Date());
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//����UserService��ע�᷽��
		UserService service=new UserService();
		try {
			service.register(user);
			response.sendRedirect(request.getContextPath()+"/client/registersuccess.jsp");
		} catch (RegisterException e) {
			String error=e.getMessage();
			response.getWriter().write(error);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
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
