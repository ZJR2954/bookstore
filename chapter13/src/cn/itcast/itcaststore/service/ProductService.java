package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.itcaststore.dao.ProductDao;
import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.FindProductByIdException;

public class ProductService {
	private ProductDao dao=new ProductDao();
	//通过页面查找商品
	public PageBean findProductByPage(int currentPage, int currentCount, String category) {
		PageBean bean=new PageBean();
		bean.setCurrentCount(currentCount);
		bean.setCurrentPage(currentPage);		
		bean.setCategory(category);
		try {
			int totalCount = dao.findAllCount(category);
			bean.setTotalCount(totalCount);
			int totalPage=(int)Math.ceil(totalCount*1.0/currentCount);
			bean.setTotalPage(totalPage);
			List<Product> ps=dao.findByPage(currentPage,currentCount,category);
			bean.setPs(ps);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return bean;
	}
	//通过id查找商品
	public Product findProductById(String id)throws FindProductByIdException {
		Product p=new Product();
		try {
				p=dao.findProductById(id);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				throw new FindProductByIdException("根据ID查找商品失败！");
			}
		return p;
	}
	//通过书名模糊查找商品
	public PageBean findBookByName(int currentPage, int currentCount, String searchfield) {
		// TODO 自动生成的方法存根
		PageBean bean=new PageBean();
		bean.setCurrentCount(currentCount);
		bean.setCurrentPage(currentPage);
		bean.setSearchfield(searchfield);

		try {
			int totalCount = dao.findBookByNameAllCount(searchfield);
			bean.setTotalCount(totalCount);
			int totalPage=(int) Math.ceil(totalCount*1.0/currentCount);
			bean.setTotalPage(totalPage);
			List<Product> ps=dao.findBookByName(currentPage,currentCount,searchfield);
			bean.setPs(ps);
			return bean;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new RuntimeException("前台搜索框根据书名查询图书失败！");
		}	
	}
	public List<Object[]> getWeekHotProduct() {
		// TODO 自动生成的方法存根
		try {
			return dao.getWeekHotProduct();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

}
