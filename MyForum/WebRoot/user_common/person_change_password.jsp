<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
     <title>�޸�����</title>
  </head>
  <body>
  	<a href="person_change_password.jsp">�޸�����</a>
  	<a href="../main?sectionId=-1">�ص���ҳ</a>
  	<s:fielderror fieldName="error_message"/>
  	<form action="../changePassword?path=user_common" method="post">
  		<table border="1">
  			<tr>
  				<td>ԭ���룺</td>
  				<td><input type="password" name="old_password"/></td>
  			</tr>
  			<tr>
  				<td>�����룺</td>
  				<td><input type="password" name="new_password"/></td>
  			</tr>
  			<tr>
  				<td>ȷ�����룺</td>
  				<td><input type="password" name="new_password_repeat"/></td>
  			</tr>
  			<tr>
  				<td><input type="submit" value="ȷ���޸�"/></td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>