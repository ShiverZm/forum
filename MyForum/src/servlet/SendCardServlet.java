package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Card;
import beans.UserTable;
import dao.CardDao;
import dao.SectionDao;
import dao.UserTableDao;

/**
 * �ӷ�������������������ݿ��������ʵ�ַ�������
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class SendCardServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�������л�ȡ����
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int sectionId = Integer.parseInt(request.getParameter("select_section"));
		//��session�л�ȡ��ǰ�û�����
		UserTable u = (UserTable) request.getSession().getAttribute("user");
		//�����������Ӧ���ݿ�
		Card c = new Card();
		c.setUserTable(new UserTableDao().queryUser(u.getUsername()));
//		c.setNameId(u.getId());
		c.setName(u.getUsername());
		c.setContents(content);
		c.setDate(new Date(System.currentTimeMillis()));
		c.setTitle(title);
		c.setSection(new SectionDao().querySection(sectionId));
//		c.setSectionId(sectionId);
		c.setReplyId(0);
		
		if(new CardDao().insertCard(c)) {
			response.sendRedirect("user/send_success.jsp");
		} else {
			request.getRequestDispatcher("user/send_card.jsp").forward(request, response);
		}
	}

}
