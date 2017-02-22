package action;

import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;

@SuppressWarnings("serial")
public class DeleteCardAction extends ActionSupport{
	
	private String id;
	private String path;
	private String sectionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public void write() throws Exception {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = ServletActionContext.getRequest();;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		InputStream inputStream = request.getInputStream();
//		byte[] b = new BufferedReader(new InputStreamReader(inputStream)).readLine().getBytes();
		byte[] b = IOUtils.toByteArray(inputStream);
		String str = new String(b,"UTF-8");
		str = str.substring(str.indexOf("{"),str.lastIndexOf("}")+1);
		JSONObject j = (JSONObject) JSONObject.parse(str);
		
		int id_ = Integer.parseInt(j.getString("id"));
		
		PrintWriter pw = response.getWriter();
		
//		int id_ = Integer.parseInt(id);
		CardDao cd = new CardDao();
		if(cd.deleteCard(id_)) {//ɾ�����Ӻͱ����ӵĻظ�
			j = new JSONObject();
			j.put("result", "success");
//			response.sendRedirect(path + "?sectionId=" + sectionId);
//			return Action.SUCCESS;
		} else {
			j = new JSONObject();
			j.put("result", "error");
		}
		pw.write(j.toJSONString());
		pw.flush();
		pw.close();
//		return Action.ERROR;
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}
	
}
