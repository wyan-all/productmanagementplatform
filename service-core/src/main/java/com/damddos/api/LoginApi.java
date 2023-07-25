package com.damddos.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.damddos.commons.ResponseCode;
import com.damddos.commons.ResponseMessage;
import com.damddos.commons.exception.Assert;
import com.damddos.entity.User;
import com.damddos.service.UserService;
import com.damddos.utils.Base64;
import com.damddos.utils.GraphicHelper;
import com.damddos.utils.Jackson2Util;

@Path("/userInfo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)  
public class LoginApi{
	private static final Logger logger = LogManager.getLogger(LoginApi.class.getCanonicalName());
	Jackson2Util jackson2Util = Jackson2Util.newInstance();
	
	@POST
	@Path("/login")	
	public Response login(@QueryParam("authCode") String authCode,String userJson,@Context HttpServletRequest request) {
		User user;
		try {
			user = jackson2Util.fromJson(userJson, User.class);			
			String name = user.getUserName();
			String password = user.getPassword();
			String sessionAuthCode = ""+ request.getSession().getAttribute("authCode");
			
			Assert.notEmpty(name, ResponseCode.USERNAME_NULL_ERROR);
			Assert.notEmpty(password, ResponseCode.PASSWORD_NULL_ERROR);
			Assert.notEmpty(sessionAuthCode, ResponseCode.CODE_NULL_ERROR);
			
			User res = UserService.login(user);
			String body ="";
			if(sessionAuthCode.equals(authCode) && res != null) {
				ResponseMessage<User> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"登录成功",res);
				body = jackson2Util.toJson(responseMessage);
			}else {
				ResponseMessage<User> responseMessage = ResponseMessage.build(ResponseCode.SUCCESS,"登陆失败",res);
				body = jackson2Util.toJson(responseMessage);
			}
			return Response.ok().status(200).entity(body).build();			
		} catch (IOException e) {		
			logger.error("IOException= ",e);
		}
		return null;
	}
	
	@GET
	@Path("/getVerifyCode")
    public void getCode(@Context HttpServletRequest request, @Context HttpServletResponse response){
		HttpSession session = request.getSession();		
		final int width = 152;
		final int height = 40;
		final String imageType = "jpeg";
		OutputStream output;
		try {
			output = response.getOutputStream();        //图片直接输出到前端
			String code = GraphicHelper.create(width, height, imageType, output);
			System.out.println("验证码内容= " + code);
			session.setAttribute("authCode", code);
		} catch (IOException e) {
			logger.error("Exception",e);			
		}
    }
	
	@GET
	@Path("/getCode")
    public void getVerifyCode(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();		
		final int width = 152;
		final int height = 40;
		final String imageType = "jpeg";
		ByteArrayOutputStream output = new ByteArrayOutputStream();		
		String code = GraphicHelper.create(width, height, imageType, output);
		session.setAttribute("authCode", code);
		
        byte[] captcha = output.toByteArray();

        Base64 base64Encoder = new Base64();
        String imageStr =  base64Encoder.encode(captcha);// 返回Base64编码过的字节数组字符串
        System.out.println("------ imagestr----------:"+ imageStr);
        
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("image_base64", imageStr);
        
        response.getWriter().write(jackson2Util.toJson(res).toString());
        response.getWriter().close();
	}
}
