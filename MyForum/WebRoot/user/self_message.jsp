<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
     <title>�ҵ�����</title>
  </head>
  <body>
  	ѡ���飺<a href="selfCard?sectionId=-1">ȫ��</a>
  	<c:forEach var="section" items="${sessionScope.section_list}">
  		<a href="selfCard?sectionId=${section.id}">${section.name}</a>
	</c:forEach>
	<a href="selfCard?sectionId=0">����</a>
	<br>
  	<a  href="user/send_card.jsp">��Ҫ����</a>
  	<a href="main?sectionId=-1">�ص���ҳ</a>
	<table border="1">
		<tr>
			<th>����</th>
			<th>����</th>
			<th>����</th>
			<th>ʱ��</th>
		</tr>
		<c:forEach var="card" items="${sessionScope.self_card_list}">
			<tr>
				<td>${card.name}</td>
				<td>${card.title}</td>
				<td>${card.contents}</td>
				<td>${card.date}</td>
				<td><a href="lookReply?replyId=${card.id}">�鿴�ظ�</a></td>
				<td><a href="deleteCard?id=${card.id}&sectionId=${sessionScope.sectionId}&path=selfCard">ɾ��</a></td>
			</tr>
		</c:forEach>
	</table>
  </body>
</html>