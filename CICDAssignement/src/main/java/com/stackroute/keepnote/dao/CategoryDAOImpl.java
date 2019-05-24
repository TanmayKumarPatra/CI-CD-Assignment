package com.stackroute.keepnote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;

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
public class CategoryDAOImpl implements CategoryDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	SessionFactory sessionFactory;
	
	@Autowired
	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*
	 * Create a new category
	 */
	public boolean createCategory(Category category) {
		;
		boolean bool = false;
		try {
			sessionFactory.getCurrentSession().save(category);
			bool=true;
		}
		catch(Exception e) {}
		return bool;

	}

	/*
	 * Remove an existing category
	 */
	public boolean deleteCategory(int categoryId) {
		boolean bool = false;
		try {
			sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Category.class, categoryId));
			bool=true;
		}
		catch(Exception e) {}
		return bool;

	}
	/*
	 * Update an existing category
	 */

	public boolean updateCategory(Category category) {
		boolean bool = false;
		try {
			sessionFactory.getCurrentSession().update(category);
			bool=true;
		}
		catch(Exception e) {}
		return bool;

	}
	/*
	 * Retrieve details of a specific category
	 */


	public Category getCategoryById(int categoryId) throws CategoryNotFoundException {
		
		Category cat =  sessionFactory.getCurrentSession().get(Category.class, categoryId);

		if(cat == null || cat.equals(null)) {
			throw new CategoryNotFoundException("");
		}
		else {
			return cat;
		}

	}

	/*
	 * Retrieve details of all categories by userId
	 */
	public List<Category> getAllCategoryByUserId(String userId) {
		return sessionFactory.getCurrentSession().createQuery("from Category where categoryCreatedBy = ?").setString(0, userId).getResultList();

	}

}
