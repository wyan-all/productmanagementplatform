package testAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;

import com.damddos.commons.ResponseCode;

import java.sql.SQLException;
import java.sql.Statement;

public class TestJdbc {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			// 通过url,user,password连接到数据库，获取到连接对象
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hibernate?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false",
					"root", "131420");
			String str = "select * from student_jpa";
			Statement st = conn.createStatement();// 通过连接对象创建执行sql语句的工具
		    ResultSet rs = st.executeQuery(str);
			while (rs.next()) {
				// 按照列来获取数据
				int sid = rs.getInt(1);// 获取第一列数据
				String sname = rs.getString(2);// 获取第二列数据
				String ssex = rs.getString(3);// 获取第三列数据
				System.out.println(sid + "\t" + sname + "\t" + ssex);
			}
			// 释放资源
			rs.close();
			st.close();
			conn.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 
 * 
 * 
 * */

