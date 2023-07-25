package com.damddos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import com.damddos.config.JpaUtil;
import com.damddos.entity.Icon;
import com.damddos.exceptions.DBException;
import com.damddos.exceptions.NotFoundException;

public class IconDao {
	
	public static Icon addIcon(Icon icon) throws DBException {
		EntityManager entityManager = null;
 		EntityTransaction transaction = null;
 		try {
			entityManager = JpaUtil.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();			
			String hql = "insert into icon_info(id,image) values(?,?)";
			Query query = entityManager.createNativeQuery(hql);
			query.setParameter(1, icon.getId());
			query.setParameter(2, icon.getImage());
			query.executeUpdate();			
	 		transaction.commit(); 
	 		return icon;
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
	
	public static Icon getIconById(int id) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		Icon resIcon = entityManager.find(Icon.class, id);
		entityManager.close();
		return resIcon;
	}	
	
	public static Icon updateIcon(Icon icon) throws NotFoundException {
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Icon dbIcon = getIconById(icon.getId());
		if(dbIcon == null) {
			throw new NotFoundException();
		}
		if(icon.getImage() != null) {
			dbIcon.setImage(icon.getImage());
		}
		entityManager.merge(dbIcon);
		transaction.commit();
		entityManager.close();
		return dbIcon;
	}
	
//	public static void deleteIcon(int id) throws DBException  {
// 		EntityManager entityManager = null;
// 		EntityTransaction transaction = null;
//// 		int id = icon.getId();
// 		try { 			
//			entityManager = JpaUtil.getEntityManager();
//			transaction = entityManager.getTransaction();
//			transaction.begin();
//			String hql = "delete from icon_info where id = :id";
//			Query query = entityManager.createNativeQuery(hql);
//			query.setParameter("id", id);
//	 		query.executeUpdate();	
//	 		transaction.commit(); 
//		}catch (RuntimeException e) {
//			transaction.rollback();
// 			throw new DBException(e);
//		}finally {
//			if(entityManager != null) {
//				entityManager.close();
//			}			
//		}				
//	}
//	
	public static Icon update(Icon icon) throws DBException  {
 		EntityManager entityManager = null;
 		EntityTransaction transaction = null;
// 		int id = icon.getId();
 		byte[] image = icon.getImage();
 		try { 			
			entityManager = JpaUtil.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			String hql = "update icon_info where image = :image";
			Query query = entityManager.createNativeQuery(hql);
			query.setParameter("image", image);
	 		query.executeUpdate();	
	 		transaction.commit();
	 		return icon;
		}catch (RuntimeException e) {
			transaction.rollback();
 			throw new DBException(e);
		}finally {
			if(entityManager != null) {
				entityManager.close();
			}			
		}				
	}

	public void deleteIcon(int id) throws DBException {
		// TODO Auto-generated method stub
 		EntityManager entityManager = null;
 		EntityTransaction transaction = null;
// 		int id = icon.getId();
 		try { 			
			entityManager = JpaUtil.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			String hql = "delete from icon_info where id = :id";
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



}
