package com.has.users.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	
	private String description;
	
	@Column(name = "role_name")
	private String roleName;

	@ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<User>();
	
	public Role(String description, String roleName) {
		this.description = description;
		this.roleName = roleName;
	}

	public String getRoleName() {
		return this.roleName;
	}

}
