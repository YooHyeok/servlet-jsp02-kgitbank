package lab.web.vo;

//Day04

public class MemberVO {
	//private 선언 이유 : 직접 접근을 막기 위해
	//VO 는 무조건 get set
	private String id;
	private String pw;
	private String name;
	private String tel;
	private java.util.Date birth;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public java.util.Date getBirth() {
		return birth;
	}
	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//생성자 
	//만든 이유 = 담아둘 DAO(데이터베이스)가 없으므로?
	public MemberVO(String id, String pw, String name, java.util.Date birth) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
	}
	
	public MemberVO(String id, String pw, String name, String tel) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
	}
}
