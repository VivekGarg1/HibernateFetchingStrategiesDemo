package com.home.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.home.entities.Call;
import com.home.entities.Employee;
import com.home.entities.Phone;
import com.home.util.HibernateUtil;

public class BatchFetchingViaHibernateDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			batchFetchingHibernateProfileEntity(session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void batchFetchingHibernateProfileEntity(Session session) {
		List<Employee> empList = session.createQuery("from Employee",Employee.class).getResultList();
		for (Employee employee : empList) {
			System.out.println("Employee details:::: ");
			System.out.println(employee);
			System.out.println("Phone details:::: ");
			List<Phone> phoneList = employee.getPhone();
			for (Phone phone : phoneList) {
				System.out.println(phone);
			}
		}
	}
}
