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
			String url ="jdbc:mysql://localhost:3306/bootmission";
			String id = "musthave";
			String pwd = "Tiger12#$";
			con = DriverManager.getConnection(url,id,pwd);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("접속안됨");
		}
	}
	
	public List<MemberDTO> getMember() {
		List<MemberDTO> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM member";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getInt(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getDate(4));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			try {
				if(rs != null)rs.close();
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
			rs=stmt.executeQuery(query);
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setId(rs.getInt(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getDate(4));
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	
	public int addMember(MemberDTO dto){
		int result = 0;
		PreparedStatement psmt = null;
		try {
			String query = "INSERT INTO member(pass,name) "
							+ " VALUES(?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getPass());
			psmt.setString(2, dto.getName());
			result = psmt.executeUpdate();
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
		return result;
	}
	
	public int updateMember(MemberDTO dto) {
		int result = 0;
		MemberDTO m = getMemberById(dto.getId());
		PreparedStatement psmt = null;
		if(m == null) {
			return result;
		}
		try {
			System.out.println("bb");
			String query = "UPDATE member SET  "
						+ " pass=?, name=? "
						+ " where id=? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getPass());
			psmt.setString(2, dto.getName());
			psmt.setInt(3, dto.getId());
			result =  psmt.executeUpdate();
		} catch (Exception e) {
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
	
	public int removeMember(Integer id) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			String query = "DELETE FROM member WHERE id=?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			result = psmt.executeUpdate();
		} catch(Exception e) {
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
