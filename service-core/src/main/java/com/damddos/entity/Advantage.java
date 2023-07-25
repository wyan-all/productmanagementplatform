package com.damddos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "advantage_info")
public class Advantage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(name = "advantage_name")
	private String advantageName;
	
	@Column(name = "advantage_content")
	private String advantageContent;
	
	@Column(name = "product_id")
	private Integer productId;
	
//	@ManyToOne(targetEntity=Product.class)  
//	@JoinColumn(name = "product_id")
	//private Product product;

	
//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdvantageName() {
		return advantageName;
	}

	public void setAdvantageName(String advantageName) {
		this.advantageName = advantageName;
	}

	public String getAdvantageContent() {
		return advantageContent;
	}

	public void setAdvantageContent(String advantageContent) {
		this.advantageContent = advantageContent;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}

