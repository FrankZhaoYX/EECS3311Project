package parkingDB;

public class officer implements user {
	private String name;
	private String email;
	private String password;
	private int authority;	//2 is officer
	private int status;		//0 is logout, 1 is login 
	
	public officer() {
		super();
		setAuthority();
	}
	
	public officer(String name,String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.authority = 2;
		this.status = 0;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", autority=" + authority + ", status= " + status + "]";
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public int getAuthority() {
		return this.authority;
	}

	
	@Override
	public int getStatus() {
		return this.status;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPassword(String password) {
		this.password=password;
	}

	@Override
	public void setEmail(String email) {
		this.email=email;
	}

	@Override
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public void setAuthority() {
		this.authority = 2;
	}

	public void setAutority_admin() {
		this.authority = 3;
	}
	
	@Override
	public officer clone() {
		officer tmp = new officer();
		return tmp;
	}
}
