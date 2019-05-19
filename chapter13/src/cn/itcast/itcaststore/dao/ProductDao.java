package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.OrderItem;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class ProductDao {
	public void addProduct(Product p) throws SQLException {
		String sql="insert into products values(?,?,?,?,?,?,?)";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,p.getId(),p.getName(),p.getPrice(),p.getCategory(),p.getPnum(),p.getImgurl(),p.getDescription());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int findAllCount(String category) throws SQLException {
		String sql="select count(*) from products";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		if(!"全部商品".equals(category)) {
			sql += " where category=?";
			Long count=(Long)runner.query(sql, new ScalarHandler(),category);
			return count.intValue();
		}else {
			Long count=(Long)runner.query(sql, new ScalarHandler());
			return count.intValue();
		}
	}

	public List<Product> findByPage(int currentPage, int currentCount, String category) throws SQLException {
		String sql=null;
		Object[] obj=null;
		if(!"全部商品".equals(category)) {
			sql="select * from products where category=? limit ?,?";
			obj=new Object[] {category,(currentPage-1)*currentCount,currentCount,};
		}else {
			sql="select * from products limit ?,?";
			obj=new Object[] {(currentPage-1)*currentCount,currentCount,};
		}
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class),obj);
	}

	public Product findProductById(String id) throws SQLException {
		String sql="select * from products where id=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql,new BeanHandler<Product>(Product.class),id);
	}

	public void changeProductNum(Order order) throws SQLException {
		String sql="update products set pnum=pnum-? where id=?";
		List<OrderItem> items=order.getOrderItems();
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		for(OrderItem item:items) {
			String id=item.getP().getId();
			int item_num=item.getBuynum();
			runner.update(sql,item_num,id);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int findBookByNameAllCount(String searchfield) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select count(*) from products where name like '%"+searchfield+"%'";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		Long count=(Long)runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	public List<Product> findBookByName(int currentPage, int currentCount, String searchfield) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select * from products where name like'%"+searchfield+"%' limit ?,?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql,new BeanListHandler<Product>(Product.class),(currentPage-1)*currentCount,currentCount);
	}

	public List<Object[]> getWeekHotProduct() throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select products.id,products.name,products.imgurl, "
				+ " SUM(orderitem.buynum) totalsalnum "
				+ " from orderitem,orders,products "
				+ " where orderitem.order_id = orders.id "
				+ " and products.id = orderitem.product_id "
				+ " and orders.paystate =1 "
				+ " and orders.ordertime>DATE_SUB(NOW(),INTERVAL 7 DAY) "
				+ " group by products.id,products.name,products.imgurl "
				+ " order by totalsalnum desc "
				+ " limit 0,2";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql,new ArrayListHandler());
	}

	public List<Product> listAll() throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select * from products";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	public List<Product> findProductByManyCondition(String id, String name, String category, String minprice,
			String maxprice) throws SQLException {
		// TODO 自动生成的方法存根
		List<Object> list=new ArrayList<Object>();
		String sql="select * from products where 1=1 ";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		if(id != null && id.trim().length()>0) {
			sql +=" and id=?";
			list.add(id);
		}
		if(name != null && name.trim().length()>0) {
			sql +=" and name=?";
			list.add(name);
		}
		if(category != null && category.trim().length()>0) {
			sql +=" and category=?";
			list.add(category);
		}
		if(minprice != null && maxprice != null && minprice.trim().length()>0 && maxprice.trim().length()>0) {
			sql +=" and price between ? and ?";
			list.add(minprice);
			list.add(maxprice);
		}
		Object[] params=list.toArray();
		return runner.query(sql, new BeanListHandler<Product>(Product.class),params);
	}

	public void editProduct(Product p) throws SQLException {
		// TODO 自动生成的方法存根
		List<Object> obj=new ArrayList<Object>();
		String sql="update products set name=?,price=?,category=?,pnum=?,description=?";
		obj.add(p.getName());
		obj.add(p.getPrice());
		obj.add(p.getCategory());
		obj.add(p.getPnum());
		obj.add(p.getDescription());
		if(p.getImgurl()!=null && p.getImgurl().trim().length()>0) {
			sql+=" ,imgurl=?";
			obj.add(p.getImgurl());
		}
		sql +=" where id=?";
		obj.add(p.getId());
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		Object[] params=obj.toArray();
		runner.update(sql,params);
	}

	public void deleteProduct(String id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="delete from products where id=?";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,id);
	}

	public List<Object[]> download(String year, String month) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="select products.name,SUM(orderitem.buynum) totalsalnum "
				+ " from products,orders,orderitem "
				+ " where orderitem.order_id = orders.id "
				+ " and products.id = orderitem.product_id "
				+ " and orders.paystate =1 "
				+ " and YEAR(ordertime)=? and MONTH(ordertime)=? "
				+ " group by products.name "
				+ " order by totalsalnum desc ";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		List<Object[]> list=runner.query(sql,new ArrayListHandler(),year,month);
		return list;
	}

	public void updateProductNum(List<OrderItem> items) throws SQLException {
		// TODO 自动生成的方法存根
		String sql="update products set pnum=pnum+? where id=?";
		QueryRunner runner=new QueryRunner();
		Object[][] params=new Object[items.size()][2];
		for(int i=0;i<params.length;i++) {
			params[i][0]=items.get(i).getBuynum();
			params[i][1]=items.get(i).getP().getId();
		}
		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

}
