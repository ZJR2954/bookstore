package cn.itcast.itcaststore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.OrderItem;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class OrderItemDao {

	public void addOrderItems(List<OrderItem> orderItems) throws SQLException {
		String sql="insert into orderitem(order_id,product_id,buynum) values(?,?,?)";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		for(OrderItem item:orderItems) {
			String order_id=item.getOrder().getId();
			String product_id=item.getP().getId();
			int num=item.getBuynum();
			runner.update(sql,order_id,product_id,num);
		}
		
	}

	public List<OrderItem> findOrderItemByOrder(Order order) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select * from orderItem,Products "
				+ " where products.id=orderItem.product_id and order_id=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<OrderItem>>() {
			public List<OrderItem> handle(ResultSet rs) throws SQLException {
				// TODO 自动生成的方法存根
				List<OrderItem> items=new ArrayList<OrderItem>();
				while(rs.next()) {
					OrderItem item=new OrderItem();
					item.setBuynum(rs.getInt("buynum"));
					Product p=new Product();
					p.setCategory(rs.getString("category"));
					p.setId(rs.getString("id"));
					p.setDescription(rs.getString("description"));
					p.setImgurl(rs.getString("imgurl"));
					p.setName(rs.getString("name"));
					p.setPnum(rs.getInt("pnum"));
					p.setPrice(rs.getDouble("price"));
					item.setP(p);
					items.add(item);
				}
				return items;
			}
		},order.getId());		
	}

	public void delOrderItems(String id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="delete from orderItem where order_id=?";
		QueryRunner runner=new QueryRunner();
		runner.update(DataSourceUtils.getConnection(),sql,id);
	}

}
