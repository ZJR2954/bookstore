package cn.itcast.itcaststore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class OrderDao {

	public void addOrder(Order order) throws SQLException {
		String sql="insert into orders(id,money,receiverAddress,receiverName,receiverPhone,paystate,user_id) values(?,?,?,?,?,?,?)";
		Object[] params= {order.getId(),order.getMoney(),order.getReceiverAddress(),order.getReceiverName(),order.getReceiverPhone(),order.getPaystate(),order.getUser().getId()};
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		int row=runner.update(sql,params);
		if(row==0) {
			throw new RuntimeException();
		}
	}

	public List<Order> findAllOrder() throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select orders.*,user.* from orders,user "
				+ " where user.id=orders.user_id order by orders.user_id";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				// TODO 自动生成的方法存根
				List<Order> orders=new ArrayList<Order>();
				while(rs.next()) {
					 Order order=new Order();
					 order.setId(rs.getString("orders.id"));
					 order.setMoney(rs.getDouble("orders.money"));
					 order.setOrdertime(rs.getDate("orders.ordertime"));
					 order.setPaystate(rs.getInt("orders.paystate"));
					 order.setReceiverAddress(rs.getString("orders.receiverAddress"));
					 order.setReceiverName(rs.getString("orders.receiverName"));
					 order.setReceiverPhone(rs.getString("orders.receiverPhone"));
					 
					 User user=new User();
					 user.setId(rs.getInt("user.id"));
					 user.setUsername(rs.getString("user.username"));
					 user.setGender(rs.getString("user.gender"));
					 user.setEmail(rs.getString("user.email"));
					 user.setTelephone(rs.getString("user.telephone"));
					 user.setRole(rs.getString("user.role"));
					 
					 order.setUser(user);
					 orders.add(order);
				}
				return orders;
			}
		});
		
	}

	public List<Order> findOrderByManyCondition(String id, String receiverName) throws SQLException{
		// TODO 自动生成的方法存根
		String sql="select orders.*,user.* from orders,user "
				+ " where user.id=orders.user_id ";
		List<Object> params=new ArrayList<Object>();
		if(id != null && id.trim().length()>0) {
			sql+=" and orders.id=?";
			params.add(id);
		}
		if(receiverName != null && receiverName.trim().length()>0) {
			sql+=" and receiverName=?";
			params.add(receiverName);
		}
		sql+=" order by orders.user_id";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				// TODO 自动生成的方法存根
				List<Order> orders=new ArrayList<Order>();
				while(rs.next()) {
					 Order order=new Order();
					 order.setId(rs.getString("orders.id"));
					 order.setMoney(rs.getDouble("orders.money"));
					 order.setOrdertime(rs.getDate("orders.ordertime"));
					 order.setPaystate(rs.getInt("orders.paystate"));
					 order.setReceiverAddress(rs.getString("orders.receiverAddress"));
					 order.setReceiverName(rs.getString("orders.receiverName"));
					 order.setReceiverPhone(rs.getString("orders.receiverPhone"));
					 
					 User user=new User();
					 user.setId(rs.getInt("user.id"));
					 user.setUsername(rs.getString("user.username"));
					 user.setGender(rs.getString("user.gender"));
					 user.setEmail(rs.getString("user.email"));
					 user.setTelephone(rs.getString("user.telephone"));
					 user.setRole(rs.getString("user.role"));
					 
					 order.setUser(user);
					 orders.add(order);
				}
				return orders;
			}
		},params.toArray());
	}

	public List<Order> findOrderByUser(final User user) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "select * from orders where user_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				List<Order> orders = new ArrayList<Order>();
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getString("id"));
					order.setMoney(rs.getDouble("money"));
					order.setOrdertime(rs.getDate("ordertime"));
					order.setPaystate(rs.getInt("paystate"));
					order.setReceiverAddress(rs.getString("receiverAddress"));
					order.setReceiverName(rs.getString("receiverName"));
					order.setReceiverPhone(rs.getString("receiverPhone"));
					order.setUser(user);
					orders.add(order);
				}
				return orders;
			}
		}, user.getId());
	}

	public Order findOrderById(String id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select * from orders,user "
				+ " where orders.user_id=user.id and orders.id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<Order>() {
			public Order handle(ResultSet rs) throws SQLException {
				// TODO 自动生成的方法存根
				Order order=new Order();
				while(rs.next()) {
					 order.setId(rs.getString("orders.id"));
					 order.setMoney(rs.getDouble("orders.money"));
					 order.setOrdertime(rs.getDate("orders.ordertime"));
					 order.setPaystate(rs.getInt("orders.paystate"));
					 order.setReceiverAddress(rs.getString("orders.receiverAddress"));
					 order.setReceiverName(rs.getString("orders.receiverName"));
					 order.setReceiverPhone(rs.getString("orders.receiverPhone"));
					 
					 User user=new User();
					 user.setId(rs.getInt("user.id"));
					 user.setUsername(rs.getString("user.username"));
					 user.setGender(rs.getString("user.gender"));
					 user.setEmail(rs.getString("user.email"));
					 user.setTelephone(rs.getString("user.telephone"));
					 user.setRole(rs.getString("user.role"));				 
					 order.setUser(user);
				}
				return order;
			}
		},id);
	}

	public void delOrderById(String id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="delete from orders where id=?";
		QueryRunner runner=new QueryRunner();
		runner.update(DataSourceUtils.getConnection(),sql,id);
		
	}

	public void pay(String order_id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="update orders set paystate=1 where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,order_id);
	}
}
