package parkingDB;

import java.util.ArrayList;

public interface maintain {

	public boolean login (String email, String password) throws Exception;
	
	public boolean logout();
	
	public boolean register(String name, String email, String password) throws Exception;
	
	
}
