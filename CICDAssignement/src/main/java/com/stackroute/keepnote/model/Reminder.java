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
 * The class "Reminder" will be acting as the data model for the Reminder Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

@Entity
public class Reminder {
	/*
	 * This class should have seven fields
	 * (reminderId,reminderName,reminderDescription,reminderType,
	 * reminderCreatedBy,reminderCreationDate,notes). Out of these seven fields, the
	 * field reminderId should be primary key and auto-generated. This class should
	 * also contain the getters and setters for the fields along with the no-arg ,
	 * parameterized constructor and toString method. The value of
	 * reminderCreationDate should not be accepted from the user but should be
	 * always initialized with the system date. annotate notes field with @OneToMany
	 * and @JsonIgnore
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int reminderId;
	String reminderName;
	String reminderDescription;
	String reminderType;
	String reminderCreatedBy;
	Date reminderCreationDate;
	@OneToMany
	@JsonIgnore
	List<Note> notes;

	public Reminder() {

	}

	public Reminder(int Int, String string, String string1, String string2, String string3, List<Note> list,
			Date date) {
		this.reminderId = Int;
		this.reminderName = string;
		this.reminderDescription = string1;
		this.reminderType = string2;
		this.reminderCreatedBy = string3;
		this.reminderCreationDate = date;
		this.notes = list;
	}


	public int getReminderId() {
		return this.reminderId;

	}

	public void setReminderId(int Int) {
		this.reminderId = Int;
	}

	public void setReminderName(String string) {
		this.reminderName = string;
	}

	public String getReminderDescription() {
		return this.reminderDescription;
	}

	public void setReminderDescription(String string) {
		this.reminderDescription = string;
	}

	public void setReminderType(String string) {
		this.reminderType = string;
	}

	public void setReminderCreationDate(Date date) {
		this.reminderCreationDate = date;
	}

	public void setReminderCreatedBy(String string) {
		this.reminderCreatedBy = string;
	}

	public void setNotes(List<Note> list) {
		this.notes = list;
	}

	public String getReminderName() {
		return reminderName;
	}

	public String getReminderType() {
		return reminderType;
	}

	public String getReminderCreatedBy() {
		return reminderCreatedBy;
	}

	public Date getReminderCreationDate() {
		return reminderCreationDate;
	}

	public List<Note> getNotes() {
		return notes;
	}

}
