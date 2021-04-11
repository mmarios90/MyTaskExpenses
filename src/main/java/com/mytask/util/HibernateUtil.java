package com.mytask.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mytask.entity.Activity;
import com.mytask.entity.Labor;

public class HibernateUtil {

	private SessionFactory sessionFactory;
	private Session session;

	public HibernateUtil() {
	}

	public SessionFactory initializeSessionFactory() {

		System.out.println("initializing sessionFactory!");

		this.sessionFactory = new Configuration().configure().addAnnotatedClass(Activity.class)
				.addAnnotatedClass(Labor.class).buildSessionFactory();
		return this.sessionFactory;

	}

	public Session initializeSession(SessionFactory sessionFactory) {

		this.session = sessionFactory.getCurrentSession();
		this.session.beginTransaction();
		return this.session;
	}

	public void commitTransaction() {
		this.session.getTransaction().commit();
	}

	public void closeSessionFactory() {
		this.sessionFactory.close();
	}
	
	public void closeSession() {
		this.session.close();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HibernateUtil [sessionFactory=");
		builder.append(sessionFactory);
		builder.append(", session=");
		builder.append(session);
		builder.append("]");
		return builder.toString();
	}

}
