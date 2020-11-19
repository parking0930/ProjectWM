package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import boardinfo.Board;
import commentinfo.Comment;
import userinfo.Member;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/wm?characterEncoding=UTF-8&serverTimezone=UTC";
			String dbID = "root";
			String dbPW = "wogus4735";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean register(Member userinfo) {
		String sql = "INSERT INTO wmusers(id, pw, nickname, email) VALUES(?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userinfo.getId());
			pstmt.setString(2, userinfo.getPw());
			pstmt.setString(3, userinfo.getNickname());
			pstmt.setString(4, userinfo.getEmail());
			
			pstmt.executeUpdate();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean login(String id, String pw) {
		String sql = "SELECT pw FROM wmusers WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(pw)) return true;
				else return false;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkNickname(String nickname) {
		String sql = "SELECT nickname FROM wmusers WHERE nickname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) return false;
			else return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkID(String id) {
		String sql = "SELECT id FROM wmusers WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) return false;
			else return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public Member getUserData(String id) {
		String sql = "SELECT nickname, email, point, profile, level, gm FROM wmusers WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			Member playerInfo = new Member();
			playerInfo.setId(id);
			playerInfo.setNickname(rs.getString(1));
			playerInfo.setEmail(rs.getString(2));
			playerInfo.setPoint(rs.getString(3));
			playerInfo.setProfile(rs.getString(4));
			playerInfo.setLevel(rs.getString(5));
			playerInfo.setGm(rs.getString(6));
			return playerInfo;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void updateUserInfo(Member userinfo) {
		String sql = "UPDATE wmusers SET pw=?, profile=? WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userinfo.getPw());
			pstmt.setString(2, userinfo.getProfile());
			pstmt.setString(3, userinfo.getId());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateNickname(Member userinfo) {
		String sql = "UPDATE wmusers SET point=point-100, nickname=? WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userinfo.getNickname());
			pstmt.setString(2, userinfo.getId());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Member> userRanking() {
		String sql = "SELECT nickname, point, date, level FROM wmusers ORDER BY level DESC, point DESC LIMIT 100";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<Member> userList = new ArrayList<Member>();
			while(rs.next()) {
				Member userinfo = new Member();
				userinfo.setNickname(rs.getString(1));
				userinfo.setPoint(rs.getString(2));
				userinfo.setDate(rs.getString(3));
				userinfo.setLevel(rs.getString(4));
				userList.add(userinfo);
			}
			
			return userList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void levelup(String id) {
		String sql = "UPDATE wmusers SET point=point-(level*100),level=level+1 WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Member getLevelProfile(String nickname) {
		String sql = "SELECT level, profile FROM wmusers WHERE nickname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			Member userinfo = new Member();
			while(rs.next()) {
				userinfo.setNickname(nickname);
				userinfo.setLevel(rs.getString(1));
				userinfo.setProfile(rs.getString(2));
			}
			return userinfo;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
