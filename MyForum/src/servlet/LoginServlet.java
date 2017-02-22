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
 * �ӵ�¼jspҳ����ղ�������DB��ķ���ʵ�ֵ�¼����
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�������л�ȡ����
		String username = request.getParameter("username");
		String userpassword = request.getParameter("userpassword");
		
		UserTableDao ud = new UserTableDao();
		
		if(username=="" || userpassword=="")
			request.setAttribute("error_massage", "����д����!");
		else if(ud.queryUser(username) != null) {
			//�����ݿ��ȡ�û���Ϣ
			UserTable u = ud.queryUser(username, userpassword);
			if(u != null) {
				//��session���󴫽�u
				HttpSession session = request.getSession();
				session.setAttribute("user", u);
				
				response.sendRedirect("MainServlet?sectionId=-1");
				return;
				
			} else {
				request.setAttribute("error_password", "�û��������");
			}
		} else {
			request.setAttribute("error_username", "�û���������!");
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
