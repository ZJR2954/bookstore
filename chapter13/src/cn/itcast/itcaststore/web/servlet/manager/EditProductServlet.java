package cn.itcast.itcaststore.web.servlet.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.service.ProductService;
import cn.itcast.itcaststore.utils.FileUploadUtils;
import cn.itcast.itcaststore.utils.IdUtils;

/**
 * Servlet implementation class EditProductServlet
 */
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product p=new Product();
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", IdUtils.getUUID());
		
		DiskFileItemFactory dfif=new DiskFileItemFactory();
		//设置文件临时缓存
		String temp=this.getServletContext().getRealPath("/temp");
		dfif.setRepository(new File(temp));
		//设置缓存大小为10M
		dfif.setSizeThreshold(1024*1024*10);
		
		//创建上传组件
		ServletFileUpload upload=new ServletFileUpload(dfif);
		upload.setHeaderEncoding("utf-8");
		try {
			//解析request得到所有FileItem
			List<FileItem> items=upload.parseRequest(request);
			for(FileItem item:items) {
				//判断是否为文件上传组件的标志
				boolean flag=item.isFormField();				
				if(flag) {
					//普通组件
					String fieldName=item.getFieldName();
					String value=item.getString("utf-8");
					map.put(fieldName, value);
				}else {
					String f=request.getParameter("upload");
					if(f!=null &&f.trim().length()>0) {
						//文件上传组件
						String fileName=item.getName();
						//截取文件名，得到文件的真实名称
						fileName=FileUploadUtils.subFileName(fileName);
						//得到随机名称
						String randomName=FileUploadUtils.generateRandomFileName(fileName);
						//得到随机路径
						String randomDir=FileUploadUtils.generateRandomDir(randomName);
						
						//图片存储父目录
						String imgurl_parent="/productImg"+randomDir;
						File parentDir=new File(this.getServletContext().getRealPath(imgurl_parent));
						if(!parentDir.exists()) {
							parentDir.mkdirs();
						}
						String imgurl=imgurl_parent+"/"+randomName;
						map.put("imgurl",imgurl);
						IOUtils.copy(item.getInputStream(), new FileOutputStream(new File(parentDir,randomName)));
						item.delete();
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			BeanUtils.populate(p, map);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		ProductService service=new ProductService();
		try {
			service.editProduct(p);
			response.sendRedirect(request.getContextPath()+"/manager/listProduct");
			return;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			response.getWriter().write("修改商品信息失败！");
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
