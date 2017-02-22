package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CardDao;
import dao.SectionDao;
import dao.UserTableDao;
import beans.Card;
import beans.UserTable;

/**
 * ����DB�෽�������ݿ����ָ�����ӵĻظ�����
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class ReplyCardServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�������л�ȡ����
		String reply = request.getParameter("reply");
		String replyId = request.getParameter("replyId");
		int sectionId = Integer.parseInt(request.getParameter("sectionId"));
		//��session�л�ȡ��ǰ�û�����
		UserTable u = (UserTable) request.getSession().getAttribute("user");
		//�����������Ӧ���ݿ�
		Card c = new Card();
		c.setUserTable(new UserTableDao().queryUser(u.getUsername()));
//		c.setNameId(u.getId());
		c.setName(u.getUsername());
		c.setContents(reply);
		c.setDate(new Date(System.currentTimeMillis()));
		c.setTitle("�ظ�");
		c.setSection(new SectionDao().querySection(sectionId));
//		c.setSectionId(sectionId);
		c.setReplyId(Integer.parseInt(replyId));
		
		if(new CardDao().insertCard(c)) {
			response.sendRedirect("user/send_success.jsp");
		} else {
			request.getRequestDispatcher("user/reply.jsp").forward(request, response);
		}
	}

}
