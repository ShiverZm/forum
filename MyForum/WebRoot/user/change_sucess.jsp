<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="beans.UserTable"%>
<html>
	<head>
     <title>�޸ĳɹ�</title>
  </head>
  <body>
  	�޸ĳɹ�,���
  	<c:if test="${sessionScope.user.power==0}">
  		<a href="../user_common/person_change_password.jsp">����</a>
  	</c:if>
  	<c:if test="${sessionScope.user.power==1}">
  		<a href="../user_manage/person_change_password.jsp">����</a>
  	</c:if>
  	���ظ������ģ�
  </body>
</html>