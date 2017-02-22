package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserTableDao;
import beans.UserTable;

/**
 * �ӹ����û������ȡ��������û������ݿ�
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class AddUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�������л�ȡ����
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserTableDao ud = new UserTableDao();
		//��ѯҪ��ӵ��û��Ƿ��Ѵ���
		UserTable user = ud.queryUser(name);
		if(user == null) {
			UserTable u = new UserTable();
			u.setUsername(name);
			u.setPassword(password);
			u.setPower(0);
			ud.insertUser(u);
			request.setAttribute("error_m", "");
			response.sendRedirect("ChangeUserServlet");
		} else {
			request.setAttribute("error_m", "�û����Ѵ��ڣ�");
			request.getRequestDispatcher("ChangeUserServlet").forward(request, response);
		}
	}
	
}
