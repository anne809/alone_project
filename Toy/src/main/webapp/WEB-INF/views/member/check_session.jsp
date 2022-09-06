<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ page session="false" %>
<%
 HttpSession sess = request.getSession(false);//세션이 없으면 새로 자동 생성 못하게 한다.

 if(sess==null){                //세션이 없는 경우. 혹은 해제된 경우
%>
 <script type="text/javascript">
  location.href="./home";     //세션 해제된 경우 메인 페이지로 이동.
 </script>
<%}else{%>                          //세션이 있는 경우
 <script type="text/javascript">
  location.href="./main";  //간략한 사용자의 정보를 표시 할수 있도록 페이지를 이동. 
 </script>
<%}%>

<meta charset="utf-8">
<title>...check...</title>
</head>
<body>

</body>
</html>