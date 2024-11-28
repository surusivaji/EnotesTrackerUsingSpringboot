package org.siva.enotes.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="title", nullable=false)
	private String title;
	@Column(name="description", nullable=false, length=2000)
	private String description;
	@Column(name="entry_date", nullable=false)
	private Date date;
	@ManyToOne
	private User user;
	
	public Notes() {
		
	}

	public Notes(int id, String title, String description, Date date, User user) {
		this.id = id;
		this.title = title;
		this.description = description;
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

	@Override
	public String toString() {
		return "Notes [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", user="
				+ user + "]";
	}
	
}
