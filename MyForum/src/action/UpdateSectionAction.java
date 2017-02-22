package action;

import java.util.Map;


import beans.Section;
import beans.UserTable;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.SectionDao;
import dao.UserTableDao;

@SuppressWarnings("serial")
public class UpdateSectionAction extends ActionSupport {
	
	private String name;
	private String hostName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		SectionDao sd = new SectionDao();
		UserTableDao ud = new UserTableDao();
		
//		HttpSession session = request.getSession();
		Map session = (Map) ActionContext.getContext().get("session");
		
		Section s = sd.querySection(name);
		
		if(name.equals("")) {
			session.put("No_user", "������������");
		} else if(hostName.equals("")) {//���������Ϊ""�����ð���Ϊ0
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
						
		} else {
			//�����û�����ѯ�û����ж��û��Ƿ���ڣ���������������ת����ChangeSectionServlet����ʾ����
			UserTable user = ud.queryUser(hostName);
			if(user != null && s == null) {
				//����section���󲢲������ݿ�
				Section section = new Section();
				section.setName(name);
				section.setHost(user.getId());
				sd.insertSection(section);
				
			} else if(user == null) {
				session.put("No_user", "�����ڸ��û���");
			} else {
				//�޸İ���
				sd.updateSection(s.getId(),user.getId());
			}
		}
//		response.sendRedirect("ChangeSectionServlet");
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}

}
