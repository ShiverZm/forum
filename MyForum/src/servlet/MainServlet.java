package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Card;
import beans.Section;
import beans.UserTable;
import dao.CardDao;
import dao.SectionDao;

/**
 * ���ݰ��id�����ݿ��ȡ����ʵ�ֲ鿴���ӹ��ܣ�0��ȫ����
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SectionDao sd = new SectionDao();
		CardDao cd = new CardDao();
		int sectionId = Integer.parseInt(request.getParameter("sectionId"));
		//���ݰ��id��ѯ����
		int host = sd.querySectionHost(sectionId);
		//���ݰ��id��ѯ����
		if(sectionId == -1) {
			//��ѯ��������
			ArrayList<Card> ac = cd.queryCard(0);
			//��ѯ���а������
			ArrayList<Section> as = sd.querySection();
			//��session�д���ac,as
			HttpSession session = request.getSession();
			session.setAttribute("card_list", ac);
			session.setAttribute("section_list", as);		
			//��session�д���sectionId��host
			session.setAttribute("sectionId", sectionId);
			session.setAttribute("host", host);
		} else {
			//���ݰ��id��ѯ����
			ArrayList<Card> ac = cd.querySectionCard(sectionId);
			//��session�д���ac,as
			HttpSession session = request.getSession();
			session.setAttribute("card_list", ac);
			//��session�д���sectionId��host
			session.setAttribute("sectionId", sectionId);
			session.setAttribute("host", host);
		}
		UserTable u = (UserTable) request.getSession().getAttribute("user");
		//����Ȩ����ת��ҳ
		if(u != null && u.getPower() == 1)
			response.sendRedirect("user_manage/main.jsp");
		else
			response.sendRedirect("user_common/main.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
