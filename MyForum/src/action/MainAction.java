package action;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import beans.Card;
import beans.Section;
import beans.UserTable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.CardDao;
import dao.SectionDao;

@SuppressWarnings("serial")
public class MainAction extends ActionSupport {
	
	private String sectionId;
	private String sec;
	
	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public void write() throws Exception {
		// TODO Auto-generated method stub
		
		SectionDao sd = new SectionDao();
		CardDao cd = new CardDao();
		
		HttpServletRequest request = ServletActionContext.getRequest();;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("text/html;charset=UTF-8");
		InputStream inputStream = request.getInputStream();
//		byte[] b = new BufferedReader(new InputStreamReader(inputStream)).readLine().getBytes();
		byte[] b = IOUtils.toByteArray(inputStream);
		String str = new String(b,"UTF-8");
		str = str.substring(str.indexOf("{"),str.lastIndexOf("}")+1);
		JSONObject j = (JSONObject) JSONObject.parse(str);
		sec = j.getString("section");
		
		Section section = sd.querySection(sec);
		
		PrintWriter pw = response.getWriter();
//		int sectionId_ = Integer.parseInt(sectionId);
//		int sectionId = Integer.parseInt(request.getParameter("sectionId"));
		//���ݰ��id��ѯ����
//		int host = sd.querySectionHost(sectionId_);
		//���ݰ��id��ѯ����
		if(sec.equals("ȫ��")) {
			//��ѯ��������
			ArrayList<Card> ac = cd.queryCard(0);
			//��ѯ���а������
			ArrayList<Section> as = sd.querySection();
//			JSONArray ja1 = new JSONArray((List)as);
//			JSONArray ja2 = new JSONArray((List)ac);
			j = new JSONObject();
			j.put("section", as);
			j.put("card", ac);
//			System.out.print(((Card)j.getJSONArray("card").get(0)).getName());
			pw.println(j.toString());
			pw.flush();
			pw.close();
			//��session�д���ac,as
//			Map session = (Map) ActionContext.getContext().get("session");
//			session.put("card_list", ac);
//			session.put("section_list", as);		
			//��session�д���sectionId��host
//			session.put("sectionId", sectionId);
//			session.put("host", host);
		} else {
			//���ݰ��id��ѯ����
			ArrayList<Card> ac = cd.querySectionCard(section.getId());
			j = new JSONObject();
			j.put("card", ac);
			pw.println(j.toJSONString());
			pw.flush();
			pw.close();
			//��session�д���ac,as
//			Map session = (Map) ActionContext.getContext().get("session");
//			session.put("card_list", ac);
			//��session�д���sectionId��host
//			session.put("sectionId", sectionId);
//			session.put("host", host);
		}
//		Map session = (Map) ActionContext.getContext().get("session");
//		UserTable u = (UserTable) session.get("user");
		//����Ȩ����ת��ҳ
//		if(u != null && u.getPower() == 1)
//			response.sendRedirect("user_manage/main.jsp");
//			return "success_manage";
//		else
//			response.sendRedirect("user_common/main.jsp");
//			return "success_common";
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}
}
