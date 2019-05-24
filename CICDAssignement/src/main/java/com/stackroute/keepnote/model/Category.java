package com.stackroute.keepnote.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * The class "Category" will be acting as the data model for the Category Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

@Entity
public class Category {
	/*
	 * This class should have six fields
	 * (categoryId,categoryName,categoryDescription,
	 * categoryCreatedBy,categoryCreationDate,notes). Out of these six fields, the
	 * field categoryId should be primary key and auto-generated. This class should
	 * also contain the getters and setters for the fields along with the no-arg ,
	 * parameterized constructor and toString method. The value of
	 * categoryCreationDate should not be accepted from the user but should be
	 * always initialized with the system date. annotate notes field with @OneToMany
	 * and @JsonIgnore
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int categoryId;
	String categoryName;
	String categoryDescription;
	String categoryCreatedBy;
	Date categoryCreationDate;
	
	@OneToMany
	@JsonIgnore
	List<Note> notes;

	public Category() {

	}

	public Category(int Int, String string, String string1, Date date, String string2, List<Note> list) {
		this.categoryId = Int; 
		this.categoryName = string;
		this.categoryDescription = string1;
		this.categoryCreatedBy = string2;
		this.categoryCreationDate = date;
		this.notes = list;
	}

	public void setCategoryId(int Int) {
		this.categoryId = Int;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String string) {
		this.categoryName = string;
	}

	public String getCategoryDescription() {
		return this.categoryDescription;
	}

	public void setCategoryDescription(String string) {
		this.categoryDescription = string;
	}

	public void setCategoryCreationDate(Date date) {
		this.categoryCreationDate = date;
	}

	public void setCategoryCreatedBy(String string) {
		this.categoryCreatedBy = string;
	}

	public void setNotes(List<Note> list) {
		this.notes = list;
	}

	public String getCategoryCreatedBy() {
		return categoryCreatedBy;
	}

	public Date getCategoryCreationDate() {
		return categoryCreationDate;
	}

	public List<Note> getNotes() {
		return notes;
	}
	
	

}