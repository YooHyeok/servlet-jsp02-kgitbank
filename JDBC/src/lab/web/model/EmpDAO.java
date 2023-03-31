package lab.web.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class EmpDAO {
	//커넥션을 하나하나뺴오는 코드를 작성

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

	public EmpVO selectEmployee(int empId) {//사원번호 불러오는 메서드.....
		Connection con = null;//try안에 선언하면 밖에서 호출을 못함 78번에서 못닫음.
		EmpVO emp = new EmpVO();
		try {
			con = getConnection(); 
			//			String sql = "select * from employees where employee_id=+empId";
			String sql = "select * from employees where employee_id=?";//?를 채운다 preparedStatement로
			PreparedStatement stmt = con.prepareStatement(sql); //쿼리문을 세팅하고 실행하기위해 sql을받아 con에 저장.
			// ^쿼리를 세팅하고 실행해줌
			stmt.setInt(1, empId); //sql은 인덱스가 0(JAVA)이아닌 1부터 시작
			ResultSet rs = stmt.executeQuery();
			//executeQuery : 테이블형식을 리턴. 쿼리. executeUpdate : 
			if(rs.next()) {
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble(9));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt(11));
			} 
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return emp;

	}
	public ArrayList<EmpVO> selectEmployeeList(){ //사원 리스트를 불러오는 메서드...
		Connection con = null;
		ArrayList<EmpVO> list = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from employees"; //preparedStatement에 들어갈 sql
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {//데이터 없을떄까지 계속실행
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));


				emp.setDepartmentId(rs.getInt(11));
				list.add(emp);
			}
		}catch(SQLException e) {
			e.printStackTrace();

		}finally {
			closeConnection(con);
		}
		return list;
	}
	public ArrayList<JobVO> selectJobs(){ //직무 리스트를 불러오는 메서드...
		Connection con = null;
		ArrayList<JobVO> jobList = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select job_id, job_title from jobs";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				JobVO job = new JobVO();
				job.setJobId(rs.getString(1));
				job.setJobTitle(rs.getString(2));
				jobList.add(job);
			}

		}catch(SQLException e) {
			e.printStackTrace();

		}finally {
			closeConnection(con);
		}
		return jobList;
	}
	public ArrayList<DeptVO> selectDepartments() {
		Connection con =null;
		ArrayList<DeptVO> deptList = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select department_id, department_name from departments";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDepartmentId(rs.getInt(1));
				dept.setDepartmentName(rs.getString(2));
				deptList.add(dept);
			}
		}catch(SQLException e) {
			e.printStackTrace();

		}finally {
			closeConnection(con);
		}
		return deptList;
	}
	public ArrayList<EmpVO> selectManagers(){
		Connection con = null;
		ArrayList<EmpVO> manList = new ArrayList<>();
		try {
			con = getConnection(); 
			String sql = "select m.manager_id, first_name||' '||last_name as name"	//java에서 쿼리가 길어져서 한줄씩 띄우는(엔터)경우 마지막, 
					+ " from employees e "											//혹은 처음에 공백(띄어쓰기)
					+ " join (select distinct manager_id from employees) m"
					+ " on e.employee_id=m.manager_id";
			PreparedStatement stmt = con.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setFirstName(rs.getString("name")); //쿼리문에서 다합친후 as name으로 수정함
				emp.setManagerId(rs.getInt("manager_id"));
				manList.add(emp);
			} 
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return manList;

	}
	//입력메서드 만드는거 고민해보기 숙제

	//집어넣으면 결과문이 숫자 , 숫자를 리턴하던가 본인이 알아서 처리해야함
	//sql developer 에서 결과문이 숫자임 (~행이 추가되었습니다)
	public void insertEmployee(EmpVO emp) { //insert update delete 형식
		Connection con = null;
		try {
			con = getConnection();
			String sql = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, emp.getEmployeeId());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setString(4, emp.getEmail());
			stmt.setString(5, emp.getPhoneNumber());
			stmt.setDate(6, emp.getHireDate());
			stmt.setString(7, emp.getJobId());
			stmt.setDouble(8, emp.getSalary());
			stmt.setDouble(9, emp.getCommissionPct());
			stmt.setInt(10, emp.getManagerId());
			stmt.setInt(11, emp.getDepartmentId());
			int result = stmt.executeUpdate(); //executeUpdate(int) insert update delete용도임
			if(!(result > 0)) { //결과가 0보다 크지 않다면?
				throw new RuntimeException("입력이 되지 않았습니다.");
			}
		}catch(SQLException e) {
			if(e.getMessage().contains("unique")) {//unique는 중중복이나온거임
				throw new RuntimeException("데이터가 중복됩니다.");
			}
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
	}

	public EmpDetailVO selectEmployeeDetails(int empId) {
		Connection con = null;
		EmpDetailVO emp = new EmpDetailVO();
		try {
			con=getConnection();
			//조인 세번함 (매니저테이블 만듬)
			String sql = "select employee_id, first_name, last_name, email,"
					+ "phone_number, hire_Date, emp.job_id, job_title, salary,"
					+ "commission_pct, emp.manager_id, manager_name, emp.department_id,"
					+ "department_name from employees emp "
					+ "left join (select m.manager_id, first_name||' '||last_name as manager_name "
					+ "from employees e "
					+ "join (select distinct manager_id from employees) m "
					+ "on e.employee_id=m.manager_id) man "
					+ "on emp.manager_id=man.manager_id "
					+ "join departments dept "
					+ "on emp.department_id=dept.department_id "
					+ "join jobs jobs "
					+ "on emp.job_id=jobs.job_id "
					+ "where emp.employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setJobTitle(rs.getString("job_title"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setManagerName(rs.getString("manager_name"));
				emp.setDepartmentId(rs.getInt("department_id"));
				emp.setDepartmentName(rs.getString("department_name"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return emp;
	}


	public void updateEmployee(EmpVO emp) {
		Connection con = null;
		try {
			con = getConnection();
			//update될 table들 모두 입력/where절에 누구를 바꾸겠다/ 
			String sql = "update employees set first_name=?,"
					+ "last_name=?, email=?, phone_number=?,"
					+ "hire_date=?, job_id=?, salary=?,"
					+ "commission_pct=?, manager_id=?,"
					+ "department_id=? "
					+ "where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, emp.getFirstName());
			stmt.setString(2, emp.getLastName());
			stmt.setString(3, emp.getEmail());
			stmt.setString(4, emp.getPhoneNumber());
			stmt.setDate(5, emp.getHireDate());
			stmt.setString(6, emp.getJobId());
			stmt.setDouble(7, emp.getSalary());
			stmt.setDouble(8,emp.getCommissionPct());
			stmt.setInt(9, emp.getManagerId());
			stmt.setInt(10, emp.getDepartmentId());
			stmt.setInt(11, emp.getEmployeeId());
			//			if(stmt.executeUpdate() > 0) { //정상적으로 실행 됬다. int형 result는 정수이므로~
			if(!(stmt.executeUpdate() > 0)) { //정상적으로 실행 됬다. int형 result는 정수이므로~
				throw new RuntimeException("수정이 되지 않았습니다.");
			}

		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally {
			closeConnection(con);
		}

	} public void deleteEmployee(int empId) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "delete from employees where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empId);
			if (!(stmt.executeUpdate()>0)){
				throw new RuntimeException("삭제되지 않았습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
	}
}



//질문하기 executeQuery가 stmt에 저장되고 int형식의 result변수에 저장됨? 역할도 질문하기.
/*
PreparedStatement stmt = con.prepareStatement(sql); 
ResultSet rs = stmt.executeQuery();
*/






