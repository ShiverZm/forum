package action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserTableDao;

@SuppressWarnings("serial")
public class DeleteUserAction extends ActionSupport {
	
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
		//������ҳ���ȡ�û�id
		int id_ = Integer.parseInt(id);
		
		UserTableDao ud = new UserTableDao();
		
		if(ud.deleteUser(id_)) {//��Dao��ķ���ɾ�����Ͱ���Ӧ����
//			response.sendRedirect("ChangeUserServlet");
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
