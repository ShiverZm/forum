package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserTable;
import dao.UserTableDao;

/**
 * �޸��û�����
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class ChangePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�������л�ȡ����
		String old_password = request.getParameter("old_password");
		String new_password = request.getParameter("new_password");
		//��ȡsession����ȡ��user
		HttpSession session = request.getSession();
		UserTable u = ((UserTable)(session.getAttribute("user")));
		//�Ƚ�ԭ�����Ƿ���ͬ���޸����ݿ⣬����ص��޸��������
		if(old_password.equals(u.getPassword())) {
			if(new UserTableDao().updateUserPassword(new_password, u.getId())) {
				//��һ�佫session�е�user������ı�
				u.setPassword(new_password);
				response.sendRedirect("user/change_sucess.jsp");
			} else {
				request.getRequestDispatcher("person_change_password.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("person_change_password.jsp").forward(request, response);
		}
	}

}
