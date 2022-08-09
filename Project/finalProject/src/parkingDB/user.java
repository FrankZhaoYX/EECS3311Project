package parkingDB;

public interface user {
	
	
	/**
	 * Get the user's name 
	 * @return the name of current user
	 */
	public String getName();
	
	/**
	 * Get the user's Authority
	 * @return the Authority of current user
	 */
	public int getAuthority();
	
	/**
	 * Get the user's email
	 * @return the email of current user
	 */
	public String getEmail();
	
	
	
	
	/**
	 * Get the user's status
	 * @return the name of current user
	 */
	public int getStatus();
	
	/**
	 * 
	 * @return
	 */
	public String getPassword();
	
	/**
	 * change the name of current user
	 * @param name
	 * @return
	 */
	public void setName(String name);
	
	/**
	 * change the password
	 * @param password
	 * @return
	 */
	public void setPassword(String password);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public void setEmail(String email);
	
	/**
	 * 
	 * @param status
	 * @return
	 */
	public void setStatus(int status);
	
	/**
	 * 
	 */
	public void setAuthority();
	
	
	/*
	 * 
	 */
	public user clone();

}
