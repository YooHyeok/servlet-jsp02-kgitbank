package lab.web.model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//비밀번호 가져오도록 만들어놓은 DAO

public class MemberDAO {
	static { //드라이버 로드

		//초기화자 {}: 인스턴스 객체를 만들때 초기화를이곳에씀

		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("드라이버 성공");
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
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
	
	private void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			}catch(SQLException e) {}
		}
	}
	
	public void insertMember(MemberVO mem) { //회원 가입 정보 입력
		Connection con = null;
		try {
			con = getConnection();
			String sql = "insert into member values(?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem.getUserId());
			stmt.setString(2, mem.getPassword()); 
			stmt.setString(3, mem.getName());	  
			stmt.setString(4, mem.getEmail());
			stmt.setString(5, mem.getAddress());
			if(!(stmt.executeUpdate()>0)) {
				throw new RuntimeException("입력이 되지 않았습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		
	}
	
	public String getPassword(String userid) { //비밀번호를 불러오는 메서드? //해당 비밀번호의 기준이될수있는건 id
		Connection con = null;
		String pw = null;
		try {
			con=getConnection();
			String sql = "select password from member where userid=?";
			PreparedStatement stmt = con.prepareCall(sql); //?에 입력받은 userid 넣어주는 코드
			stmt.setString(1, userid);					   //?에 입력받은 userid 넣어주는 코드
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString(1); //입력받은 아이디에 해당하는 비밀번호를 가져오는 코드
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return pw;
	}
	
}
