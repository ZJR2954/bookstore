package cn.itcast.itcaststore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.itcaststore.domain.Order;
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

}
