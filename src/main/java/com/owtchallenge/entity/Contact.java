package com.owtchallenge.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="CONTACT")
public class Contact {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="address")
	private String address;
	
	@Email(message = "Mail not correct")
	@Column(name="email")
	private String email;
	
	@Pattern(regexp="(^$|^\\+[0-9]*$|^[0-9]*$)",
			 message = "Phone number not correct")
	@Column(name="phone_number")
	private String phone_number;
	
	@ManyToMany
	@JoinTable(
		name="CONTACT_TO_SKILL",
		joinColumns=@JoinColumn(name = "contact_id"),
		inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private Set<Skill> skills;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Set<Skill> getSkills() {
		if (skills==null) {
			skills=new HashSet<Skill>();
		}
		return skills;
	}
	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
}
