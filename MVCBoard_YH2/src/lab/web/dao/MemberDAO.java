package lab.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lab.web.vo.MemberVO;

public class MemberDAO {
	//static 초기화자 {}: 인스턴스 객체를 만들때 초기화를이곳에씀
	static { //드라이버 로드
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("드라이버 성공");
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	private void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			}catch(SQLException e) {}
		}
	}
	private Connection getConnection() { //커넥션 생성
		DataSource ds = null;
		Connection con = null;//sql임포트
		try {
			Context ctx = new InitialContext();//네이밍기법이므로 네이밍으로 임포트
			//ctx.lookup("java:comp/env/jdbc/oracle"); //lookup 찾는메서드
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}	
	/*
	insert작성할때
	집에가서 171쪽 pstmt의 2번이 getPassword 그리고 3번이 getName으로 바꿔서 해줘야함 
	왜냐면 우리가 SQL내에서의 테이블 만들었던 순서는 교안과 다르기떄문
	 */
	public void insert (MemberVO member) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "insert into member values(?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getAddress());
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			if(e.getMessage().contains("unique")) {
				//contains() : 특정 문자열을 포함한 요소를 선택하는 선택자
				throw new RuntimeException("아이디가 중복됩니다.");
			}else {
				e.printStackTrace();
				throw new RuntimeException("MemberDAO.insert()예외발생-콘솔확인");
			}
		}finally {
			closeConnection(con);
		}
	}
	public MemberVO selectMember(String userid) {
		Connection con = null;
		MemberVO member = new MemberVO();
		try {
			con = getConnection();
			String sql = "select * from member where userid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setUserid(userid);
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
			}
		}
		catch(SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDAO.selectMember()예외발생-콘솔확인");
		}finally {
			closeConnection(con);
		}
		return member;		
	}
	public void updateMember(MemberVO member) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "update member set email=?, address=?, name=?,"
					+ "password=? where userid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			//순서 주의. where의 마지막절에 userid가 들어가야 정상실행 가능.
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getAddress());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPassword());
			pstmt.setString(5, member.getUserid());
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			
				e.printStackTrace();
				throw new RuntimeException("MemberDAO. ()예외발생-콘솔확인");
				
		}finally {
			closeConnection(con);
		}
	}
	public String getPassword(String userid) { //회원 비밀번호를 가져오는 메서드 (회원정보삭제에 사용)
		String pw = "";
		Connection con = null;
		try {
			con = getConnection();
			String sql = "select password from member where userid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString("password");
			}
		}
		catch(SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDAO.getPassword()예외발생-콘솔확인");
		}finally {
			closeConnection(con);
		}
		return pw;
	}	
	public void deleteMember(String userid, String password) {
		Connection con = null;
		String pw = "";
		try {
			con = getConnection();
			con.setAutoCommit(false);
			String sql = "select password from member where userid=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString("password");
				System.out.println(pw);
				System.out.println(password);
			}else {
				throw new RuntimeException("아이디가 잘못 입력되었습니다.");
			}
			if(pw.equals(password)) {
				try {
					String sql2 = "delete from board where masterid in (select bbsno from board where userid=?)";
					//masterid를기준으로 지우는이유는 bbsno를기준으로지우면 다른사람이 해당id에 답글을단것은 걸러지지않아 남게된다
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, userid);
					pstmt.executeUpdate();
					
					String sql3 = "delete from member where userid=?";
					pstmt = con.prepareStatement(sql3);
					pstmt.setString(1, userid);
					pstmt.executeUpdate();
					con.commit();
				}catch(SQLException e) {
					con.rollback();
					e.printStackTrace();
					throw new RuntimeException("삭제가 되지 않았습니다 : "+e.getMessage());
				}	
			}else {
				
				throw new RuntimeException("비밀번호가 다릅니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDAO.deleteMember()예외발생-콘솔확인");
		}finally {
			closeConnection(con);
		}
	}
	
}
