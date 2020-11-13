package userinfo;

public class Member {
	private String id;
	private String pw;
	private String nickname;
	private String email;
	private String point;
	private String level;
	private String date;
	private String profile;
	private String gm;

	public Member() {
		
	}
	public Member(String id, String pw, String nickname, String email) {
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.email = email;
	}
	public String getGm() {
		return gm;
	}
	public void setGm(String gm) {
		this.gm = gm;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
