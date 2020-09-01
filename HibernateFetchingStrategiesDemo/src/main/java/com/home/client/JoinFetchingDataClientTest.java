package com.home.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.home.entities.Call;
import com.home.entities.Employee;
import com.home.entities.Phone;
import com.home.util.HibernateUtil;

public class JoinFetchingDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			joinFetchingHibernateEntity(session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void joinFetchingHibernateEntity(Session session) {
		int empId=2;
		Employee employee = session.get(Employee.class,empId);
		if(employee!=null) {
			System.out.println("Employee Id: "+employee.getEmployeeId());
			System.out.println("Phone count: "+employee.getPhone().size());
			}
		else
			System.out.println("Employee not found with Id: "+empId);
	}
}
