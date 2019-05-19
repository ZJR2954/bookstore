package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Notice;
import cn.itcast.itcaststore.service.NoticeService;

/**
 * Servlet implementation class FindNoticeByIdServlet
 */
public class FindNoticeByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NoticeService service=new NoticeService();
		int n_id=Integer.parseInt(request.getParameter("id"));
		Notice notice=service.findNoticeById(n_id);
		request.setAttribute("n", notice);
		request.getRequestDispatcher("/admin/notices/edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
