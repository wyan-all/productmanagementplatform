package testAPI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.sound.midi.Soundbank;

import org.apache.commons.math3.analysis.function.Add;
import org.junit.Test;

import com.damddos.config.JpaUtil;
import com.damddos.dao.IconDao;
import com.damddos.dao.ProductDao;
import com.damddos.entity.Advantage;
import com.damddos.entity.Function;
import com.damddos.entity.Icon;
import com.damddos.entity.Product;
import com.damddos.exceptions.DBException;
import com.damddos.exceptions.NotFoundException;
import com.damddos.utils.Jackson2Util;


public class ProductTest {
	
	@Test
	public void getProducts() {
		ProductDao productDao = new ProductDao();
		List<Product> res = productDao.getProducts();
		for (int i = 0; i < res.size(); i++) {
			Product product  = res.get(i);
			System.out.println(product.getScrollName() + " proName = " + product.getProductName());
		}
	}
	
	@Test
	public void addProduct() throws DBException, IOException {
		
		Jackson2Util jackson2Util = Jackson2Util.newInstance();
		
		ProductDao productDao = new ProductDao();		
		Product pro = new Product();
		
		pro.setScrollName("gate20");
		pro.setProductName("test20");
		pro.setProductDescription("xx20");
		pro.setStyleId(0);
		
		//Set<Advantage> advantages = new HashSet<Advantage>();
		Advantage advantage = new Advantage();
		advantage.setAdvantageName("adv20");
		advantage.setAdvantageContent("test20");
		pro.getProductAdvantages().add(advantage);
		
//		productDao.addProduct(pro);
		String jsonString = jackson2Util.toJson(pro);
		System.out.println("jsonString= " + jsonString );
		Product testProduct = jackson2Util.fromJson(jsonString, Product.class);
		
		Product resProduct = productDao.addProduct(testProduct);
		
//		System.out.println("scroll = " + pro.getScrollName() + " name = " + pro.getProductName());
		System.out.println("scroll = " + testProduct.getScrollName() + " name = " + testProduct.getProductName());

//		JpaUtil.getEntityManagerFactory().close();
		System.out.println("..." + resProduct.getProductAdvantages().size());	
	}
	
//	@Test
	public void updateProduct() throws NotFoundException, DBException {
		ProductDao productDao = new ProductDao();		
		Product pro = new Product();
		
		pro.setId(6);
		pro.setScrollName("gate6");
		pro.setProductName("DD6");
		pro.setProductDescription("xx6");
		pro.setStyleId(0);
				
		Function func = new Function();
		func.setFunctionName("功能6-1");
		func.setFunctionContent("ds6-1");
		
		pro.getProductFunctions().add(func);
			
		Advantage adv = new Advantage();
		adv.setAdvantageName("优势6-1");
		adv.setAdvantageContent("内容6-1");
		pro.getProductAdvantages().add(adv);
				
		Product resProduct = productDao.updateProduct(pro);
		System.out.println(resProduct.getScrollName() + "---" + resProduct.getProductFunctions().size()
				 + "----"+ resProduct.getProductFunctions().iterator().next().getFunctionName());
		
		JpaUtil.getEntityManagerFactory().close();
		System.out.println("...");			
	}
	
	@Test
	public void addIconTest() throws DBException {
		EntityManager entityManager = null;
 		EntityTransaction transaction = null;
 		try {
			entityManager = JpaUtil.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			String hql = "insert into icon_info(id,image) values(?,?)";
			Query query = entityManager.createNativeQuery(hql);
			//ByteArrayInputStream stream = new ByteArrayInputStream();
			query.setParameter(1, 25);
			query.setParameter(2, "abdcf".getBytes());
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
		
	@Test
	public void getIconById() {
		IconDao iconDao = new IconDao();
		Icon icon = iconDao.getIconById(11);
		if(icon == null) {
			System.out.println("null****");
		}else {
			System.out.println("*****" + icon.getId());
		}		
	}
	
	@Test
	public void deleteIcon() throws DBException {
		IconDao iconDao = new IconDao();
		Icon icon = new Icon();
		iconDao.deleteIcon(1);
		System.out.println("****");
	}	
	
	@Test
	public void updateIcon() throws DBException{
		IconDao iconDao = new IconDao();
		Icon icon = new Icon();
		icon.setId(23);
		icon.setImage("abcdfrrggtg".getBytes());
		iconDao.update(icon);
		System.out.println("****");
	}
	
	@Test
	public void addImage() throws DBException {
		IconDao iconDao = new IconDao();
		Icon icon = new Icon();
		icon.setId(5);
		icon.setImage("abc".getBytes());
		iconDao.addIcon(icon);	
		System.out.println(icon.getId().equals(5));
	}

	
    @Test
	public void findById() throws DBException {
		ProductDao productDao = new ProductDao();
		Product resProduct = productDao.getProductById(31);
		System.out.println("scroll = " + resProduct.getScrollName());
	}
	
//	@Test
	public void deleteSub() throws DBException {
		ProductDao productDao = new ProductDao();			
		List<Function> res1 = productDao.getFunctions(2);
		for (int i = 0; i < res1.size(); i++) {
			Function function  = res1.get(i);
			System.out.println(function.getFunctionName() + " content = " + function.getFunctionContent());
		}
		productDao.deleteAdvantage(3);
//		System.out.println(productDao.getProductById(3).getProductAdvantages().size());
		JpaUtil.getEntityManagerFactory().close();
		System.out.println("...");
	}
	
   @Test
	public void deleteProductById() throws DBException {
		ProductDao productDao = new ProductDao();
		Product resProduct = productDao.deleteProductById(31);
		System.out.println("scroll = " + resProduct.getScrollName());
		JpaUtil.getEntityManagerFactory().close();
		System.out.println("...");
	}		
	
}
