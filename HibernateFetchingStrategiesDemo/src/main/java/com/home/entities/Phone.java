package com.home.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfile.FetchOverride;
import org.hibernate.annotations.FetchProfiles;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="phone",uniqueConstraints = @UniqueConstraint(name= "emloyee_phone_number",columnNames = { "phone_number","employee_id" }))
//Dynamic fetching via JPA entity graph
//@NamedEntityGraph(name="phone.call", attributeNodes = @NamedAttributeNode("call"))
//or
/*@NamedEntityGraphs(value = {
		@NamedEntityGraph(name="phone.call", attributeNodes = @NamedAttributeNode("call"))
		})*/
//Dynamic fetching via @profiles
/*@FetchProfiles(value = {
		@FetchProfile(fetchOverrides = { @FetchOverride(association = "call", entity = Phone.class, mode = FetchMode.JOIN) }
		, name = "phone.call")
})*/
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phone_id")
	private Integer phoneId;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	//Fetching by @Profiles then need nauralId
	//@NaturalId
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name="phone_type")
	private PhoneType phoneType;
	
	@OneToMany(mappedBy = "phone",cascade =CascadeType.ALL ,orphanRemoval = true)
	private List<Call> call=new ArrayList<Call>();

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name="phone_date")
	private Date phoneDate;
	
	public Date getPhoneDate() {
		return phoneDate;
	}

	public void setPhoneDate(Date phoneDate) {
		this.phoneDate = phoneDate;
	}
	
	public Integer getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Integer phoneId) {
		this.phoneId = phoneId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public List<Call> getCall() {
		return call;
	}

	public void setCall(List<Call> call) {
		this.call = call;
	}

	@Override
	public String toString() {
		return "Phone [phoneId=" + phoneId + ", phoneNumber=" + phoneNumber + ", phoneType="
				+ phoneType + ", phoneDate=" + phoneDate + "]";
	}
	
	
}
