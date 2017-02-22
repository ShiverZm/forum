package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CardDao;
import dao.SectionDao;
import beans.Card;
import beans.Section;
import beans.UserTable;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�������л�ȡ����
		String key = request.getParameter("key");
		CardDao cd = new CardDao();
		SectionDao sd = new SectionDao();
		//��ѯ���ӺͰ����Ϣ
		ArrayList<Card> ac = cd.queryByKey(key);
		ArrayList<Section> as = sd.querySection();
		//���ݰ��id��ѯ����
		int host = sd.querySectionHost(-1);
		
		HttpSession session = request.getSession();
		//��session�д���ac,as
		session.setAttribute("card_list", ac);
		session.setAttribute("section_list", as);	
		//��session�д���sectionId��host
		session.setAttribute("sectionId", -1);
		session.setAttribute("host", host);
		
		UserTable u = (UserTable) request.getSession().getAttribute("user");
		System.out.print(u);
		//����Ȩ����ת��ҳ
		if(u != null && u.getPower() == 1)
			response.sendRedirect("user_manage/main.jsp");
		else
			response.sendRedirect("user_common/main.jsp");
	}

}
