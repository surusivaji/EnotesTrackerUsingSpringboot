package org.siva.enotes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="full_name", length=40, nullable = false)
	private String fullname;
	@Column(name="email", length=40, nullable=false, unique=true)
	private String email;
	@Column(name="mobile", length=10, nullable=false, unique=true)
	private String mobile;
	@Column(name="gender", length=10, nullable=false)
	private String gender;
	@Column(name="qualification", length=30, nullable=false)
	private String qualification;
	@Column(name="password", length=20, nullable=false)
	private String password;
	
	public User() {
		
	}

	public User(Integer id, String fullname, String email, String mobile, String gender, String qualification,
			String password) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.qualification = qualification;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullname=" + fullname + ", email=" + email + ", mobile=" + mobile + ", gender="
				+ gender + ", qualification=" + qualification + ", password=" + password + "]";
	}

}
