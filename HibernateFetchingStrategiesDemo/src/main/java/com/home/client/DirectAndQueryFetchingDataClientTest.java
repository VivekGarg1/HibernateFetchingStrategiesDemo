package com.home.client;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.home.entities.Employee;
import com.home.entities.Phone;
import com.home.util.HibernateUtil;

public class DirectAndQueryFetchingDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			//directEntityFetching(session);
			queryEntityFetching(session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void directEntityFetching(Session session) {
		int phoneId=1;
		Phone phone=session.get(Phone.class, phoneId);
		if(phone!=null) {
			System.out.println("Phone details!!!!!!!");
			System.out.println(phone);
			System.out.println("Employee details!!!");
			Employee employee = phone.getEmployee();
			if(employee!=null) {
				System.out.println(employee);
			}
			else
				System.out.println("Employee details not found for phone: "+phoneId);
		}
		else
			System.out.println("Phoe not found with Id: "+phoneId);
	}
	

	private static void queryEntityFetching(Session session) {
		int phoneId=1;
		Query<?> query = session.createQuery("select p from Phone p where p.phoneId=:phnId");
		query.setParameter("phnId", phoneId);
		Object object = query.getSingleResult();
		Phone phone=(Phone) object;
		if(phone!=null) {
			System.out.println("Phone details!!!!!!!");
			System.out.println(phone);
			System.out.println("Employee details!!!");
			Employee employee = phone.getEmployee();
			if(employee!=null) {
				System.out.println(employee);
			}
			else
				System.out.println("Employee details not found for phone: "+phoneId);
		}
		else
			System.out.println("Phoe not found with Id: "+phoneId);
	}
}
