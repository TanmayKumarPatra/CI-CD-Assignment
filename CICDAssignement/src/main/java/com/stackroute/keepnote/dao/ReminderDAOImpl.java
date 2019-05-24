package com.stackroute.keepnote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;

/*
 * This class is implementing the UserDAO interface. This class has to be annotated with 
 * @Repository annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, 
 * thus clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */

@Repository
@Transactional
public class ReminderDAOImpl implements ReminderDAO {
	
	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	@Autowired
	SessionFactory sessionFactory;

	public ReminderDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	/*
	 * Create a new reminder
	 */

	public boolean createReminder(Reminder reminder) {
		boolean bool = false;
		try {
			sessionFactory.getCurrentSession().save(reminder);
			bool=true;
		}
		catch(Exception e) { }
		return bool;

	}
	
	/*
	 * Update an existing reminder
	 */

	public boolean updateReminder(Reminder reminder) {
		boolean bool = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(reminder);
			bool=true;
		}
		catch(Exception e) {}
		return bool;

	}

	/*
	 * Remove an existing reminder
	 */
	
	public boolean deleteReminder(int reminderId) {
		boolean bool = false;
		try {
			sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Reminder.class, reminderId));
			bool=true;
		}
		catch(Exception e) {}
		return bool;

	}

	/*
	 * Retrieve details of a specific reminder
	 */
	
	public Reminder getReminderById(int reminderId) throws ReminderNotFoundException {

		Reminder rem = sessionFactory.getCurrentSession().get(Reminder.class, reminderId);
		if(rem == null) {
			throw new ReminderNotFoundException("");
		}
		
		return rem;

	}

	/*
	 * Retrieve details of all reminders by userId
	 */
	
	public List<Reminder> getAllReminderByUserId(String userId) {
		return sessionFactory.getCurrentSession().createQuery("from Reminder where reminderCreatedBy = ?").
				setString(0, userId).getResultList();

	}

}
