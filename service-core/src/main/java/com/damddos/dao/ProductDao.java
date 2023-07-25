package com.damddos.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.damddos.config.JpaUtil;
import com.damddos.entity.Advantage;
import com.damddos.entity.Function;
import com.damddos.entity.Product;
import com.damddos.exceptions.ConflictException;
import com.damddos.exceptions.DBException;
import com.damddos.exceptions.NotFoundException;

public class ProductDao {	
	public static Product saveProduct(Product product) throws ConflictException, DBException {		
		if(product.getId() != null && getProductById(product.getId()) != null) {
			throw new ConflictException();
		}
 		EntityManager entityManager = null;
 		EntityTransaction transaction = null; 		
		try {
			entityManager = JpaUtil.getEntityManager();
	        transaction = entityManager.getTransaction();
	        transaction.begin();
			entityManager.persist(product);
			entityManager.getTransaction().commit();
			return product;	
		} catch (Exception e) {
			transaction.rollback();
			throw new DBException();
	    } finally {
	    	if(entityManager != null) {
	    		entityManager.close();
	    	}
	    }
	}
	
	public static Product getProductById(int id) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		Product resProduct = entityManager.find(Product.class, id);
		entityManager.close();
		return resProduct;
	}
	
	public static List<Product> getProducts(){
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		List<Product> res = entityManager.createQuery("select o from Product o",Product.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return res;		
	}
	
	public static Product updateProduct(Product product) throws NotFoundException, DBException{
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Product dbProduct = getProductById(product.getId());
//		 findById(product) , 更改子属性
		if(dbProduct == null) {			
			throw new NotFoundException();
		}
		if(product.getScrollName() != null) {
			dbProduct.setScrollName(product.getScrollName());
		}
		if(product.getProductName() != null) {
			dbProduct.setProductName(product.getProductName());
		}
		if(product.getProductDescription() != null) {
			dbProduct.setProductDescription(product.getProductDescription());
		}
		if(product.getStyleId() != 0) {
			dbProduct.setStyleId(product.getStyleId());
		}	
		entityManager.merge(dbProduct);		
//	先做删除  再遍历 更新   最后保存	
		deleteFunction(product.getId());		
		Iterator<Function> funcIterator = product.getProductFunctions().iterator();
		while(funcIterator.hasNext()) {
			Function function = funcIterator.next();
			function.setId(null);
			function.setProductId(product.getId());
			entityManager.persist(function);
		}
		deleteAdvantage(product.getId());		
		Iterator<Advantage> advIterator = product.getProductAdvantages().iterator();
		while (advIterator.hasNext()) {
			Advantage advantage = advIterator.next();		
			advantage.setId(null);
			advantage.setProductId(product.getId());
			entityManager.persist(advantage);
		}		
		transaction.commit();
		entityManager.close();			
		return dbProduct;		
	}
		
	public static Product addProduct(Product product) throws DBException {
 		EntityManager entityManager = null;
 		EntityTransaction transaction = null;
 		try {
 			entityManager = JpaUtil.getEntityManager();
 			transaction = entityManager.getTransaction();
 			transaction.begin();
 			entityManager.persist(product);		
 			transaction.commit();
 			return product;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			throw new DBException();
		} finally {
			if(entityManager != null) {
				entityManager.close();
			}
		}
	}

	public static Product deleteProductById(int id) throws DBException {
 		EntityManager entityManager = null;
 		EntityTransaction transaction = null;
 		try {
 			entityManager = JpaUtil.getEntityManager();
 			transaction = entityManager.getTransaction();
 			transaction.begin();
 			Product product = entityManager.find(Product.class, id);
 			if(product != null) {
 				entityManager.remove(product);
 			}
 			transaction.commit();
			return product;
		} catch (RuntimeException e) {
			// TODO: handle exception
			transaction.rollback();
			throw new DBException();
		}finally {
			if(entityManager != null) {
				entityManager.close();
			}
		}	
	}
	
	public static void deleteFunction(int id) throws DBException  {
 		EntityManager entityManager = null;
 		EntityTransaction transaction = null;
 		try { 			
			entityManager = JpaUtil.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			String hql = "delete from function_info where product_id = :id";
			Query query = entityManager.createNativeQuery(hql);
			query.setParameter("id", id);
	 		query.executeUpdate();	
	 		transaction.commit(); 
		}catch (RuntimeException e) {
			transaction.rollback();
 			throw new DBException(e);
		}finally {
			if(entityManager != null) {
				entityManager.close();
			}			
		}				
	}
	
	public List<Function> getFunctions(int productId){
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		String hql = "select function from Function function where function.productId = (:productId)";		
		@SuppressWarnings("unchecked")
		List<Function> res = entityManager.createQuery(hql).setParameter("productId",productId).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return res;	
	} 
		
    public static void deleteAdvantage(int id) throws DBException {
    	EntityManager entityManager = null;
 		EntityTransaction transaction = null;
 		try {
			entityManager = JpaUtil.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
//			String hql = "delete from Advantage advantage where advantage.productId = :id";
			String hql = "delete from advantage_info where product_id = :id";
			Query query = entityManager.createNativeQuery(hql);
			query.setParameter("id", id);
			query.executeUpdate();
	 		transaction.commit(); 		
		}catch (RuntimeException e) {
			transaction.rollback();
			e.printStackTrace();
			throw new DBException(e);
		}finally {
			if(entityManager != null) {
				entityManager.close();
			}
		}		
    }
	
}
