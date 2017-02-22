package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SectionDao;
import dao.UserTableDao;
import beans.Section;
import beans.UserTable;

/**
 * ���°�����ݱ���û�иð�������Ӳ���ð��
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class UpdateSectionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�������л�ȡ����
		String name = request.getParameter("name");
		String hostName = request.getParameter("hostName");
		
		SectionDao sd = new SectionDao();
		UserTableDao ud = new UserTableDao();
		
		HttpSession session = request.getSession();
		
		Section s = sd.querySection(name);
		
		//���������Ϊ""�����ð���Ϊ0
		if(hostName == "") {
			if(s == null) {
				//����section���󲢲������ݿ�
				Section section = new Section();
				section.setName(name);
				section.setHost(0);
				sd.insertSection(section);
			} else {
				//�޸İ���Ϊ��
				sd.updateSection(s.getId(),0);
			}
			
			session.setAttribute("No_user", null);
			
		} else {
			//�����û�����ѯ�û����ж��û��Ƿ���ڣ���������������ת����ChangeSectionServlet����ʾ����
			UserTable user = ud.queryUser(hostName);
			if(user != null && s == null) {
				//����section���󲢲������ݿ�
				Section section = new Section();
				section.setName(name);
				section.setHost(user.getId());
				sd.insertSection(section);
				
				session.setAttribute("No_user", null);
			} else if(user == null) {
				session.setAttribute("No_user", "�����ڸ��û���");
			} else {
				//�޸İ���
				sd.updateSection(s.getId(),user.getId());
			}
		}
		response.sendRedirect("ChangeSectionServlet");
	}

}
