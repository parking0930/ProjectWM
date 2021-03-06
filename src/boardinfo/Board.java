package boardinfo;

public class Board {
	private String db_name;
	private String name;
	private String intro;
	private String id;
	private String title;
	private String contents;
	private String writer;
	private String date;
	private String view;
	
	public Board() {
		
	}
	public Board(String title, String contents, String writer) {
		this.title = title;
		this.contents = contents;
		this.writer = writer;
	}
	public void setNameIntro(String board) {
		switch(board){
			case "free":
				this.setName("자유게시판");
				this.setIntro("자유롭게 글을 쓸 수 있는 게시판입니다.");
				break;
			case "tactic":
				this.setName("공략게시판");
				this.setIntro("게임 공략을 작성할 수 있는 게시판입니다.");
				break;
			case "screenshot":
				this.setName("스크린샷");
				this.setIntro("게임 스크린샷을 올릴 수 있는 게시판입니다.");
				break;
			case "notice":
				this.setName("공지사항");
				this.setIntro("각종 소식을 받아 볼 수 있는 공지사항 게시판입니다.");
				break;
			case "event":
				this.setName("이벤트");
				this.setIntro("이벤트 소식을 받아볼 수 있는 게시판입니다.");
				break;
			default:
				this.setName("자유게시판");
				this.setIntro("자유롭게 글을 쓸 수 있는 게시판입니다.");
				break;
		}
	}
	public String getDb_name() {
		return db_name;
	}
	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}
