package cn.itcast.itcaststore.service;

import java.util.List;

//import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.FindProductByIdException;

public class Test {

	public static void main(String[] args) throws FindProductByIdException {
		// TODO �Զ����ɵķ������
		ProductService service=new ProductService();
//		PageBean bean=service.findProductByPage(1,2,"ȫ����Ʒ");
//		System.out.println(bean.getCategory());
		
		List<Product> list=service.findProductByManyCondition("", "", "����", "0", "40");
		System.out.println(list.size());
	}

}
