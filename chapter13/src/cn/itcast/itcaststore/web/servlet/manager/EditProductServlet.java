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
		//�����ļ���ʱ����
		String temp=this.getServletContext().getRealPath("/temp");
		dfif.setRepository(new File(temp));
		//���û����СΪ10M
		dfif.setSizeThreshold(1024*1024*10);
		
		//�����ϴ����
		ServletFileUpload upload=new ServletFileUpload(dfif);
		upload.setHeaderEncoding("utf-8");
		try {
			//����request�õ�����FileItem
			List<FileItem> items=upload.parseRequest(request);
			for(FileItem item:items) {
				//�ж��Ƿ�Ϊ�ļ��ϴ�����ı�־
				boolean flag=item.isFormField();				
				if(flag) {
					//��ͨ���
					String fieldName=item.getFieldName();
					String value=item.getString("utf-8");
					map.put(fieldName, value);
				}else {
					String f=request.getParameter("upload");
					if(f!=null &&f.trim().length()>0) {
						//�ļ��ϴ����
						String fileName=item.getName();
						//��ȡ�ļ������õ��ļ�����ʵ����
						fileName=FileUploadUtils.subFileName(fileName);
						//�õ��������
						String randomName=FileUploadUtils.generateRandomFileName(fileName);
						//�õ����·��
						String randomDir=FileUploadUtils.generateRandomDir(randomName);
						
						//ͼƬ�洢��Ŀ¼
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		try {
			BeanUtils.populate(p, map);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} 
		ProductService service=new ProductService();
		try {
			service.editProduct(p);
			response.sendRedirect(request.getContextPath()+"/manager/listProduct");
			return;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			response.getWriter().write("�޸���Ʒ��Ϣʧ�ܣ�");
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
