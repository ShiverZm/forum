package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CardDao;


/**
 * ����DB�෽��ɾ��ָ����������ظ�����ʵ��ɾ������
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class DeleteCardServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		CardDao cd = new CardDao();
		if(cd.deleteCard(id)) {//ɾ�����Ӻͱ����ӵĻظ�
			response.sendRedirect(request.getParameter("path") + "?sectionId=" + request.getParameter("sectionId"));
		}
	}
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
