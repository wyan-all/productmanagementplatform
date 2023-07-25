package com.damddos.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import com.damddos.commons.ResponseCode;
import com.damddos.commons.ResponseMessage;
import com.damddos.dao.ProductDao;
import com.damddos.entity.Advantage;
import com.damddos.entity.Product;
import com.damddos.exceptions.DBException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson2Util {

	public static Jackson2Util newInstance() {
		return new Jackson2Util();
	}

	private ObjectMapper mapper;

	public Jackson2Util() {
		mapper = new ObjectMapper();
		// default null not absent
		mapper.setSerializationInclusion(Include.NON_NULL);
		// ignore those exist in json but not in java
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// forbid the deserialize int for Enum order()
		mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
		// map array to list
		mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, false);

		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
	}

	/**
	 * For Generic Type, like List<MyBean>, call
	 * constructParametricType(ArrayList.class,MyBean.class) Map<String,MyBean> call
	 * (HashMap.class,String.class, MyBean.class)
	 */
	public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
		return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
	}

	@SuppressWarnings("unchecked")
	public <T> T fromJson(String jsonString, Class<?> parametrized, Class<?>... parameterClasses) throws IOException {

		return (T) this.fromJson(jsonString, constructParametricType(parametrized, parameterClasses));
	}

	/**
	 * if JSON string is Null or "null"String , return Null. if JSON string is "[]", return empty
	 * collection. if read List/Map, use constructParametricTypeto construct type first.
	 *
	 * @see #constructParametricType(Class, Class...)
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) throws IOException {
		return mapper.readValue(jsonString, clazz);
	}

	/**
	 * if JSON string is Null or "null"String , return Null. if JSON string is "[]", return empty
	 * collection. if read List/Map, use constructParametricTypeto construct type first.
	 *
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @see #constructParametricType(Class, Class...)
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromJson(String jsonString, JavaType javaType) throws IOException {
		// jsonString = URLDecoder.decode(jsonString, "UTF-8");
		return (T) mapper.readValue(jsonString, javaType);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> fromJsonToList(String jsonString, Class<T> classMeta) throws IOException {

		return (List<T>) this.fromJson(jsonString, constructParametricType(List.class, classMeta));
	}

	public void setDateFormat(DateFormat dateFormat) {
		mapper.setDateFormat(dateFormat);
	}

	/**
	 * if object is null , return null if object is null collection, return "[]"
	 */
	public String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		}
		catch (IOException e) {
			return null;
		}
	}

	public String toPrettyJson(Object object) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		}
		catch (IOException e) {
			return null;
		}
	}
	
	public static void  main(String[] args) throws IOException, DBException {
//		Product pro = new Product();
//		
//		pro.setId(6);
//		pro.setScrollName("gate6");
//		pro.setProductName("DD6");
//		pro.setProductDescription("xx6");
		Product pro = new Product();
		
		pro.setScrollName("gate15");
		pro.setProductName("tst15");
		pro.setProductDescription("xx13");
		pro.setStyleId(0);
		//Set<Advantage> advantages = new HashSet<Advantage>();
		Advantage advantage = new Advantage();
		advantage.setAdvantageName("adv15");
		advantage.setAdvantageContent("sss15");
		//advantage.setProduct(pro);
		//advantages.add(advantage);
		pro.getProductAdvantages().add(advantage);
		
		Product resProduct = ProductDao.addProduct(pro);
		
		
		ResponseMessage<Product> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,resProduct);
		System.out.println(responseMessage.getMessage()+ " " + responseMessage.getResult().getProductName());
		System.out.println("json= " + Jackson2Util.newInstance().toJson(responseMessage));
		
		
		String string = Jackson2Util.newInstance().toJson(pro);
//  反序列化
		
		System.out.println("*******");
		
		
		String jsonString = Jackson2Util.newInstance().toJson(resProduct);
		System.out.println("jsonString = " + jsonString);
		Product product = Jackson2Util.newInstance().fromJson(jsonString, Product.class);

		Set<Advantage> set = product.getProductAdvantages();
		for(Advantage adv : set) {
			System.out.println("name = " + adv.getAdvantageName());				
		}
	}


}
