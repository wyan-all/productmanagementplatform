package com.damddos.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "product_info") 
public class Product {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)  
//	identity  主键由数据库自动生成（主要是自动增长型）
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "scroll_name")
	private String scrollName;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_description")
	private String productDescription;	
	
	@Column(name = "style_id")
	private Integer styleId;

    /***
              * 单向 1 对 n 的关联关系
              * 注意：customer_id 表示在 n 的那张表里生成的外键字段叫 customer_id
     * fetch 设置为饿汉式加载，使用 join 查询
     * cascade 设置成删除 1 后，其相关的 n 也删除（级联）---级联操作
     */
	
	@OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})	
	@JoinColumn(name = "product_id")
    private Set<Function> productFunctions = new HashSet<Function>() ;
// EAGER 急加载 ；    即指一旦主表进行了相应操作，则从表也将立即进行相应的级联操作
//	懒加载： 当需要使用到getFunctions()方法时，才会读取相关联的级联表数据
	@OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	@JoinColumn(name = "product_id")
	private Set<Advantage> productAdvantages = new HashSet<Advantage>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getScrollName() {
		return scrollName;
	}

	public void setScrollName(String scrollName) {
		this.scrollName = scrollName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Integer getStyleId() {
		return styleId;
	}

	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}

	public Set<Function> getProductFunctions() {
		return productFunctions;
	}

	public void setProductFunctions(Set<Function> productFunctions) {
		this.productFunctions = productFunctions;
	}

	public Set<Advantage> getProductAdvantages() {
		return productAdvantages;
	}

	public void setProductAdvantages(Set<Advantage> productAdvantages) {
		this.productAdvantages = productAdvantages;
	}

	
	
}

