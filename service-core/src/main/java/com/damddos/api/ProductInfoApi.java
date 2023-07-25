package com.damddos.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.io.input.InfiniteCircularInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.damddos.commons.ResponseCode;
import com.damddos.commons.ResponseMessage;
import com.damddos.dao.IconDao;
import com.damddos.entity.Product;
import com.damddos.service.ProductService;
import com.damddos.utils.Base64;
import com.damddos.utils.Jackson2Util;
import com.sun.jersey.core.header.FormDataContentDisposition;


@Path("/productInfo")
@Produces(MediaType.APPLICATION_JSON)  
@Consumes(MediaType.APPLICATION_JSON)
public class ProductInfoApi {
	private static final Logger logger = LogManager.getLogger(ProductInfoApi.class.getCanonicalName());

	Jackson2Util jackson2Util = Jackson2Util.newInstance();
	
	@GET
	@Path("/name/{id}")
	public Response getProductsById(@PathParam(value = "id") int id) {
		Product product = ProductService.getProductById(id);
		String body = "";
		if(product != null) {						
			ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"查询成功",product);
			body = jackson2Util.toJson(responseMessage);
		} else {			
			body = jackson2Util.toJson(ResponseMessage.build(ResponseCode.ERROR,"查询失败",product));
			return Response.ok().status(200).entity(body).build();
		}	
		return Response.ok().status(200).entity(body).build();
	}
	
	@POST
	@Path("/update")
	public Response updateProduct(String productJson){	
		Product product = null;		
		try {
			product = jackson2Util.fromJson(productJson.toString(), Product.class);
			Product res = new Product();
			String body = "";
			res = ProductService.updateProduct(product);
			if(res != null) {
				ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"更新成功",res);
				body = jackson2Util.toJson(responseMessage);
			} else {
				ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"更新失败",res);
				body = jackson2Util.toJson(responseMessage);
			}	
			return Response.ok().status(200).entity(body).build();
		} catch (Exception e) {
			logger.error("Exception",e);
		}
		return null;		
	}
	
	@POST
	@Path("/saveOrUpdate")
	public Response saveOrUpdate(String productJson){	
		Product product = null;		
		try {
			product = jackson2Util.fromJson(productJson.toString(), Product.class);
			Product res = new Product();
			String body = "";
			if(product.getId() == null) {
				res = ProductService.saveProduct(product); 
				ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"保存成功",res);
				body = jackson2Util.toJson(responseMessage);
			}else {
				res = ProductService.updateProduct(product);
				if(res != null) {
					ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"更新成功",res);
					body = jackson2Util.toJson(responseMessage);
				} else {
					ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"更新失败",res);
					body = jackson2Util.toJson(responseMessage);
				}
			}	
			return Response.ok().status(200).entity(body).build();
		} catch (Exception e) {
			logger.error("Exception",e);
		}
		return null;		
	}
	
	
}
	

	
	