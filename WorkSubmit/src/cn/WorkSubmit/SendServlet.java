package cn.WorkSubmit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendServlet
 */
public class SendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		try {
			String webPath = "/upload/";
			FileOutputStream fos1 = new FileOutputStream(new File(getServletContext().getRealPath("\\WorkFile.zip")));
			filePackage.toZip(getServletContext().getRealPath(webPath), fos1, true);
			//把压缩包发给管理员
			MailUtils.sendMail("763305173@qq.com", "管理员强制发送",getServletContext().getRealPath("\\WorkFile.zip"));
			response.getWriter().print("WorkFile发送成功!");
		} catch (AddressException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
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
