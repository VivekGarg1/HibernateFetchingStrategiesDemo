package com.home.client;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.home.entities.Employee;
import com.home.entities.Phone;
import com.home.util.HibernateUtil;

public class Nplus1IssueFetchingWithQyeryDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			nPlus1QueryIssueEntity(session);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void nPlus1QueryIssueEntity(Session session) {
		CriteriaBuilder  builder=session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		root.fetch("phone",JoinType.LEFT);
		criteriaQuery.select(root);
		List<Employee> empList = session.createQuery(criteriaQuery).getResultList();
		
		//List<Employee> empList = session.createQuery("from Employee",Employee.class).getResultList();
		//List<Employee> empList = session.createQuery("from Employee e  join fetch e.phone",Employee.class).getResultList();
		
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
