package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Notice;
import cn.itcast.itcaststore.service.NoticeService;

/**
 * Servlet implementation class EditNoticeServlet
 */
public class EditNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NoticeService service=new NoticeService();
		Notice notice=new Notice();
		int n_id=Integer.parseInt(request.getParameter("id"));
		String title=request.getParameter("title");
		String details=request.getParameter("details");
		String t=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		notice.setN_id(n_id);
		notice.setTitle(title);
		notice.setDetails(details);
		notice.setN_time(t);
		service.updateNotice(notice);
		request.getRequestDispatcher("/manager/listNotice").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
