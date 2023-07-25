package com.damddos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.damddos.config.JpaUtil;
import com.damddos.entity.Product;
import com.damddos.entity.User;

public class UserDao {
	
	public static User getUserById(int id) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		User res = entityManager.find(User.class, id);
		entityManager.close();
		return res;
	}
	
	public static User getUserByName(String name) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		String hql = "select u from User u where u.userName = :userName";
		Query query = entityManager.createNativeQuery(hql);
		query.setParameter("userName", name);
		query.executeUpdate();
		return null;
	}
	
    /**
     * Find a user by username or email.
     * @param identifier
     * @return
     */
    public User findByUsernameOrEmail(String identifier) {
    	EntityManager entityManager = JpaUtil.getEntityManager();
    	List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :identifier OR u.email = :identifier", User.class)
                .setParameter("identifier", identifier)
                .setMaxResults(1)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
    
}
