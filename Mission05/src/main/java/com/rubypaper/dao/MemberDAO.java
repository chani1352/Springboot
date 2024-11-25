package com.rubypaper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rubypaper.domain.MemberDTO;

@Repository
public class MemberDAO {
	private Connection con;
	
	public MemberDAO() {
		try {
			String url = "jdbc:mysql://localhost:3306/bootmission";
			String id = "musthave";
			String pwd = "Tiger12#$";
			con = DriverManager.getConnection(url,id,pwd);
		}catch (Exception e) {
			System.out.println("접속안됨");
			e.printStackTrace();
		}
	}
	
	public Map<String,Object> getMember(){
		Map<String,Object> map = new HashMap<>(); 
		List<MemberDTO> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try {
			query = "SELECT * FROM member";
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
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		map.put("query",query);
		map.put("method", "Get");
		map.put("result", 1);
		map.put("list", list);
		
		return map;
	}
	
	public Map<String,Object> getMemberById(Integer id) {
		Map<String,Object> map = new HashMap<>(); 
		MemberDTO dto = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try {
			query = "SELECT * FROM member"
					+ " WHERE id= " + id;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
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
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		map.put("query",query);
		map.put("method", "Get");
		map.put("result", 1);
		map.put("dto", dto);
		
		return map;
	}
	
	public Map<String,Object> addMember(MemberDTO dto) {
		Map<String,Object> map = new HashMap<>(); 
		int result = 0;
		PreparedStatement psmt = null;
		String queryQ = "";
		try {
			String query = "INSERT INTO member(pass,name) "
						+ " VALUES(?,?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getPass());
			psmt.setString(2, dto.getName());
			result = psmt.executeUpdate();
			queryQ = psmt.toString().substring(psmt.toString().indexOf(":") +2);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null) psmt.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("query",queryQ);
		map.put("method", "Post");
		map.put("result", result);
		return map;
	}
	
	public Map<String,Object> updateMember(MemberDTO dto) {
		Map<String,Object> map = new HashMap<>(); 
		int result = 0;
		MemberDTO  m = (MemberDTO)getMemberById(dto.getId()).get("dto");
		PreparedStatement psmt = null;
		String queryQ = "";
		if(m == null) {
			map.put("result", result);
			return map;
		}

		try {
			if(dto.getPass() == null) {
				String query = "UPDATE member SET name=? WHERE id=? ";
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getName());
				psmt.setInt(2, dto.getId());
			} else if(dto.getName() == null) {
				String query = "UPDATE member SET pass=? WHERE id=? ";
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getPass());
				psmt.setInt(2, dto.getId());
			} else {
				String query = "UPDATE member SET pass=?, name=? WHERE id=? ";
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getPass());
				psmt.setString(2, dto.getName());
				psmt.setInt(3, dto.getId());
			}
			result = psmt.executeUpdate();
			queryQ = psmt.toString().substring(psmt.toString().indexOf(":") +2);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null) psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("query",queryQ);
		map.put("method", "Put");
		map.put("result", result);
		
		return map;
	}
	
	public Map<String,Object> removeMember(Integer id) {
		Map<String,Object> map = new HashMap<>();
		int result = 0;
		PreparedStatement psmt = null;
		String queryQ = "";
		try {
			String query = "DELETE FROM member WHERE id=?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			result = psmt.executeUpdate();
			queryQ = psmt.toString().substring(psmt.toString().indexOf(":") +2);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null)psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("query",queryQ);
		map.put("method", "Delete");
		map.put("result", result);
		return map;
	}
	
	
	

}
