package com.secure.bankapp.model;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
   
	private Long roleId;
    private String name;
  

 

    @Column(name = "role_name")
    public String getName() {
        return name;
    }

    @Id
    @Column(name = "role_id")
    public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long role_id) {
		this.roleId = role_id;
	}

	public void setName(String name) {
        this.name = name;
    }

  
  
}