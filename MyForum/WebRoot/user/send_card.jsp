<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
     <title>��̳����</title>
  </head>
  <body>
  	<s:fielderror fieldName="error_message"/>
  	<form action="../sendCard" method="post">
  		<table>
  			<caption>�û�����</caption>
  			<tr>
  				<td>���⣺</td>
				<td><input type="text" name="title" size="20"/></td>
  			</tr>
  			<tr>
  				<td>���ݣ�</td>
  				<td><input type="text" name="content" size="20"/></td>
  			</tr>
  			<tr>
  				<td>������飺</td>
  				<td>
  					<select name="select_section">
  						<c:forEach var="section" items="${sessionScope.section_list}">
							<option value="${section.id}">${section.name}</option>
						</c:forEach>
  						<option value="0">����</option>
  					</select>
  				</td>
  			</tr>
  		</table>
  		<input type="submit" value="����"/>
		<input type="reset" value="����"/>
  	</form>
  </body>
</html>