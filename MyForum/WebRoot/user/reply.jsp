<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
     <title>�ظ�����</title>
  </head>
  <body>
  <s:fielderror fieldName="send_error"/>
  	<form action="../replyCard?replyId=<%=request.getParameter("replyId") %>&sectionId=<%=request.getParameter("sectionId") %>" method="post">
		������ظ����ݣ�
		<input type="text" name="reply"/>
		<input type="submit" value="�ظ�"/>
	</form>
  </body>
</html>