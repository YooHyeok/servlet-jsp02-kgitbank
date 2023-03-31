package lab.web.vo;

import java.util.ArrayList;

public class MemberVO {
	
	private String id;
	private String pw;
	private String name;
	private java.util.Date birth;
	private char gender;
	private ArrayList<String> hobby = new ArrayList<>();
	private String area;
	private String introduce;
	
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public ArrayList<String> getHobby() {
		return hobby;
	}
	public void setHobby(ArrayList<String> hobby) {
		this.hobby = hobby;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	// 취미(선택된 요소들)에 대한 생성자 대신
	public void addHobby(String[] hobbys) {
		for(String h : hobbys) {	//hobbys에서 h를 하나씩 뺀다
			this.hobby.add(h); //h를 hobby에 저장하여 그것을 hobby를 향하여 가르킨다
							   //this.hobby는  선언된 객체 ArrayList<String> hobby를 가르킴	
		}
	}
}
