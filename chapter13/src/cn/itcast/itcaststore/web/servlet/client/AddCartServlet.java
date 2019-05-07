package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.FindProductByIdException;
import cn.itcast.itcaststore.service.ProductService;

/**
 * Servlet implementation class AddCartServlet
 */
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		try {
			String id=request.getParameter("id");
			ProductService service=new ProductService();
			Product p=service.findProductById(id);
			HttpSession session=request.getSession();
			Map<Product,Integer> cart=(Map<Product, Integer>)session.getAttribute("cart");
			if(cart == null) {
				cart =new HashMap<Product,Integer>();
			}
			Integer count=cart.get(p);
			if(count == null) {
				cart.put(p,1);
			}else {
				cart.put(p, count+1);
			}
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath()+"/client/cart.jsp");
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
