package com.stackroute.keepnote.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*
 * The class "Note" will be acting as the data model for the Note Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

@Entity
public class Note {
	/*
	 * This class should have eight fields
	 * (noteId,noteTitle,noteContent,noteStatus,createdAt,
	 * category,reminder,createdBy). Out of these eight fields, the field noteId
	 * should be primary key and auto-generated. This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of createdAt should not be
	 * accepted from the user but should be always initialized with the system date.
	 * annotate category and reminder field with @ManyToOne.
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int noteId;
	String noteTitle;
	String noteContent;
	String StringnoteStatus;
	Date createdAt;
	String createdBy;
	
	@ManyToOne
	Category category;
	@ManyToOne
	Reminder reminder;
	

	public Note() {

	}

	public Note(int Int, String string, String string1, String string2, Date date, Category category, Reminder reminder,
			String string3) {
		this.noteId = Int;
		this.noteTitle = string;
		this.noteContent = string1;
		this.StringnoteStatus = string2;
		this.createdAt = date;
		this.category = category;
		this.reminder = reminder;
		this.createdBy = string3;
	}

	public int getNoteId() {
		return this.noteId;
	}

	public void setNoteId(int Int) {
		this.noteId = Int;
	}

	public String getNoteTitle() {
		return this.noteTitle;
	}

	public void setNoteTitle(String string) {
		this.noteTitle = string;
	}

	public String getNoteContent() {
		return this.noteContent;
	}

	public void setNoteContent(String string) {
		this.noteContent = string;
	}

	public void setNoteStatus(String string) {
		this.StringnoteStatus = string;
	}

	public void setNoteCreatedAt(Date date) {
		this.createdAt = date;
	}

	public void setCreatedBy(String string) {
		this.createdBy = string;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getStringnoteStatus() {
		return StringnoteStatus;
	}

	public void setStringnoteStatus(String stringnoteStatus) {
		StringnoteStatus = stringnoteStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Category getCategory() {
		return category;
	}

	public Reminder getReminder() {
		return reminder;
	}
	

}
