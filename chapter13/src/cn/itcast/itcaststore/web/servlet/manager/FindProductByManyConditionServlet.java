package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class FindProductByManyConditionServlet
 */
public class FindProductByManyConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String category=request.getParameter("category");
		String minprice=request.getParameter("minprice");
		String maxprice=request.getParameter("maxprice");
		ProductService service=new ProductService();
		List<Product> ps=service.findProductByManyCondition(id,name,category,minprice,maxprice);
		request.setAttribute("ps", ps);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
