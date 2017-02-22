package action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import dao.SectionDao;

@SuppressWarnings("serial")
public class DeleteSectionAction extends ActionSupport {
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//������ҳ���ȡ���id
		int id_ = Integer.parseInt(id);
		
		SectionDao sd = new SectionDao();
		
		if(sd.deleteSection(id_)) {//��DB��ķ���ɾ�����Ͱ���Ӧ����
//			response.sendRedirect("ChangeSectionServlet");
			return Action.SUCCESS;
		}
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}

}
