package lab.web.vo;

public class InfoVO {
	private String id;
	private String pw;
	private String name;
	private java.util.Date birth;
	private String gender;
	private String hobby;
	private String area;
	private String tosay;

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.util.Date getBirth() {
		return birth;
	}
	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTosay() {
		return tosay;
	}
	public void setTosay(String tosay) {
		this.tosay = tosay;
	}

	public InfoVO(String id, String pw, String name, java.util.Date birth, String gender, String hobby, String area, String tosay) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.hobby = hobby;
		this.area = area;
		this.tosay = tosay;
	}
}

