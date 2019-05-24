package com.stackroute.keepnote.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;

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
public class UserDaoImpl implements UserDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	
	SessionFactory sessionFactory;
	
	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/*
	 * Create a new user
	 */

	public boolean registerUser(User user) {

		boolean bool = false;
		try {
			sessionFactory.getCurrentSession().save(user);
			bool=true;
		}
		catch(Exception e) {}
		return bool;
	}

	/*
	 * Update an existing user
	 */

	public boolean updateUser(User user) {

		boolean bool = false;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			bool=true;
		}
		catch(Exception e) {}
		return bool;

	}

	/*
	 * Retrieve details of a specific user
	 */
	public User getUserById(String UserId) {

		return sessionFactory.getCurrentSession().get(User.class, UserId);
	}

	/*
	 * validate an user
	 */

	public boolean validateUser(String userId, String password) throws UserNotFoundException {
		
		User user = (User) sessionFactory.getCurrentSession().createQuery("from User where userId = ? and userPassword = ?")
					.setString(0, userId).setString(1, password).uniqueResult();
		
		if(user==null) {
			throw new UserNotFoundException("");
			
		}
		else {
			return true;
		}

	}

	/*
	 * Remove an existing user
	 */
	public boolean deleteUser(String userId) {
		
			boolean bool = false;
			try {
				sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(User.class, userId));
				bool=true;
			}
			catch(Exception e) {}
			return bool;

		

	}

}
