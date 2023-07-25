package com.damddos.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaUtil {
	
	private static final String PERSISTENCE_UNIT_NAME="PERSISTENCE";
	private static EntityManagerFactory factory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
        
	static {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return factory;
	}

	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
    public void init(){      
        entityManager = factory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        // 开启事务
        entityTransaction.begin();
    }

    public void destroy(){
        // 提交事务
//        entityTransaction.rollback();
        entityTransaction.commit();
        entityManager.close();
        if(factory != null) {
        	factory.close();
        }
    }
    

}
