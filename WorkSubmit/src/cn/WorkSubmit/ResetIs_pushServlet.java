package cn.WorkSubmit;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResetIs_pushServlet
 */
public class ResetIs_pushServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		//重置所有is_push为false
			UsersDao usersDao = new UsersDao();
			ArrayList<User> list=usersDao.findAll();
			for(int i=0;i<list.size();i++) {
				usersDao.updateIs_push(list.get(i).getUsername(), false);
			}
			response.getWriter().print("重置所有Is_push成功!");
			//创建一个检查数据库并提醒未完成作业的人的线程
			Inspect T1 = new Inspect();
			T1.start();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}	

}
