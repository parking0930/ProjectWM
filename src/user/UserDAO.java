package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	public boolean register(Member m) {
		String sql = "INSERT INTO wmusers(id, pw, nickname, email) VALUES(?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPw());
			pstmt.setString(3, m.getNickname());
			pstmt.setString(4, m.getEmail());
			
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
	public String[] getUserData(String id) {
		String sql = "SELECT nickname, email, point, profile, level, gm FROM wmusers WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			String[] playerInfo = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
			return playerInfo;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void updateUserInfo(String id, String pw, String profile) {
		String sql = "UPDATE wmusers SET pw=?, profile=? WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, profile);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateNickname(String id, String nickname) {
		String sql = "UPDATE wmusers SET point=point-100, nickname=? WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> userRanking() {
		String sql = "SELECT nickname, point, date, level FROM wmusers ORDER BY level DESC, point DESC LIMIT 100";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<String> userList = new ArrayList<String>();
			while(rs.next()) {
				userList.add(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4));
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
	public ArrayList<String> getBoardData(String board) {
		String sql = "SELECT id, title, writer, date, view FROM "+board+" ORDER BY id DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<String> userList = new ArrayList<String>();
			while(rs.next()) {
				userList.add(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));
			}
			
			return userList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String getBoardContentsData(String board, String id) {
		String sql = "SELECT id, title, contents, writer, date, view FROM "+board+" WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			String bData = "";
			while(rs.next()) {
				bData = rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6);
			}
			return bData;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String getLevelProfile(String nickname) {
		String sql = "SELECT level, profile FROM wmusers WHERE nickname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			String bData = "";
			while(rs.next()) {
				bData = rs.getString(1)+"|"+rs.getString(2);
			}
			return bData;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<String> getMainBoardData(String board) {
		String sql = "SELECT id, title, date FROM "+board+" ORDER BY id DESC LIMIT 8";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<String> userList = new ArrayList<String>();
			while(rs.next()) {
				userList.add(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3));
			}
			
			return userList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void updateView(String board, String id) {
		String sql = "UPDATE "+board+" SET view=view+1 WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
