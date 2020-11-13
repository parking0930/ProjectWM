package user;

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
	public ArrayList<Board> getBoardData(String bname) {
		String sql = "SELECT id, title, writer, date, view FROM "+bname+" ORDER BY id DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<Board> boardList = new ArrayList<Board>();
			while(rs.next()) {
				Board board = new Board();
				board.setDb_name(bname);
				board.setId(rs.getString(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setDate(rs.getString(4));
				board.setView(rs.getString(5));
				boardList.add(board);
			}
			
			return boardList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Board getBoardContentsData(Board boardinfo) {
		String sql = "SELECT id, title, contents, writer, date, view FROM "+boardinfo.getDb_name()+" WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardinfo.getId());
			rs = pstmt.executeQuery();
			String bData = "";
			while(rs.next()) {
				boardinfo.setId(rs.getString(1));
				boardinfo.setTitle(rs.getString(2));
				boardinfo.setContents(rs.getString(3));
				boardinfo.setWriter(rs.getString(4));
				boardinfo.setDate(rs.getString(5));
				boardinfo.setView(rs.getString(6));
			}
			return boardinfo;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
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
	public ArrayList<Board> getMainBoardData(String board) {
		String sql = "SELECT id, title, date FROM "+board+" ORDER BY id DESC LIMIT 8";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<Board> boardList = new ArrayList<Board>();
			while(rs.next()) {
				Board boardinfo = new Board();
				boardinfo.setId(rs.getString(1));
				boardinfo.setTitle(rs.getString(2));
				boardinfo.setDate(rs.getString(3));
				boardList.add(boardinfo);
			}
			
			return boardList;
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
	public void insertComment(Comment comment) {
		String sql = "INSERT INTO comment(bid, board, comment, writer) VALUES(?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getId());
			pstmt.setString(2, comment.getBoard());
			pstmt.setString(3, comment.getComment());
			pstmt.setString(4, comment.getWriter());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Comment> getComment(Board boardinfo) {
		String sql = "SELECT comment, writer, date FROM comment WHERE board=? AND bid=? ORDER BY id DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardinfo.getDb_name());
			pstmt.setString(2, boardinfo.getId());
			rs = pstmt.executeQuery();
			ArrayList<Comment> commentList = new ArrayList<Comment>();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setComment(rs.getString(1));
				comment.setWriter(rs.getString(2));
				comment.setDate(rs.getString(3));
				commentList.add(comment);
			}
			return commentList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String getCommentCount(Board boardinfo) {
		String sql = "SELECT count(*) FROM comment WHERE board=? AND bid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardinfo.getDb_name());
			pstmt.setString(2, boardinfo.getId());
			rs = pstmt.executeQuery();
			String count = "";
			while(rs.next()) {
				count = rs.getString(1);
			}
			return count;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
