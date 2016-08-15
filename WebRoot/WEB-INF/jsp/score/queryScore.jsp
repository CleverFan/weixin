<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML">
<html>
<head>

<title>成绩</title>
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
		<a href="${pageContext.request.contextPath }?FromUserName=${FromUserName}" data-icon="Search">其他查询</a>
		<h4>成绩查询</h4>
		<a href="${pageContext.request.contextPath }/checkError?FromUserName=${FromUserName}"
			data-icon="info">报错</a>
	</div>
	<div data-role="content">

		<%-- <table width="100%" border=1>
			<c:if test="${queryScore!=null }">
			${queryScore }
		</c:if>
		</table> --%>
		
		<table border="1"  data-mode="reflow" class="ui-responsive table-stroke">
			<thead>
				<c:if test="${queryScore!=null }">
					${queryScore }
				</c:if>
			</thead>
		</table>
	</div>

	<div data-role="footer" data-position="fixed">
		<h4>copyright@chengfan2016</h4>
	</div>
</body>
</html>
