package org.siva.enotes.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	@Column(name = "Title", length = 100, nullable = false)
	private String title;
	@Column(name = "Description", length = 500, nullable = false)
	private String description;
	@Column(name = "Status", length = 50, nullable = false)
	private String status;
	@Column(name = "Date", length = 50, nullable = false)
	private Date date;
	@ManyToOne
	private User user;

	public Todo() {

	}

	public Todo(int id, String title, String description, String status, Date date, User user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.date = date;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
