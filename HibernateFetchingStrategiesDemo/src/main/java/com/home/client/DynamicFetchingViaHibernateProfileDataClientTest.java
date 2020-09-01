package com.home.client;

import java.util.List;

import org.hibernate.Session;
import com.home.entities.Call;
import com.home.entities.Employee;
import com.home.entities.Phone;
import com.home.util.HibernateUtil;

public class DynamicFetchingViaHibernateProfileDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			dynamicFetchingHibernateProfileEntity(session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void dynamicFetchingHibernateProfileEntity(Session session) {
		String phoneNumber="123456789";
		session.enableFetchProfile("phone.call");
		Phone phone = session.bySimpleNaturalId(Phone.class).load(phoneNumber);
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
				System.out.println("Employee details not found for phone: "+phoneNumber);
		}
		else
			System.out.println("Phoe not found with number: "+phoneNumber);
		
	}
	
}
