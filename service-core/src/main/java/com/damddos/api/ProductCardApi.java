package com.damddos.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.damddos.commons.ResponseCode;
import com.damddos.commons.ResponseMessage;
import com.damddos.entity.Icon;
import com.damddos.entity.Product;
import com.damddos.exceptions.DBException;
import com.damddos.service.ProductService;
import com.damddos.utils.Jackson2Util;

@Path("/productCard")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)  
public class ProductCardApi {
	private static final Logger logger = LogManager.getLogger(ProductCardApi.class.getCanonicalName());
	Jackson2Util jackson2Util = Jackson2Util.newInstance();
	
	@GET
	@Path("/display")
	public Response getProducts() {
		List<Product> list = ProductService.getProducts();
		ResponseMessage<List<Product>> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,list);
		String body = jackson2Util.toJson(responseMessage);
		return Response.ok().status(200).entity(body).build();
	}

	@POST
	@Path("/add")
	public Response addProduct(String productJson) {
		try {
			Product product = jackson2Util.fromJson(productJson, Product.class);			
			Product res = ProductService.addProduct(product);			
			String body = "";
			if(res != null) {
				ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"添加成功",res);
				body = jackson2Util.toJson(responseMessage);
			}else {
				ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"添加失败",res);
				body = jackson2Util.toJson(responseMessage);
			}
			return Response.ok().status(200).entity(body).build();
		} catch (Exception e) {
			logger.error("IOException= ", e);					
		}		
		return null;
	}
	
	@DELETE
	@Path("/delete/{id}")
	public Response deleteProductById(@PathParam(value = "id") int id){
		Product product;
		try {
			product = ProductService.deleteProductById(id);
			String body = "";
			if(product != null) {
				ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"删除成功",product);
				body = jackson2Util.toJson(responseMessage);
			}else {
				ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"删除失败",product);
				body = jackson2Util.toJson(responseMessage);
			}
			return Response.ok().status(200).entity(body).build();
		} catch (DBException e) {
			logger.error("DB Exception",e);
		}
		return null;
	}

}
