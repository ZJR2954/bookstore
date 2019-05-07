package cn.itcast.itcaststore.service;

//import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.FindProductByIdException;

public class Test {

	public static void main(String[] args) throws FindProductByIdException {
		// TODO 自动生成的方法存根
		ProductService service=new ProductService();
//		PageBean bean=service.findProductByPage(1,2,"全部商品");
//		System.out.println(bean.getCategory());
		
		Product p=service.findProductById("");
		System.out.println(p.getCategory());
	}

}
