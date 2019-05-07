package cn.itcast.itcaststore.service;

import java.sql.SQLException;

import cn.itcast.itcaststore.dao.OrderDao;
import cn.itcast.itcaststore.dao.OrderItemDao;
import cn.itcast.itcaststore.dao.ProductDao;
import cn.itcast.itcaststore.domain.Order;
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

}
