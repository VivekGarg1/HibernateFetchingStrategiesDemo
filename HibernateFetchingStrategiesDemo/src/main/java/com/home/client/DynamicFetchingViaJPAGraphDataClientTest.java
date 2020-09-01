package com.home.client;

import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import com.home.entities.Call;
import com.home.entities.Employee;
import com.home.entities.Phone;
import com.home.util.HibernateUtil;

public class DynamicFetchingViaJPAGraphDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			dynamicFetchingJPAGraphEntityGraph(session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void dynamicFetchingJPAGraphEntityGraph(Session session) {
		int phoneId=1;
		Phone phone = session.find(Phone.class, phoneId, Collections.singletonMap("javax.persistecnce.fetchGraph", session.getEntityGraph("phone.call")));
		if(phone!=null) {
			System.out.println("Phone details!!!!!!!");
			System.out.println(phone);
			System.out.println("call details!!!");
			List<Call> calls = phone.getCall();
			for (Call call : calls) {
				System.out.println(call);
			}
			System.out.println("Employee details!!!");
			Employee employee = phone.getEmployee();
			if(employee!=null) 
				System.out.println(employee);
			else
				System.out.println("Employee details not found for phone: "+phoneId);
		}
		else
			System.out.println("Phoe not found with Id: "+phoneId);
		
	}
	
}
