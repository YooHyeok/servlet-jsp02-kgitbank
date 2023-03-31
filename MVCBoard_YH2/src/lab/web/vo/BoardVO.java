package lab.web.vo;

import java.sql.Date;

public class BoardVO {
	private int bbsno;
	private String userid;
	private String name;
	private String password;
	private String subject;
	private String content;
	private Date writeDate; //Date 는 sql로 임포트해줘야함
	private int readCount;
	private int masterId;
	private int replyNumber;
	private int replyStep;
	public int getBbsno() {
		return bbsno;
	}
	public void setBbsno(int bbsno) {
		this.bbsno = bbsno;
	}
	public String getUserId() {
		return userid; //EL표현식은 get메서드이름의 첫글자가아닌 중간의 대소문자만 구문해서 사용함.
		//변수가 소문자여도 상관이없음
	}
	public void setUserId(String userId) {
		this.userid = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getMasterId() {
		return masterId;
	}
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}
	public int getReplyNumber() {
		return replyNumber;
	}
	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}
	public int getReplyStep() {
		return replyStep;
	}
	public void setReplyStep(int replyStep) {
		this.replyStep = replyStep;
	}
	
	
}
