<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
  <head>

    <title>已经绑定</title>

	<meta name="format-detection" content="telephone=no" />
    <meta name="msapplication-tap-highlight" content="no" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width" />
    
	<script	src="./js/jquery-mobile/jquery-1.8.3.min.js"></script>
	<script src="./js/jquery-mobile/jquery.mobile-1.3.0.min.js"></script>
	<link rel="stylesheet" href="./js/jquery-mobile/jquery.mobile.structure-1.3.0.min.css" />
	<link rel="stylesheet" href="./js/jquery-mobile/jquery.mobile.theme-1.3.0.min.css" />
  </head>
  
  <body>
    <div data-role="header" data-position="fixed">
		<a data-rel="back" data-icon="Back">返回</a>
		<h4>绑定结果</h4>
		<a href="${pageContext.request.contextPath }/checkError?FromUserName=${FromUserName}"
			data-icon="info">报错</a>
	</div>
	<div data-role="content">

		<br>
		<label>
			<c:if test="${okInfo != null }">
				${okInfo }
			</c:if>
			<c:if test="${okInfo == null }">
				你已经进行过原创绑定了，点击下面的按钮可以重新绑定或者解绑
			</c:if>
		</label>
		<br>
		<br>
		<c:if test="${FromUserName != null }">
			<form action="${pageContext.request.contextPath }/bangding">
				<input type="hidden" value="${FromUserName_all }" name="FromUserName">
				<input type="submit" value="重新绑定">
			</form>
		</c:if>
		

		<c:if test="${okInfo == null }">
			<br>
			<form action="${pageContext.request.contextPath }/jiebang">
				<input type="hidden"
					value="${FromUserName }" name="FromUserName">
				<input type="submit" value="解除绑定">
			</form>
		</c:if>
		
		
	</div>

	<div data-role="footer" data-position="fixed">
		<h4>copyright@chengfan2016</h4>
	</div>
  </body>
</html>
