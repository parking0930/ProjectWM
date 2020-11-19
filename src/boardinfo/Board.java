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
				this.setName("�����Խ���");
				this.setIntro("�����Ӱ� ���� �� �� �ִ� �Խ����Դϴ�.");
				break;
			case "tactic":
				this.setName("�����Խ���");
				this.setIntro("���� ������ �ۼ��� �� �ִ� �Խ����Դϴ�.");
				break;
			case "screenshot":
				this.setName("��ũ����");
				this.setIntro("���� ��ũ������ �ø� �� �ִ� �Խ����Դϴ�.");
				break;
			case "notice":
				this.setName("��������");
				this.setIntro("���� �ҽ��� �޾� �� �� �ִ� �������� �Խ����Դϴ�.");
				break;
			case "event":
				this.setName("�̺�Ʈ");
				this.setIntro("�̺�Ʈ �ҽ��� �޾ƺ� �� �ִ� �Խ����Դϴ�.");
				break;
			default:
				this.setName("�����Խ���");
				this.setIntro("�����Ӱ� ���� �� �� �ִ� �Խ����Դϴ�.");
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
