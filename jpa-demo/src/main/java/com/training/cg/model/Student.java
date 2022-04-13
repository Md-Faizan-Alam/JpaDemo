package com.training.cg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
@NamedQuery(query = "select s from Student s where s.id = :id", name = "find student by id")
public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9143810693264439663L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="stud_id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	public Student() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
