package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CardDao;


/**
 * ʵ���ö�����
 * @author ������
 *
 */
@SuppressWarnings("serial")
public class KeepTopServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ������id
		int id = Integer.parseInt(request.getParameter("id"));
		
		CardDao cd = new CardDao();
		cd.updateTop(id, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
		response.sendRedirect("MainServlet?sectionId=" + request.getParameter("sectionId"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
