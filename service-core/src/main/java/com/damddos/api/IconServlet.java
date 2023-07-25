package com.damddos.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.util.TextUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.damddos.dao.IconDao;
import com.damddos.entity.Icon;
import com.damddos.exceptions.DBException;
import com.damddos.utils.Jackson2Util;

public class IconServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(IconServlet.class.getCanonicalName());
	Jackson2Util jackson2Util = Jackson2Util.newInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream inputStream = request.getInputStream();
        String iconId = request.getParameter("id");
		int id=1;
		if(TextUtils.isEmpty(iconId)){
			id=1;
		}else{
			id = Integer.parseInt(iconId);
		}
	    try {
	    	Icon icon = addOrUpdate(id,inputStream,response);						
		} catch (IOException | DBException e1) {
			logger.info(e1);
		}
	}
	
	public Icon addOrUpdate(int id, InputStream fileInputStream,HttpServletResponse response) throws IOException, DBException{
		if(fileInputStream == null) {
			logger.error("输入流为null");
		}		
		byte[] bytes = new byte[2048];
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int read = 0;
		try {			
			while((read = fileInputStream.read(bytes)) != -1) {
				output.write(bytes,0,read);
			}
			byte[] fileByte = output.toByteArray();
			Icon icon = new Icon();
			icon.setId(id);
			icon.setImage(fileByte);	//获取到的前端Icon
						
			IconDao iconDao = new IconDao();
			Icon dbIcon = iconDao.getIconById(id);
			Icon resIcon = new Icon();
			if(dbIcon == null) {
				resIcon = IconDao.addIcon(icon);
				if(resIcon != null) {
//					返回页面的处理结果？？
					response.getWriter().println("add icon sucess-----");
				}else {
					response.getWriter().println("add icon fail");
				}
			} else {
				iconDao.deleteIcon(id);
				resIcon = iconDao.addIcon(icon);
//				resIcon = IconDao.update(icon);
				if(resIcon != null) {
					response.getWriter().println("update icon sucess");
				}else {
					response.getWriter().println("update icon fail");
				}
			}
			return resIcon;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fileInputStream != null) {
				fileInputStream.close();
			}
			if(output != null) {
				output.close();
			}
		}
		return null;
	}
	
}


