package com.has.users.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data

@Entity
public class User {

	private @javax.persistence.Id @GeneratedValue Long Id;
	
	@Column(name="firstName")
	private String firstName;

	@Column(name="lastName")
	private String lastName;
	
	@Column(name="email")
	private String email; 
	
	@Column(name="birthDate")
	private Date birthDate;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	 @ManyToMany(fetch = FetchType.EAGER)
	 @JoinTable(name = "user_role", joinColumns
	            = @JoinColumn(name = "user_id",
	            referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id",
	                    referencedColumnName = "id"))
	private List<Role> roles;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String email, Date birthDate, String phone, String username, String password) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.email = email;
		this.setBirthDate(birthDate);
		this.setPhone(phone);
		this.username = username;
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
