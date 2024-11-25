package com.rubypaper.logdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class LogDAO {

	private Connection con;

	public LogDAO() {
		try {
			String url = "jdbc:mysql://localhost:3306/bootmission";
			String id = "musthave";
			String pwd = "Tiger12#$";
			con = DriverManager.getConnection(url, id, pwd);
		} catch (Exception e) {
			System.out.println("접속 안됨");
			e.printStackTrace();
		}
	}
	public void logData(Map<String,Object> map) {
		PreparedStatement psmt = null;
			try {
				String query = "INSERT INTO dblog(method,sqlstring,success) "
						+ " VALUES(?,?,?)";
				psmt = con.prepareStatement(query);
				psmt.setString(1,map.get("method").toString());
				psmt.setString(2, map.get("query").toString());
				psmt.setInt(3, (int)map.get("result"));
				psmt.executeUpdate();	
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
					try {
						if(psmt != null)psmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
	}
}
