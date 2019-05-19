package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		ProductService service=new ProductService();
		List<Object[]> ps=service.download(year,month);
		String fileName=year+"��"+month+"�����۰�.csv";
		String fileType=this.getServletContext().getMimeType(fileName);
		response.setContentType(fileType);
		response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("GBK"),"iso8859-1"));
		response.setCharacterEncoding("gbk");
		PrintWriter out=response.getWriter();
		out.println("��Ʒ���ƣ���Ʒ����");
		for(int i=0;i<ps.size();i++) {
			Object[] arr=ps.get(i);
			out.println(arr[0]+","+arr[1]);
		}
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
