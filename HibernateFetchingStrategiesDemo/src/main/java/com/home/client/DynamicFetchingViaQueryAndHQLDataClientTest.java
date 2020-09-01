package com.home.client;

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

public class DynamicFetchingViaQueryAndHQLDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			//dynamicFetchingHQLEntityFetching(session);
			dynamicFetchingQueryEntityFetching(session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void dynamicFetchingQueryEntityFetching(Session session) {
		int phoneId=1;
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<Phone> criteriaQuery = builder.createQuery(Phone.class);
		Root<Phone> root = criteriaQuery.from(Phone.class);
		root.fetch("call",JoinType.LEFT);
		criteriaQuery.select(root).where(builder.equal(root.get("phoneId"), phoneId));
		Phone phone = session.createQuery(criteriaQuery).getSingleResult();
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
	
	private static void dynamicFetchingHQLEntityFetching(Session session) {
		int phoneId=1;
		Phone phone = session.createQuery("select p from Phone p left join fetch p.call where p.phoneId=:phnId",Phone.class)
		.setParameter("phnId", phoneId)
		.getSingleResult();
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
