package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SectionDao;


/**
 * ���ݴӽ����ȡ�İ��idʵ�ֹ���Ա��ɾ����鹦�ܣ������������ӣ�
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class DeleteSectionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������ҳ���ȡ���id
		int id = Integer.parseInt(request.getParameter("id"));
		
		SectionDao sd = new SectionDao();
		
		if(sd.deleteSection(id)) {//��DB��ķ���ɾ�����Ͱ���Ӧ����
			response.sendRedirect("ChangeSectionServlet");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
