package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.itcaststore.dao.OrderDao;
import cn.itcast.itcaststore.dao.OrderItemDao;
import cn.itcast.itcaststore.dao.ProductDao;
import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.OrderItem;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class OrderService {
	private OrderDao odao=new OrderDao();
	private OrderItemDao oidao=new OrderItemDao();
	private ProductDao pdao=new ProductDao();
	
	public void addOrder(Order order){
		// TODO 自动生成的方法存根
		try {
			DataSourceUtils.startTransaction();
				odao.addOrder(order);
				oidao.addOrderItems(order.getOrderItems());
				pdao.changeProductNum(order);
			DataSourceUtils.releaseAndCloseConnection();			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}

	public List<Order> findAllOrder() {
		// TODO 自动生成的方法存根
		List<Order> orders=null;
		try {
			orders=odao.findAllOrder();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return orders;
	}

	public List<Order> findOrderByManyCondition(String id, String receiverName) {
		// TODO 自动生成的方法存根
		List<Order> orders=null;
		try {
			orders=odao.findOrderByManyCondition(id,receiverName);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return orders;
	}

	public List<Order> findOrderByUser(User user) {
		// TODO 自动生成的方法存根
		List<Order> orders=null;
		try {
			orders = odao.findOrderByUser(user);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return orders;
	}

	public Order findOrderById(String id) {
		// TODO 自动生成的方法存根
		Order order=null;
		try {
			order=odao.findOrderById(id);
			List<OrderItem> items=oidao.findOrderItemByOrder(order);
			order.setOrderItems(items);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return order;
	}

	public void delOrderById(String id) {
		// TODO 自动生成的方法存根
		try {
			DataSourceUtils.startTransaction();
			oidao.delOrderItems(id);
			odao.delOrderById(id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	public void delOrderByIdWithClient(String id) {
		// TODO 自动生成的方法存根
		try {
			DataSourceUtils.startTransaction();
			Order order=new Order();
			order.setId(id);
			List<OrderItem> items=oidao.findOrderItemByOrder(order);
			pdao.updateProductNum(items);
			oidao.delOrderItems(id);
			odao.delOrderById(id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	public void pay(String order_id) {
		// TODO 自动生成的方法存根
		try {
			odao.pay(order_id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
