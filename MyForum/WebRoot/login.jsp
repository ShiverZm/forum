<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
     <title>��̳��¼</title>
  </head>
  <body>
  	<s:fielderror fieldName="error_massage"/>
  	<s:fielderror fieldName="error_password"/>
  	<s:fielderror fieldName="error_username"/>
  	<s:form action="login" method="post">
  		<table>
  			<caption>�û���¼</caption>
  			<tr>
  				<td>�û�����</td>
				<td><input type="text" name="username" size="20"/></td>
  			</tr>
  			<tr>
  				<td>���룺</td>
  				<td><input type="password" name="userpassword" size="20"/></td>
  			</tr>
  		</table>
  		<input type="submit" value="��¼"/>
		<input type="reset" value="����"/>
		&nbsp;û���˺ţ�<a href="register.jsp">ע��</a>
		����<a href="main?sectionId=-1">�ο͵�½</a>
  	</s:form>
  </body>
</html>