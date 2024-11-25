package com.rubypaper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rubypaper.domain.MemberDTO;


public class MemberDAO {
	private Connection con;
	
	public MemberDAO() {
		try {
			String url = "jdbc:mysql://localhost:3306/bootmission";
			String id = "musthave";
			String pwd = "Tiger12#$";
			con = DriverManager.getConnection(url,id,pwd);
		} catch(Exception e) {
			System.out.println("접속안됨");
			e.printStackTrace();
		}
	}
	
	public List<MemberDTO> getMember(){
		List<MemberDTO> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM member";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				MemberDTO dto = MemberDTO.builder()
						.id(rs.getInt(1))
						.pass(rs.getNString(2))
						.name(rs.getString(3))
						.regidate(rs.getDate(4))
						.build();
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public MemberDTO getMemberById(Integer id) {
		MemberDTO dto = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM member"
						+ " WHERE id= " + id;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				dto = MemberDTO.builder()
					.id(rs.getInt(1))
					.pass(rs.getString(2))
					.name(rs.getString(3))
					.regidate(rs.getDate(4))
					.build();
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return dto;	
	}
	
	public int addMember(MemberDTO dto) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			String query = "INSERT INTO member(pass,name) "
						+ " VALUES(?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getPass());
			psmt.setString(2, dto.getName());
			result = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null)psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}

	
}
