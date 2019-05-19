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
		// TODO �Զ����ɵķ������
		try {
			DataSourceUtils.startTransaction();
				odao.addOrder(order);
				oidao.addOrderItems(order.getOrderItems());
				pdao.changeProductNum(order);
			DataSourceUtils.releaseAndCloseConnection();			
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
	}

	public List<Order> findAllOrder() {
		// TODO �Զ����ɵķ������
		List<Order> orders=null;
		try {
			orders=odao.findAllOrder();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return orders;
	}

	public List<Order> findOrderByManyCondition(String id, String receiverName) {
		// TODO �Զ����ɵķ������
		List<Order> orders=null;
		try {
			orders=odao.findOrderByManyCondition(id,receiverName);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return orders;
	}

	public List<Order> findOrderByUser(User user) {
		// TODO �Զ����ɵķ������
		List<Order> orders=null;
		try {
			orders = odao.findOrderByUser(user);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return orders;
	}

	public Order findOrderById(String id) {
		// TODO �Զ����ɵķ������
		Order order=null;
		try {
			order=odao.findOrderById(id);
			List<OrderItem> items=oidao.findOrderItemByOrder(order);
			order.setOrderItems(items);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return order;
	}

	public void delOrderById(String id) {
		// TODO �Զ����ɵķ������
		try {
			DataSourceUtils.startTransaction();
			oidao.delOrderItems(id);
			odao.delOrderById(id);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

	public void delOrderByIdWithClient(String id) {
		// TODO �Զ����ɵķ������
		try {
			DataSourceUtils.startTransaction();
			Order order=new Order();
			order.setId(id);
			List<OrderItem> items=oidao.findOrderItemByOrder(order);
			pdao.updateProductNum(items);
			oidao.delOrderItems(id);
			odao.delOrderById(id);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

	public void pay(String order_id) {
		// TODO �Զ����ɵķ������
		try {
			odao.pay(order_id);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

}
