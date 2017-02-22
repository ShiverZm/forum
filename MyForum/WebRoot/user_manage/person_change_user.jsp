<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
     <title>�����û�</title>
  </head>
  <body>
  	<a href="person_change_password.jsp">�޸�����</a>
  	<a href="../changeUser">�����û�</a>
  	<a href="../changeSection">������</a>
  	<a href="../main?sectionId=-1">�ص���ҳ</a>
  	<br>
  	<table border="1">
  		<caption>�����û�</caption>
  		<tr>
  			<th>�û���</th>
  			<th>Ȩ��</th>
  		</tr>
  		<c:forEach items="${sessionScope.user_list}" var="user">
  			<tr>
  				<td>${user.username}</td>
  				<td>
	  				<c:if test="${user.power == 0}">
	  					��ͨ
	  				</c:if>
	  				<c:if test="${user.power == 1}">
	  					����Ա
	  				</c:if>
	  				<c:if test="${user.power == 2}">
	  					����
	  				</c:if>
	  			</td>
  				<td><a href="../deleteUser?id=${user.id}">ɾ��</a></td>
  				<c:if test="${user.power == 1}">
  					<td><a href="../updateUser?power=0&id=${user.id}">��ȥ����ԱȨ��</a></td>
  				</c:if>
  				<c:if test="${user.power != 1}">
  					<td><a href="../updateUser?power=1&id=${user.id}">��Ϊ����Ա</a></td>
  				</c:if>
  			</tr>
  		</c:forEach>
  	</table>
  	<br>
  	${sessionScope.error_m}
  	<%session.setAttribute("error_m", ""); %>
  	<form action="../addUser" method="post">
  		�û�����<input type="text" name="name" size="18"/>
  		���룺<input type="password" name="password" size="18"/>
  		<br>
  		<br>
  		<input type="submit" value="����û�"/>
  	</form>
  </body>
</html>