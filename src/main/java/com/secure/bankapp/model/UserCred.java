package com.secure.bankapp.model;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.*;
@Entity
@Table(name = "user_creds")
public class UserCred {

	 
	    private String userId;
	    private String password;
	    
	    private String passwordConfirm;
	    private Long roleId;
	    private String status;
	    
	    
	    @Id
	    @Column(name = "user_id")
		public String getUserId() {
			return userId;
		}




		public void setUserId(String userId) {
			this.userId = userId;
		}



	    @Column(name = "password")
		public String getPassword() {
			return password;
		}




		public void setPassword(String password) {
			this.password = password;
		}



		@Transient
		public String getPasswordConfirm() {
			return passwordConfirm;
		}




		public void setPasswordConfirm(String passwordConfirm) {
			this.passwordConfirm = passwordConfirm;
		}



	    @Column(name = "role_id")
		public Long getRoleId() {
			return roleId;
		}




		public void setRoleId(Long roleId) {
			this.roleId = roleId;
		}



	    @Column(name = "status")
		public String getStatus() {
			return status;
		}




		public void setStatus(String status) {
			this.status = status;
		}




	




	

}
