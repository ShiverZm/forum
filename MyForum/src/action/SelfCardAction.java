package action;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;






import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import beans.Card;
import beans.Section;
import beans.UserTable;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;
import dao.SectionDao;

@SuppressWarnings("serial")
public class SelfCardAction extends ActionSupport{
	
	private String sectionId;

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
		String sec = j.getString("section");
		String nameId = j.getString("nameId");
		
		int nameId_ = Integer.parseInt(nameId);
		
		PrintWriter pw = response.getWriter();
//		int sectionId_ = Integer.parseInt(sectionId);
		//��session��ȡ�û�id
//		HttpSession session = request.getSession();
//		Map session = (Map) ActionContext.getContext().get("session");
//		int nameId = ((UserTable)session.get("user")).getId();
		
		CardDao cd = new CardDao();
		SectionDao sd = new SectionDao();
		//���id��ѯ����
		if(sec.equals("ȫ��")) {
			//��ѯ��������
			ArrayList<Card> arr = cd.querySelfCard(nameId_);
			//��ѯ���а������
			ArrayList<Section> as = sd.querySection();
			
			j = new JSONObject();
			j.put("section", as);
			j.put("card", arr);
			pw.println(j.toString());
			pw.flush();
			pw.close();
			//��session�д���arr
//			session.put("self_card_list", arr);
			//��session�д���sectionId
//			session.put("sectionId", sectionId);
			
		} else {
			//���ݰ��id��ѯ����
			ArrayList<Card> arr = cd.querySection_SelfCard(nameId_, sd.querySection(sec).getId());
			j = new JSONObject();
			j.put("card", arr);
			pw.println(j.toString());
			pw.flush();
			pw.close();
			//��session�д���arr
//			session.put("self_card_list", arr);
			//��session�д���sectionId
//			session.put("sectionId", sectionId);
			
		}
//		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}
}
