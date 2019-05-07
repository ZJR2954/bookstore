package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class ShowProductByPageServlet
 */
public class ShowProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currentPage=1;
		String _currentPage=request.getParameter("currentPage");
		if(_currentPage !=  null) {
			currentPage=Integer.parseInt(_currentPage);
		}
		int currentCount=4;
		String _currentCount=request.getParameter("currentCount");
		if(_currentCount != null) {
			currentCount=Integer.parseInt(_currentCount);
		}
		String category="全部商品";
		String _category=request.getParameter("category");
		if(_category != null) {
			category=_category;
		}
		ProductService service=new ProductService();
		PageBean bean=service.findProductByPage(currentPage,currentCount,category);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
