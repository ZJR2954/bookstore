<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/form.js"></script>
</head>
<body class="main">
	
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
					<table width="100%" border="0" cellspacing="0"
						style="margin-top:30px">
						<tr>
							<td class="listtitle">我的帐户</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/icon1.png" width="15" height="10" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath }/client/modifyuserinfo.jsp">用户信息修改</a>
							</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/icon2.png" width="15" height="10" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath}/findOrderByUser">订单查询</a>
							</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/icon3.png" width="15" height="10" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath}/logout">用戶退出</a>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/client/myAccount.jsp">&nbsp;我的帐户</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;用户信息修改
					</div>
					<form action="${pageContext.request.contextPath}/changeUser" method="post" onsubmit="return checkPassword()&&checkConfirm();">
					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="2" class="upline">
									<tr>
										<td style="text-align:right; width:20%">会员邮箱：</td>
										<td style="width:40%; padding-left:20px">${user.email }</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td style="text-align:right">会员名：</td>
										<td style="padding-left:20px">${user.username }</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td style="text-align:right">修改密码：</td>
										<td><input type="password" class="textinput"  id="password" name="password" onkeyup="checkPassword();"/></td>
										<td><span id="passwordMsg"></span><font color="#999999">请设置6-16位字符</font></td>
									</tr>
									<tr>
										<td style="text-align:right">重复密码：</td>
										<td><input type="password" class="textinput"  id="repassword" name="repassword" onkeyup="checkConfirm();"/></td>
										<td><span id="confirmMsg"></span>&nbsp;</td>
									</tr>
									<tr>
										<td style="text-align:right">性别：</td>
										<td colspan="2">&nbsp;&nbsp;
											<input type="radio" name="gender" value="男" ${user.gender=='男'?'checked':'' }/> 男
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="gender" value="女"  ${user.gender=='女'?'checked':'' }/> 女
										</td>
									</tr>
									<tr>
										<td style="text-align:right">联系方式：</td>
										<td colspan="2">
											<input name="telephone" type="text" value="${user.telephone}" class="textinput" />
										</td>
									</tr>
									<tr>
										<td style="text-align:right">&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
								<p style="text-align:center">
									<input name="submit" type="image" 
									src="${pageContext.request.contextPath }/client/images/botton_gif_025.gif" width="140" height="35" />
									<!-- 
									<a href="${pageContext.request.contextPath }/changeUser">
										<img src="${pageContext.request.contextPath }/client/images/botton_gif_025.gif" border="0" width="140" height="35"/>
									</a>
									-->
								</p>
								<p style="text-align:center">&nbsp;</p>
							</td>
						</tr>
					</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>