<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员</title>
</head>
<body>
	<font style="align-self: center;">
		<%
			
			out.print("<br /><a href='/WorkSubmit/SendServlet'>直接发送WorkFile</a><br />");
			out.print("<br /><a href='/WorkSubmit/ResetIs_pushServlet'>重置所有IsPush</a>");
			
		%>
	</font>
</body>
</html>