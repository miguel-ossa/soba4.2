package com.perfmath.spring.soba.model.dao;

import com.perfmath.spring.soba.model.dao.BillPaymentDao;
import com.perfmath.spring.soba.model.domain.BillPayment;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateBillPaymentDao implements BillPaymentDao {
	private SessionFactory sessionFactory;

	public HibernateBillPaymentDao() {
		Configuration configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}

	public void store(BillPayment billPayment) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.saveOrUpdate(billPayment);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void delete(String id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			BillPayment billPayment = sessionFactory
					.getCurrentSession().get(BillPayment.class, id);
			session.delete(billPayment);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public BillPayment findById(String id) {
		Session session = sessionFactory.openSession();
		try {
			return sessionFactory.getCurrentSession().get(
					BillPayment.class, id);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<BillPayment> findAll() {
		Session session = sessionFactory.openSession();
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from Bill_Payment");
			return query.list();
		} finally {
			session.close();
		}
	}
}
