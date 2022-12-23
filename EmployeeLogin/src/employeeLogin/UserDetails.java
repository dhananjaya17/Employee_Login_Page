package employeeLogin;

public class UserDetails {
	
	int empid;
	String userName;
	String password;
	
	public UserDetails(int empid,String username ,String password ) {
		super();
		this.empid = empid;
		this.userName = username;
		this.password = password;
		
	}

	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [empid=" + empid + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
