package com.damddos.service;

import java.util.List;

import com.damddos.dao.ProductDao;
import com.damddos.entity.Product;
import com.damddos.exceptions.ConflictException;
import com.damddos.exceptions.DBException;
import com.damddos.exceptions.NotFoundException;


public class ProductService {
	public static List<Product> getProducts(){
		return ProductDao.getProducts();
	}
	
	public static Product saveProduct(Product product) throws ConflictException, DBException {
		return ProductDao.saveProduct(product);
	}
	
	public static Product getProductById(int id){
		return ProductDao.getProductById(id);
	}
	
	public static Product addProduct(Product product) throws DBException {
		return ProductDao.addProduct(product);
	}
	
	public static Product updateProduct(Product product) throws NotFoundException, DBException {
		return ProductDao.updateProduct(product);
	}

	public static Product deleteProductById(int id) throws DBException {
		return ProductDao.deleteProductById(id);
	}
	
}
