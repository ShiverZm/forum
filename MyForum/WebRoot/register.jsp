<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
     <title>��̳ע��</title>
  </head>
  <body>
	<s:fielderror />
  	<form action="register" method="post">
  		<table>
  			<caption>�û�ע��</caption>
  			<tr>
  				<td>�û�����</td>
				<td><input type="text" name="username" size="20"/></td>
  			</tr>
  			<tr>
  				<td>���룺</td>
  				<td><input type="password" name="userpassword" size="20"/></td>
  			</tr>
  			<tr>
  				<td>ȷ�����룺</td>
  				<td><input type="password" name="userpassword2" size="20"/></td>
  			</tr>
  		</table>
  		<input type="submit" value="ע��"/>
		<input type="reset" value="����"/>
		<a  href="login.jsp">��Ҫ��½</a>
  	</form>
  </body>
</html>