package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserTableDao;
import beans.UserTable;

/**
 * ��ע��jspҳ����ղ�������DB��ķ���ʵ��ע�Ṧ��
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�������л�ȡ����
		String username = request.getParameter("username");
		String userpassword = request.getParameter("userpassword");
		String userpassword2 = request.getParameter("userpassword2");
		//�����û����Ӧ���ݿ�
		UserTable u = new UserTable();
		u.setUsername(username);
		u.setPassword(userpassword);
		u.setPower(0);
		
		UserTableDao ud = new UserTableDao();
		
		if(username=="" || userpassword=="" || userpassword2=="")
			request.setAttribute("error_message", "����д������");
		else if(!userpassword.equals(userpassword2))
			request.setAttribute("error_message", "��������ͬ���룡");
		else if(ud.queryUser(username)==null && ud.insertUser(u)) {
			response.sendRedirect("login.jsp");
			return;
		} else{
			request.setAttribute("error_message", "ע��ʧ�ܣ���Ϊ�û��Ѵ��ڣ�");
		}
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

}
