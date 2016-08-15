<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML ">
<html>
<head>
<title>课表查询</title>

<meta name="format-detection" content="telephone=no" />
<meta name="msapplication-tap-highlight" content="no" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width" />

<script src="./js/jquery-mobile/jquery-1.8.3.min.js"></script>
<script src="./js/jquery-mobile/jquery.mobile-1.3.0.min.js"></script>
<link rel="stylesheet"
	href="./js/jquery-mobile/jquery.mobile.structure-1.3.0.min.css" />
<link rel="stylesheet"
	href="./js/jquery-mobile/jquery.mobile.theme-1.3.0.min.css" />

</head>

<body>
	<div data-role="header" data-position="fixed">
		<a href="${pageContext.request.contextPath }?FromUserName=${FromUserName}" data-icon="Back">返回</a>
		<h4>成绩查询</h4>
		<a href="${pageContext.request.contextPath }/checkError?FromUserName=${FromUserName}"
			data-icon="info">报错</a>
	</div>
	<div data-role="content">

		<form action="${pageContext.request.contextPath }/queryTable"
			method="post" data-ajax=“false”>
			<label style="width: 100%;text-align: center;">欢迎你：<c:if
					test="${!empty FromUserName }">
			 ${FromUserName }
			</c:if></label> <br>
			<br>
			<c:if test="${!empty infoError}">
				<label>${infoError }</label>
				<br>
				<br>
			</c:if>
			<label>学号：</label> <input type="text" data-icon="info"
				name="studentID"> <label>密码: </label> <input type="password"
				name="ycPassword"> <label>学期: </label> <select
				name="Cbo_Xueqi">
				<option>2015/2016(2)</option>
				<option>2015/2016(1)</option>
				<option>2014/2015(2)</option>
				<option>2014/2015(1)</option>
				<option>2013/2014(2)</option>
				<option>2013/2014(1)</option>
			</select> <label>查询方式: </label> <select name="queryMethod">
				<option>按课表查询</option>
				<option>按课程查询</option>
			</select> <input type="hidden"
				value="<%=session.getAttribute("FromUserName")%>"
				name="FromUserName" /> <br> <input type="submit" value="查询"
				data-icon="Check" data-iconpos="top">

		</form>

	</div>

	<div data-role="footer" data-position="fixed">
		<h4>copyright@chengfan2016</h4>
	</div>
</body>
</html>
