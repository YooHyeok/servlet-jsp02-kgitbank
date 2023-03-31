package lab.web.model;

public class EmpDetailVO extends EmpVO{ //EmpVO가갖고있는것들을 상속(extends)으로 가져옴 
	private String jobTitle;
	private String departmentName;
	private String managerName;
	
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
}
