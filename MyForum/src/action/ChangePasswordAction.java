package action;

import java.util.Map;


import beans.UserTable;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserTableDao;

@SuppressWarnings("serial")
public class ChangePasswordAction extends ActionSupport {
	
	private String old_password;
	private String new_password;
	private String new_password_repeat;
	private String path;
	
	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getNew_password_repeat() {
		return new_password_repeat;
	}

	public void setNew_password_repeat(String new_password_repeat) {
		this.new_password_repeat = new_password_repeat;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//��ȡsession����ȡ��user
//		HttpSession session = request.getSession();
		Map session = (Map) ActionContext.getContext().get("session");
		UserTable u = (UserTable)session.get("user");
		if(old_password.equals("") || new_password.equals("") ||new_password_repeat.equals("")) {
//			addFieldError("error_message", "����д������");
			return Action.ERROR;
		}
		if(!new_password.equals(new_password_repeat)) {
//			addFieldError("error_message", "��ȷ�����룡");
			return Action.ERROR;
		}
			//�Ƚ�ԭ�����Ƿ���ͬ���޸����ݿ⣬����ص��޸��������
		if(old_password.equals(u.getPassword())) {
			if(new UserTableDao().updateUserPassword(new_password, u.getId())) {
				//��һ�佫session�е�user������ı�
				u.setPassword(new_password);
//				response.sendRedirect("user/change_sucess.jsp");
				return Action.SUCCESS;
			} else {
//				addFieldError("error_message", "�޸Ĵ���");
//				request.getRequestDispatcher("person_change_password.jsp").forward(request, response);
				return Action.ERROR;
			}
		} else {
//			addFieldError("error_message", "ԭ�������");
//			request.getRequestDispatcher("person_change_password.jsp").forward(request, response);
			return Action.ERROR;
		}
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}

}
