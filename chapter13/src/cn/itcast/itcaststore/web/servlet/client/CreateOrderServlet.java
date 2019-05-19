package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.OrderItem;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.OrderService;
import cn.itcast.itcaststore.utils.IdUtils;

/**
 * Servlet implementation class CreatOrderServlet
 */
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Order order=new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		Map<Product,Integer> cart=(Map<Product, Integer>) session.getAttribute("cart");
		order.setUser(user);
		order.setId(IdUtils.getUUID());
		for(Product p:cart.keySet()) {
			OrderItem item=new OrderItem();
			item.setOrder(order);
			item.setBuynum(cart.get(p));
			item.setP(p);
			order.getOrderItems().add(item);
		}
		OrderService service=new OrderService();
		service.addOrder(order);
		request.getSession().removeAttribute("cart");
		response.sendRedirect(request.getContextPath()+"/client/createOrderSuccess.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
