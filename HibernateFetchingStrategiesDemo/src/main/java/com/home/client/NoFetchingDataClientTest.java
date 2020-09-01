package com.home.client;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.home.entities.Employee;
import com.home.entities.Phone;
import com.home.util.HibernateUtil;

public class NoFetchingDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			nofetchingEntityFetching(session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void nofetchingEntityFetching(Session session) {
		int phoneId=1;
		Phone phone = session.createQuery("select p from Phone p where p.phoneId=:phnId",Phone.class)
		.setParameter("phnId", phoneId)
		.getSingleResult();
		if(phone!=null) {
			System.out.println("Phone details!!!!!!!");
			System.out.println(phone);
			System.out.println("Employee details!!!");
			Employee employee = phone.getEmployee();
			if(employee!=null) 
				System.out.println(employee);
			else
				System.out.println("Employee details not found for phone: "+phoneId);
		}
		else
			System.out.println("Phoe not found with Id: "+phoneId);
		
		//No fetching (scaler) example
		Integer phoneIdData = session.createQuery("select p.phoneId from Phone p where p.phoneId=:phnId",Integer.class)
				.setParameter("phnId", phoneId)
				.getSingleResult();
		System.out.println("Access Level: "+phoneIdData);
	}
}
