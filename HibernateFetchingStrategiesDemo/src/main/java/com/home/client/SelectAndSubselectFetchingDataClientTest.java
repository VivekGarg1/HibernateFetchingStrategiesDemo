package com.home.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.home.entities.Call;
import com.home.entities.Employee;
import com.home.entities.Phone;
import com.home.util.HibernateUtil;

public class SelectAndSubselectFetchingDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			selectFetchingHibernateProfileEntity(session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void selectFetchingHibernateProfileEntity(Session session) {
		List<Employee> empList = session.createQuery("from Employee",Employee.class).getResultList();
		System.out.println("Employee count: "+empList.size());
		for (Employee employee : empList) {
			System.out.println("Employee Id: "+employee.getEmployeeId());
			System.out.println("Phone count: "+employee.getPhone().size());
		}
	}
}
