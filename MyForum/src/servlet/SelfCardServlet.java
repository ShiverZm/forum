package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CardDao;
import beans.Card;
import beans.UserTable;

/**
 * �����û�id�Ͱ��id�����ݿ��ȡ����ʵ�ֲ鿴���ӹ��ܣ����idΪ0��ȫ����
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class SelfCardServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int sectionId = Integer.parseInt(request.getParameter("sectionId"));
		//��session��ȡ�û�id
		HttpSession session = request.getSession();
		int nameId = ((UserTable)session.getAttribute("user")).getId();
		
		CardDao cd = new CardDao();
		//���id��ѯ����
		if(sectionId == -1) {
			//��ѯ��������
			ArrayList<Card> arr = cd.querySelfCard(nameId);
			//��session�д���arr
			session.setAttribute("self_card_list", arr);
			//��session�д���sectionId
			session.setAttribute("sectionId", sectionId);
			
			response.sendRedirect("user/self_message.jsp");
		} else {
			//���ݰ��id��ѯ����
			ArrayList<Card> arr = cd.querySection_SelfCard(nameId, sectionId);
			//��session�д���arr
			session.setAttribute("self_card_list", arr);
			//��session�д���sectionId
			session.setAttribute("sectionId", sectionId);
			
			response.sendRedirect("user/self_message.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
