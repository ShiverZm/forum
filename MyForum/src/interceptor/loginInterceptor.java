package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class loginInterceptor implements Interceptor{

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("�����������٣�");
	}

	public void init() {
		// TODO Auto-generated method stub
		System.out.println("�������Ѽ��أ�");
	}

	// �ж�session�������Ƿ������Ϊuser�Ķ����������˵����¼���ˣ�û�д���˵���û�û�е�¼
	// �ù����������ñ�֤����û�û�е�¼���Ͳ�����ʾ���ԡ��������
	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		Map session = (Map) ActionContext.getContext().get("session");
		if (session.get("user") != null) {//�ѵ�¼
			String result = arg0.invoke();//����Ƿ������������еĻ������������µ���������û����ִ��action��ҵ���߼�
			return result;
		} else {//û��¼
			System.out.println("û�е�¼����");
			return Action.ERROR;
		}
	}
}
