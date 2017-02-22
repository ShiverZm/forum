package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;
import beans.Card;

public class CardDao {
	
	/*
	 * ����id��ѯ���ӣ�����Card��û���򷵻�null
	 */
	@SuppressWarnings("unchecked")
	public Card queryCardById(int id) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Card where id=?");
		query.setInteger(0, id);
		List<Card> list = query.list();
		if (list != null && list.size() > 0) {
//			list.get(0).setName(new UserTableDao().queryUserName(list.get(0).getNameId()));
			list.get(0).setName(new UserTableDao().queryUserName(list.get(0).getNameId()));
			return list.get(0);
		} else
			return null;
	}
	
	/*
	 * ����replyId[���ظ����ӵ�id]��ѯ���ӡ�0Ϊ�ǻظ������ӡ�������ArrayList��û���򷵻�null
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Card> queryCard(int replyId) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Card where replyId=? order by topDate desc");
		query.setInteger(0, replyId);
		List<Card> list = query.list();
		make_name(list);
		if (list != null && list.size() > 0) {
			return (ArrayList) list;
		} else
			return null;
	}
	
	/*
	 * �����û�id��ѯ�û���������(�ǻظ�)������ArrayList��û���򷵻�null
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Card> querySelfCard(int nameId) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Card where nameId=? and replyId=? order by topDate desc");
		query.setInteger(0, nameId);
		query.setInteger(1, 0);
		List<Card> list = query.list();
		make_name(list);
		if (list != null && list.size() > 0) {
			return (ArrayList) list;
		} else
			return null;
	}
	
	/*
	 * ���ݰ��id��ѯ�ǻظ����ӣ�����ArrayList��û���򷵻�null
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Card> querySectionCard(int sectionId) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Card where sectionId=? and replyId=? order by topDate desc");
		query.setInteger(0, sectionId);
		query.setInteger(1, 0);
		List<Card> list = query.list();
		make_name(list);
		if (list != null && list.size() > 0) {
			return (ArrayList) list;
		} else
			return null;
	}
	
	/*
	 * ���ݰ��id���û�id��ѯ�ǻظ����ӣ�����ArrayList��û���򷵻�null
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Card> querySection_SelfCard(int nameId, int sectionId) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Card where nameId=? and sectionId=? and replyId=? order by topDate desc");
		query.setInteger(0, nameId);
		query.setInteger(1, sectionId);
		query.setInteger(2, 0);
		List<Card> list = query.list();
		make_name(list);
		if (list != null && list.size() > 0) {
			return (ArrayList) list;
		} else
			return null;
	}
	
	/*
	 * �������ݻ����ؼ��ֲ�ѯ����
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Card> queryByKey(String key) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Card where contents like '%"+key+"%' and title like '%"+key+"%' order by topDate desc");
		List<Card> list = query.list();
		make_name(list);
		if (list != null && list.size() > 0) {
			return (ArrayList) list;
		} else
			return null;
	}
	
	/*
	 * �����ӱ�������ӣ�����0Ϊ���ɹ�������1Ϊ�ɹ�
	 */
	public boolean insertCard(Card c) {
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(c);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			ts.rollback();
			return false;
		}
	}
	
//	/*
//	 * ���ݰ��idɾ������
//	 */
//	public boolean deleteCardBySectionId(int sectionId) {
//		
//	}
	
	/*
	 * ��������idɾ������
	 */
	public boolean deleteCard(int id) {
		Session session = HibernateSessionFactory.getSession();
		Card c = (Card) session.get(Card.class, new Integer(id));
		ArrayList<Card> rc = queryCard(id);
		Transaction ts = session.beginTransaction();
		try {
			session.delete(c);
			if(rc != null)
				for(Card r:rc)
					session.delete(r);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			ts.rollback();
			return false;
		}
	}
	
//	/*
//	 * ������������idɾ������
//	 */
//	public boolean deleteCardByNameId(int nameId) {
//		
//	}
	
//	/*
//	 * ���ݻظ�����idɾ���ظ�������
//	 */
//	public boolean deleteReplyCard(int id) {
//		
//	}
	
	/*
	 * �ö�
	 */
	public boolean updateTop(int id, String date) {
		Session session = HibernateSessionFactory.getSession();
		Card c = (Card) session.get(Card.class, new Integer(id));
		Transaction ts = session.beginTransaction();
		try {	
			c.setIsTop(1);
			c.setTopDate(date);
			session.saveOrUpdate(c);
			ts.commit();
			session.close();
			return true;
		} catch (Exception e) {
			ts.rollback();
			return false;
		}
	}
	
	/*
	 * �����Ӹ�ֵ�û���
	 */
	public void make_name(List<Card> list) {
		for(Card c:list) {
//			c.setName(new UserTableDao().queryUserName(c.getNameId()));
			c.setName(new UserTableDao().queryUserName(c.getNameId()));
		}
	}
	
}
