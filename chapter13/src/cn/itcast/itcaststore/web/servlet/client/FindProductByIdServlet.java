package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.FindProductByIdException;
import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class FindProductByIdServlet
 */
public class FindProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String type=request.getParameter("type");
		ProductService service=new ProductService();
		try {
			Product p = service.findProductById(id);
			request.setAttribute("p", p);
			if(type==null) {
				request.getRequestDispatcher("/client/info.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
			return;
		} catch (FindProductByIdException e) {
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
