package lab.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lab.web.vo.BoardVO;
import lab.web.vo.MemberVO;

public class BoardDAO {
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

	
	//글을 삽입하기 위한 메서드.
	public void insertArticle(BoardVO board) { //VO와 DAO를 묶어주는 패키지가 다르므로 임포트해줘야함~
		Connection con = null;
		String sql1 = "select nvl(max(bbsno),0) from board";
		int bbsno = 0;
		String sql2 = "insert into board"
				+ "(bbsno, userid, password, subject, content,"
				+ "writedate, masterid, readcount, replynumber, replystep)"
				+ "values(?,?,?,?,?,SYSDATE,?,0,0,0)"; //writedate:작성일 <= SYSDATE:현재시간
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			bbsno = rs.getInt(1)+1;

			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, bbsno);
			pstmt.setString(2, board.getUserId());
			pstmt.setString(3, board.getPassword());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5, board.getContent());
			pstmt.setInt(6, bbsno);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("BoardDAO.insertArticle()예외발생-콘솔확인");
		}
	}
	//글 목록을 가져오는 메서드
	public Collection<BoardVO> selectArticleList(int page){ 
		Connection con = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		String sql = "select bbsno, name, subject, writedate, readcount, rnum from ("
				+ "select bbsno, name, subject, writedate, readcount, rownum as rnum from("
				+ "select bbsno, name, subject, writedate, readcount from board b "
				+ "join member m on b.userid=m.userid "
				+ "order by masterid desc, replynumber, replystep)) "
				+ "where rnum between ? and ?";
		int start = (page-1) * 10 + 1;
		int end = start+9;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {//
				BoardVO board = new BoardVO();
				board.setBbsno(rs.getInt("bbsno"));
				board.setName(rs.getString("name"));
				board.setSubject(rs.getString("subject"));
				board.setWriteDate(rs.getDate("writedate"));
				board.setReadCount(rs.getInt("readcount"));
				list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("BoardDAO.selectArticleList()예외발생-콘솔확인");
		}finally{
			closeConnection(con);
		}
		return list;
	}
	//글의 상세 내용을 보여주기 위해 게시글 한개만 가져오는 메서드.
	public BoardVO selectArticle(int bbsno) { 
		Connection con = null;
		BoardVO board = null;
		String sql = "select bbsno, name, b.userid, subject, content, readcount,"
				+ "writedate, masterid, replynumber, replystep "
				+ "from board b join member m on b.userid=m.userid where bbsno=?";
		//보여줄 데이터는 이름, 제목, 내용, 조회수, 작성일 이지만 글을 수정 및 삭제하기 위해 필요한 모든 값을 가지고옴

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bbsno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { //next()------------>행(가로)을 찍어줌 (값이 있는지 없는지확인후) 
				//if나 while문으로 넣어서 true일때 실행 false일때 실행안함)
				board = new BoardVO();
				board.setBbsno(rs.getInt("bbsno"));
				board.setName(rs.getString("name"));
				board.setUserId(rs.getString("userid"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readcount"));
				board.setWriteDate(rs.getDate("writedate"));
				board.setMasterId(rs.getInt("masterid"));
				board.setReplyNumber(rs.getInt("replynumber"));
				board.setReplyStep(rs.getInt("replystep"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("BoardDAO. selectArticle()예외발생-콘솔확인");

		}finally {
			closeConnection(con);
		}
		return board;
	}
	//글을 클릭할 경우 조회수를 늘리기 위한 메서드
	public void updateReadCount(int bbsno) { 
		Connection con = null;
		String sql = "update board set readcount=readcount+1 where bbsno=?";
		//게시판에서 선택한 게시글번호의 조회수(readcount)를 올려주는 sql update 쿼리문.
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bbsno);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("BoardDAO.updateReadCount()예외발생-콘솔확인");

		}finally {
			closeConnection(con);
		}
	}
	//글 삭제시 비밀번호를 체크할 메서드
	public String getPassword(int bbsno) { 
		Connection con = null;
		String password = "";
		String sql = "select password from board where bbsno=?";
		//해당 게시글번(bbsno=?)를 board테이블로부터 불러와 비밀번호를 출력하세요 라는 sql쿼리문
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bbsno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				password = rs.getString("password");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("BoardDAO.getPassword()예외발생-콘솔확인");

		}finally {
			closeConnection(con);
		}
		return password;
	}
	//답변(댓글)을 달 경우 답변 글을 작성하는 메서드
	public void replyArticle(BoardVO board) { 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			String sql1 = "update board set replynumber=replynumber+1 where masterid=? and replynumber >?";
			//답글을 달려는 글에 이미 달려있는 답글들의 repltynumber를 올려주는 쿼리문
			//가장 최근에 달린 답글이므로 기존 답글들에게 먼저 달렸다는것을 표시해주기 위해 실행.
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, board.getMasterId());
			pstmt.setInt(2, board.getReplyNumber());
			pstmt.executeUpdate();
			
			String sql2 = "select max(bbsno) from board";
			//답글(bbsno/게시글번호) 역시 글이므로 게시글번호를 현재 게시글번호의 최대값을 가져와 1을 더한 값으로 넣어줌
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setBbsno(rs.getInt(1)+1);
			}
			String sql3 = "insert into board values(?,?,?,?,?,SYSDATE,?,0,?,?)";
			//답글을 insert(수정)하는 쿼리
			pstmt = con.prepareStatement(sql3);
			pstmt.setInt(1, board.getBbsno());
			pstmt.setString(2, board.getUserId());
			pstmt.setString(3, board.getPassword());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5, board.getContent());
			pstmt.setInt(6, board.getMasterId());
			
			pstmt.setInt(7, board.getReplyNumber()+1);
			pstmt.setInt(8, board.getReplyStep()+1);
			pstmt.executeUpdate();
			con.commit();
			//내가 답글에 답글을 다는 경우는 replystep이 2로, 그냥 글에 답글을 다는 경우는 replystep이 1로 들어가야함
			// 또 나는 답글이기에 replynumber값도 하나 증가시켜주어야 답글인것을 표시할 수 있음
			// 해당 상태를 위해 +1씩 올려 값을 집어넣어줌 - 목록을 정렬하기 위해 행함.
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("BoardDAO.replyArticle()예외발생-콘솔확인");

		}finally {
			closeConnection(con);
		}
	}
	
	//글과 답변 모두 삭제해주는 메서드 
	public void deleteArticle(int bbsno, int replynumber) { //매개변수로는 게시글번호와 답글번호가 들어감
		String sql = "";
		Connection con = null;
		try {
			con = getConnection();
			
			if(replynumber>0) {//답글은 무조건 replynumber가 0보다 클수밖에 없음 
				//그냥 글이라면 replynumber는 무조건 0인상태
				sql = "delete from board where bbsno=?"; 
				//답글이라면 게시글 번호를를 기준으로 지우므로 bbsno(게시글 번호)를 기준으로 삭제하는 쿼리
			}else {
				sql = "delete from board where masterid=?";
				//그냥 글이라면 딸려있는 답글까지 모두 지워야하기 때문에 masterid 를 기준으로 삭제하는 쿼리
			}
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,bbsno);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDAO.deleteArticle()예외발생-콘솔확인");
				
		}finally {
			closeConnection(con);
		}
	}
	//글의 총 개수를 세어주는 메서드.
	public int selectTotalBbscount() {
		Connection con = null;
		String sql = "select count(bbsno) from board";
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int bbsCount = rs.getInt(1);
			return bbsCount;
		}
		catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("MemberDAO.selectTotalBbscount()예외발생-콘솔확인");
				
		}finally {
			closeConnection(con);
		}
	}
	//글을 수정하는 메서드 
	public void updateArticle(BoardVO board) {//제목,내용,시간을 수정하므로 매개변수로 BoardVO를 담아줌
		Connection con = null;
		try {
			con = getConnection();
			String sql = "update board set "
					+ "subject=?, content=?, writedate=SYSDATE "
					+ "where bbsno=?";
			// subject = 제목, content = 내용, writedate= 시간 세가지 수정할 항목을
			//게시글번호에 해당하는 조건으로 board테이블에 수정 해주라는 sql쿼리문
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBbsno());
			pstmt.executeUpdate();
	}catch(Exception e) {
			
				e.printStackTrace();
				throw new RuntimeException("MemberDAO.updateArticle()예외발생-콘솔확인");
				
		}finally {
			closeConnection(con);
		}
	}
	//마이페이지에서 내가 쓴 글이 총 몇개인지 확인하기 위한 메서드.
	public int selectCount(String userid) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "select count(bbsno) from board where userid=?";
			//내가 쓴 글만 확인할수있도록 해당 아이디의 게시글 갯수를 게시판 테이블로 부터 불러와 출력해주는 쿼리문.
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			return count;			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDAO.selectCount()예외발생-콘솔확인");
			
		}finally {
			closeConnection(con);
		}
	}
	public Collection<BoardVO> memberList(String userid, int page) {
		Connection con = null;
		String sql = "select rnum, bbsno, name, subject, readcount, writedate from "
				+ "(select rownum rnum, bbsno, name, subject, readcount, writedate from "
				+ "(select bbsno, name, subject, readcount, writedate from board b "
				+ "join member m on b.userid = m.userid where b.userid=? order by bbsno desc))"
				+ "where rnum between ? and ?";
		ArrayList<BoardVO> list = new ArrayList<>();
		int start = (page-1) * 20 + 1;
		int end = start + 19;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBbsno(rs.getInt("bbsno"));
				board.setName(rs.getString("name"));
				board.setWriteDate(rs.getDate("writedate"));
				board.setSubject(rs.getString("subject"));
				board.setReadCount(rs.getInt("readcount"));
				list.add(board);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("MemberDAO.memberList()예외발생-콘솔확인");
			
		}finally {
			closeConnection(con);
		}
		return list;
		
	}
	
	/*
	
	public void () {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeException("MemberDAO. ()예외발생-콘솔확인");
			
		}finally {
			closeConnection(con);
		}
	}
	
	*/

}
