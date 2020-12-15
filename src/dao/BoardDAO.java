package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boardinfo.Board;
import commentinfo.Comment;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BoardDAO() {
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
	
	public void close() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			
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
			rs.close();
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
			while(rs.next()) {
				boardinfo.setId(rs.getString(1));
				boardinfo.setTitle(rs.getString(2));
				boardinfo.setContents(rs.getString(3));
				boardinfo.setWriter(rs.getString(4));
				boardinfo.setDate(rs.getString(5));
				boardinfo.setView(rs.getString(6));
			}
			rs.close();
			return boardinfo;
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
			rs.close();
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
	public void updateMyWrite(Board board, String id) {
		String sql = "UPDATE "+board.getDb_name()+" SET title=?, contents=? WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContents());
			pstmt.setString(3, id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Board getMyWrite(Board board) {
		String sql = "SELECT id FROM "+board.getDb_name()+" WHERE title=? AND writer=? ORDER BY date desc LIMIT 1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				board.setId(rs.getString(1));
			}
			rs.close();
			return board;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void insertBoard(Board board) {
		String sql = "INSERT INTO "+board.getDb_name()+"(title, contents, writer) VALUES(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContents());
			pstmt.setString(3, board.getWriter());
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
			rs.close();
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
			rs.close();
			return count;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void deleteMyWrite(Board boardinfo) {
		String sql = "DELETE FROM "+boardinfo.getDb_name()+" WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardinfo.getId());
			pstmt.executeUpdate();
			return;
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	public void deleteComments(Board boardinfo) {
		String sql = "DELETE FROM comment WHERE board=? AND bid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardinfo.getDb_name());
			pstmt.setString(2, boardinfo.getId());
			pstmt.executeUpdate();
			return;
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
