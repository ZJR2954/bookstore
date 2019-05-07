package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.itcaststore.domain.OrderItem;
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

}
