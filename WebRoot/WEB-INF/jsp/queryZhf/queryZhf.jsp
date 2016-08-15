<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML">
<html>
<head>
<title>综合分查询</title>
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
		<h4>综合分查询</h4>
		<a href="${pageContext.request.contextPath }/checkError?FromUserName=${FromUserName}"
			data-icon="info">报错</a>
	</div>
	<div data-role="content">
		<c:if test="${info!=null }">
		${info }
		</c:if>
	</div>

	<div data-role="footer" data-position="fixed">
		<h4>copyright@chengfan2016</h4>
	</div>
</body>
</html>
