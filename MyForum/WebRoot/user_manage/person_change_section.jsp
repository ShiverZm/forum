<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
     <title>������</title>
  </head>
  <body>
  	<a href="person_change_password.jsp">�޸�����</a>
  	<a href="../changeUser">�����û�</a>
  	<a href="../changeSection">������</a>
  	<a href="../main?sectionId=-1">�ص���ҳ</a>
  	<br>
  	<table border="1">
  		<tr>
  			<th>���</th>
  			<th>����</th>
  		</tr>
  		<c:forEach var="section" items="${sessionScope.section_list}">
			<tr>
				<td>${section.name}</td>
				<td>${section.hostName}</td>
				<td><a href="../deleteSection?id=${section.id}">ɾ��</a></td>
			</tr>
		</c:forEach>
  	</table>
  	<br>
  	${sessionScope.No_user}
  	<%session.setAttribute("No_user", ""); %>
  	<form action="../updateSection" method="post">
  		�������<input type="text" name="name" size="18"/>
  		��������<input type="text" name="hostName" size="18"/>
  		<br>
  		<br>
  		<input type="submit" value="���Ӱ��/�޸İ���"/>
    </form>
  </body>
</html>