package com.damddos.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.util.TextUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.damddos.dao.IconDao;
import com.damddos.entity.Icon;
import com.damddos.utils.Base64;

public class IconShowServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(IconShowServlet.class.getCanonicalName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request,response);
		IconDao iconDao = new IconDao();
		String iconId = request.getParameter("id");
		int id=1;
		if(TextUtils.isEmpty(iconId)){
			id=1;
		}else{
			id = Integer.parseInt(iconId);
		}
		//注意 通过id查询，此时icon里的picture是byte[]类型
		Icon icon = iconDao.getIconById(id);
		if(icon == null) {			
			response.getWriter().println("image not exit");
		}else {
			byte[] byteArr = (byte[]) icon.getImage();
			
	        //response.setContentType("image/jpeg");
	        response.setContentType("image/png");
	        ServletOutputStream out = response.getOutputStream();
	        out.write(byteArr);
	        out.flush();
	        out.close();
		}

	}
	
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IconDao iconDao = new IconDao();
		String iconId = request.getParameter("id");
		int id=1;
		if(TextUtils.isEmpty(iconId)){
			id=1;
		}else{
			id = Integer.parseInt(iconId);
		}
		//注意 通过id查询，此时profile里的picture是byte[]类型
		Icon icon = iconDao.getIconById(id);
		
		byte[] byteArr = (byte[]) icon.getImage();
		
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        out.write(byteArr);
        out.flush();
        out.close();
        
//		String data=new String(byteArr,"utf-8");
//		Base64 decoder = new Base64();
//		//base64解码成byte[],这样才能生成图片
//		byte[] bytes = decoder.decode(data);
//		for(int i=0;i<bytes.length;++i){
//			//byte是有符号的，它表示的范围是-127~127，
//			// 如果要映射到无符号0~255，那么0~127不用改变，而-128~-1对应128~255
//			if(bytes[i] <0){
//				bytes[i]+=256;
//			}
//		}

//      request.getParameter(“key的名称”) 这种方式获取数据必须是标准的表单提交 方式才可以取到值。（注: application/x-www-form-urlencoded ）
	}
	*/


}
