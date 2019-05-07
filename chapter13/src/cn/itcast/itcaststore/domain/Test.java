package cn.itcast.itcaststore.domain;

import java.sql.SQLException;
import java.util.UUID;

import cn.itcast.itcaststore.dao.ProductDao;

//import java.util.HashMap;
//import java.util.Map;

public class Test {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO 自动生成的方法存根
		Product p=new Product();
//		Map<Product,Integer> cart=new HashMap<Product, Integer>();
//		cart.put(p,1);
//		Product p1=new Product();
//		System.out.println(p1.equals(p));
//		System.out.println(cart.get(p1));
//		p.setPrice(20.05);
//		double x=p.getPrice();
//		System.out.println(x*3/10000/0.0001);
		p.setId(UUID.randomUUID().toString());
		p.setName("test6");
		p.setPrice(30);
		p.setCategory("生活百科");
		p.setPnum(100);
		p.setImgurl("/bookcover/106.jpg");
		p.setDescription("测试商品6");	
		ProductDao pd=new ProductDao();
		pd.addProduct(p);
	}
}
