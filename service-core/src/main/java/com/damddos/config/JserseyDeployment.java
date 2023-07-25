package com.damddos.config;

import java.util.HashSet;
import java.util.Set;

import com.damddos.api.LoginApi;
import com.damddos.api.ProductCardApi;
import com.damddos.api.ProductInfoApi;

public class JserseyDeployment extends javax.ws.rs.core.Application{
	public JserseyDeployment() {

	 }

	 @Override
	 public Set<Class<?>> getClasses() {
	  Set<Class<?>> s = new HashSet<>();
	  s.add(ProductInfoApi.class);
	  s.add(ProductCardApi.class);
	  s.add(LoginApi.class);
	  return s;
	 }
	 
}

