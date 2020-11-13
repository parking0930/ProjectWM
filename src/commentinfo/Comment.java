package commentinfo;

public class Comment {
	private String id;
	private String board;
	private String comment;
	private String writer;
	private String date;
	
	public Comment() {
		
	}
	public Comment(String id, String board, String comment, String writer) {
		this.id = id;
		this.board = board;
		this.comment = comment;
		this.writer = writer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
}
