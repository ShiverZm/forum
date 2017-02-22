package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserTableDao;


/**
 * ���ݹ����û����洫�����û�idɾ���û���������
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class DeleteUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������ҳ���ȡ�û�id
		int id = Integer.parseInt(request.getParameter("id"));
		
		UserTableDao ud = new UserTableDao();
		
		if(ud.deleteUser(id)) {//��Dao��ķ���ɾ�����Ͱ���Ӧ����
			response.sendRedirect("ChangeUserServlet");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
