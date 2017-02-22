package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import beans.Section;

public class SectionDao {

	/*
	 *��ѯ���ݿ��������
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Section> querySection() {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Section");
		List<Section> list = query.list();
		make_name(list);
		if (list != null && list.size() > 0) {
			return (ArrayList) list;
		} else
			return null;
	}
	
	/*
	 *���ݰ��id��ѯ���ݿ������
	 */
	@SuppressWarnings("unchecked")
	public int querySectionHost(int id) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("select host from Section where id=?");
		query.setInteger(0, id);
		List<Integer> list = query.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else
			return -1;
	}
	
	/*
	 *���ݰ������ѯ���ݿ���
	 */
	@SuppressWarnings("unchecked")
	public Section querySection(String name) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Section where name=?");
		query.setString(0, name);
		List<Section> list = query.list();
		if (list != null && list.size() > 0) {
			list.get(0).setHostName(new UserTableDao().queryUserName(list.get(0).getHost()));
			return list.get(0);
		} else
			return null;
	}
	
	/*
	 *���ݰ��id��ѯ���ݿ���
	 */
	@SuppressWarnings("unchecked")
	public Section querySection(int id) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Section where id=?");
		query.setInteger(0, id);
		List<Section> list = query.list();
		if (list != null && list.size() > 0) {
			list.get(0).setHostName(new UserTableDao().queryUserName(list.get(0).getHost()));
			return list.get(0);
		} else
			return null;
	}
	
	/*
	 * ����������Ӱ�飬����0Ϊ���ɹ�������1Ϊ�ɹ�
	 */
	public boolean insertSection(Section s) {
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(s);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			ts.rollback();
			return false;
		}
	}
	
	/*
	 * ����idɾ�����
	 */
	public boolean deleteSection(int id) {
		Session session = HibernateSessionFactory.getSession();
		Section s = (Section) session.get(Section.class, new Integer(id));
		Transaction ts = session.beginTransaction();
//		ArrayList<Card> cardList = new CardDao().querySectionCard(id);
		try {
			session.delete(s);
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
	 * ���ݰ�����޸İ���
	 */
	public boolean updateSection(int id, int host) {
		Session session = HibernateSessionFactory.getSession();
		Section s = (Section) session.get(Section.class, new Integer(id));
		Transaction ts = session.beginTransaction();
		try {
			s.setHost(host);
			session.saveOrUpdate(s);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			ts.rollback();
			return false;
		}
	}
	
	/*
	 * ����鸳ֵ�û���
	 */
	public void make_name(List<Section> list) {
		for(Section s:list) {
			s.setHostName(new UserTableDao().queryUserName(s.getHost()));
		}
	}
	
}
