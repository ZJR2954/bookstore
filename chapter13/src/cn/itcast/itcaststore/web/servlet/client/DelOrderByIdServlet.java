package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.OrderService;

/**
 * Servlet implementation class DelOrderByIdServlet
 */
public class DelOrderByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String type=request.getParameter("type");
		OrderService service=new OrderService();
		if((User)request.getSession().getAttribute("user")!=null){
			if(type !=null && type.trim().length()>0 && "admin".equals(type)) {					
				service.delOrderById(id);
				request.getRequestDispatcher("/manager/findOrders").forward(request, response);
				return;				
			}else {
				service.delOrderByIdWithClient(id);
				response.sendRedirect(request.getContextPath()+"/client/delOrderSuccess.jsp");
				return;
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/error/privilege.jsp");
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
