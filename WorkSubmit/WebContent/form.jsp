<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
	<font>
		<%
			if(request.getSession().getAttribute("username")!=null){
			out.print("您已登陆，欢迎你，"+request.getSession().getAttribute("username")+"!<br /><a href='/WorkSubmit/Logout'>退出</a>");
			}else{
				response.sendRedirect("/WorkSubmit/login.html");
			}
		%>
	</font>
	<div style="width:100%;text-align: center;">
		<form action="UploadServlet" method="post" enctype="multipart/form-data" style="border: 2px solid;margin: 50px 300px 50px 300px;">
			<br />
			上传者：<input name="name" type="text" /><br /><br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			上传文件：<input name="myfile" type="file" /><br /><br />
					<input type="submit" value="上传" id="bt" /><br /><br />
		</form>
	</div>
</body>
</html>