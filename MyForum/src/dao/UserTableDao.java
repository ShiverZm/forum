package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import beans.UserTable;

public class UserTableDao {
	
	/*
	 * ��ѯ�����û��������������û���򷵻�null
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<UserTable> queryUser() {
		Session session = HibernateSessionFactory.getSession();
		//�ɻỰSession���󴴽�һ����ѯQuery���� 
		Query query = session.createQuery("from UserTable");
		ArrayList<UserTable> user = (ArrayList<UserTable>) query.list();
		return user;
	}
	
	/*
	 * �����û����������ѯ�û��Ƿ���ڲ�����User����û���򷵻�null
	 */
	@SuppressWarnings("unchecked")
	public UserTable queryUser(String username, String password) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from UserTable where username=? and password=?");
		query.setString(0, username);
		query.setString(1, password);
		List<UserTable> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else
			return null;
	}
	
	/*
	 * �����û�����ѯ�û��Ƿ���ڲ�����User����û���򷵻�null
	 */
	@SuppressWarnings("unchecked")
	public UserTable queryUser(String username) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from UserTable where username=?");
		query.setString(0, username);
		List<UserTable> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else
			return null;
	}
	
	/*
	 * ���û�������û�������falseΪ���ɹ�������trueΪ�ɹ�
	 */
	public boolean insertUser(UserTable u) {
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(u);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			ts.rollback();
			return false;
		}
	}
	
	/*
	 * ����id��ѯ�û�����
	 */
	@SuppressWarnings("unchecked")
	public String queryUserName(int id) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("select username from UserTable where id=?");
		query.setInteger(0, id);
		List<String> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else
			return null;
	}
	
	/*
	 * �����û�idɾ���û�
	 */
	public boolean deleteUser(int id) {
		Session session = HibernateSessionFactory.getSession();
		UserTable u = (UserTable) session.get(UserTable.class, new Integer(id));
//		ArrayList<Card> cardList = new CardDao().querySelfCard(id);
//		Set<Card> cardList = u.getCardList();
		Transaction ts = session.beginTransaction();
		try {
			session.delete(u);
//			session.delete(cardList);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			ts.rollback();
			return false;
		}
	}
	
	/*
	 * �����û�id�޸�����
	 */
	public boolean updateUserPassword(String password, int id) {
		Session session = HibernateSessionFactory.getSession();
		UserTable u = (UserTable) session.get(UserTable.class, new Integer(id));
		Transaction ts = session.beginTransaction();
		try {
			u.setPassword(password);
			session.saveOrUpdate(u);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			ts.rollback();
			return false;
		}
	}
	
	/*
	 * �����û�id�޸�Ȩ��
	 */
	public boolean updateUserPower(int power, int id) {
		Session session = HibernateSessionFactory.getSession();
		UserTable u = (UserTable) session.get(UserTable.class, new Integer(id));
		Transaction ts = session.beginTransaction();
		try {
			u.setPower(power);
			session.saveOrUpdate(u);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			ts.rollback();
			return false;
		}
	}
	
}
