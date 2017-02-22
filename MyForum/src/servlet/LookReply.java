package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Card;
import dao.CardDao;

/**
 * ����DB�෽�����ݻظ�������id��ѯָ�����ӵĻظ�����ʵ�ֲ鿴�ظ�����
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class LookReply extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��request��ȡ����id��Ϊ�ظ����ӵĻظ�id
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		
		CardDao cd = new CardDao();
		HttpSession session = request.getSession();
		//��������id��ѯԭ���Ӳ�����session
		Card c = cd.queryCardById(replyId);
		session.setAttribute("card", c);
		//��������id��Ϊ�ظ�id��ѯ�ظ����Ӳ�����session
		ArrayList<Card> arr = cd.queryCard(replyId);
		session.setAttribute("reply_list", arr);
		
		response.sendRedirect("user/look_reply.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
