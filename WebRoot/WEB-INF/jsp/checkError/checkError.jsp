<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>报错</title>
    
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
		<a href="${pageContext.request.contextPath }?FromUserName=${FromUserName}" data-icon="Search">查询</a>
		<h4>报告错误</h4>
	</div>
	<div data-role="content">

		<c:choose>  
		   <c:when test="${jieshouOK!=null }"> 
		    ${jieshouOK }
		   </c:when>  
		   <c:otherwise> 
			   <form action="${pageContext.request.contextPath }/checkError" method="post">
			   <c:if test="${jieshouFalse!=null }">
			   	<label style="color: red;">${jieshouFalse }</label>
			   	<br>
			   	<br>
			   </c:if>
				<label>姓名：</label>
				<input type="text" name="studentName">
				<label>学号：</label>
				<input type="text" name="studentid">
				<label>联系方式：</label>
				<input type="text" name="studentPhone">
				<label>内容：</label>
				<input type="text" name="content">
				<input type="hidden" value="${FromUserName }" name="FromUserName">
				<br>
				<br>
				<input type="submit" value="提交">
			  </form>
		   </c:otherwise>  
		</c:choose>
		

	</div>

	<div data-role="footer" data-position="fixed">
		<h4>copyright@chengfan2016</h4>
	</div>
  </body>
</html>
