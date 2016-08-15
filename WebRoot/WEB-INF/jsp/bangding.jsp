<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE>
<html>
  <head> 
    <title>原创绑定</title>

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
		<h4>原创绑定</h4>
		<a href="${pageContext.request.contextPath }/checkError?FromUserName=${FromUserName}"
			data-icon="info">报错</a>
	</div>
			
 	<div data-role="content">
 	
		<c:choose>  
		   <c:when test="${BangdingOK!=null }"> 
		    <label>${BangdingOK }</label>
		   </c:when>  
		   <c:otherwise> 
		   		
		   		<form action="${pageContext.request.contextPath }/submit" method="post" data-ajax=“false”>
				  	<label>欢迎你：${FromUserName }</label><br><br>
				  	<c:if test="${infoError!=null }">
				  		<label >${infoError }</label><br><br>
				  	</c:if>
					<c:if test="${feifa!=null }">
						${feifa }
					</c:if>
				  	<label>学号：</label>
				    <input type="text" data-icon="info" name="studentID">
				    <label>密码</label>
				    <input type="password" name="ycPassword">	
				    
				    <input type="hidden" value="${FromUserName }" name="FromUserName"/>   
				    <br> 
				    <input type="submit" value="绑定" data-icon="Check" data-iconpos="top"data-iconpos="top">
				    
				  </form>
		   </c:otherwise>  
		</c:choose>
	  	 
	  
	  </div>
  
  <div data-role="footer" data-position="fixed">
    <h4>copyright@chengfan2016</h4>
  </div>
  </body>
</html>
