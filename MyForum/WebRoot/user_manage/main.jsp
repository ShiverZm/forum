<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
     <title>��ҳ</title>
  </head>
  <body>
  	ѡ���飺<a href="main?sectionId=-1">ȫ��</a>
  	<c:forEach var="section" items="${sessionScope.section_list}">
  		<a href="main?sectionId=${section.id}">${section.name}</a>
	</c:forEach>
	<a href="main?sectionId=0">����</a>
  	<br>
  	<a  href="../selfCard?sectionId=-1">�ҷ�������</a>
  	<a  href="../extra?path=user/send_card.jsp">��Ҫ����</a>
  	<a  href="../extra?path=user_manage/person_change_password.jsp">��������</a>
  	<a  href="../outLogin">�˳���½</a>
  	<br>
  	<form action="../search" method="post">
  		<input type="text" name="key" size="18"/>
  		<input type="submit" value="����"/>
  		<br>
  	</form>
	<table border="1">
		<tr>
			<th>����</th>
			<th>����</th>
			<th>����</th>
			<th>ʱ��</th>
		</tr>
		<c:forEach var="card" items="${sessionScope.card_list}">
			<tr>
				<td>${card.name}</td>
				<td>${card.title}</td>
				<td>${card.contents}</td>
				<td>${card.date}</td>
				<td><a href="../lookReply?replyId=${card.id}">�鿴�ظ�</a></td>
				<td><a href="../user/reply.jsp?replyId=${card.id}&sectionId=${card.section.id}">�ظ�</a></td>
				<td><a href="../deleteCard?id=${card.id}&sectionId=${sessionScope.sectionId}&path=main">ɾ��</a></td>
				<td><a href="../keepTop?id=${card.id}&sectionId=${sessionScope.sectionId}">�ö�</a></td>
			</tr>
		</c:forEach>
	</table>
  </body>
</html>