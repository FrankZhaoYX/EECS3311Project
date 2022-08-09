package parkingDB;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class maintainOfficer implements maintain{
	private ArrayList<customer> customerDB = new ArrayList<customer>();
	private ArrayList<officer> officerDB = new ArrayList<officer>();
	private final String officerPath = "D:\\EECS3311\\Winter\\Project\\officer.csv";
	private final String customerPath = "D:\\EECS3311\\Winter\\Project\\customer.csv";
	private officer currentOfficer = new officer();
	
	public maintainOfficer() {
		super();
	}
	
	public maintainOfficer(officer user) throws Exception{
		this.officerDB = loadOfficer();
		this.currentOfficer = user;
	}

	
	private ArrayList<officer> loadOfficer() throws Exception{
		CsvReader reader = new CsvReader(this.officerPath); 
		reader.readHeaders();
		ArrayList<officer> users = new ArrayList<officer>();
		while(reader.readRecord()){ 
			officer tmpU = new officer();
			tmpU.setName(reader.get("name"));
			tmpU.setEmail(reader.get("email"));
			tmpU.setPassword(reader.get("password"));
			if(Integer.valueOf(reader.get("authority"))==2) {
				tmpU.setAuthority();
			}
			if(Integer.valueOf(reader.get("authority"))==3) {
				tmpU.setAutority_admin();
			}
			tmpU.setStatus(Integer.valueOf(reader.get("status")));
			users.add(tmpU);
		}
		return users;
	}
	
	private ArrayList<customer> loadCustomer() throws Exception {
		CsvReader reader = new CsvReader(this.customerPath); 
		reader.readHeaders();
		ArrayList<customer> users = new ArrayList<customer>();
		while(reader.readRecord()){ 
			customer tmpU = new customer();
			tmpU.setName(reader.get("name"));
			tmpU.setEmail(reader.get("email"));
			tmpU.setPassword(reader.get("password"));
			tmpU.setAuthority();
			tmpU.setStatus(Integer.valueOf(reader.get("status")));
			users.add(tmpU);
		}
		return users;
	}
	
	public void update(ArrayList<officer> user) {
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(this.officerPath, false), ',');
			csvOutput.write("name");
			csvOutput.write("email");
	    	csvOutput.write("password");
			csvOutput.write("authority");
			csvOutput.write("status");
			csvOutput.endRecord();
			
			for(user u: user) {
				csvOutput.write(u.getName());
				csvOutput.write(u.getEmail());
				csvOutput.write(u.getPassword());
				csvOutput.write(String.valueOf(u.getAuthority()));
				csvOutput.write(String.valueOf(u.getStatus()));
				csvOutput.endRecord();
			}
			csvOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update_customer(ArrayList<customer> user) {
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(this.customerPath, false), ',');
			csvOutput.write("name");
			csvOutput.write("email");
	    	csvOutput.write("password");
			csvOutput.write("authority");
			csvOutput.write("status");
			csvOutput.endRecord();
			
			for(user u: user) {
				csvOutput.write(u.getName());
				csvOutput.write(u.getEmail());
				csvOutput.write(u.getPassword());
				csvOutput.write(String.valueOf(u.getAuthority()));
				csvOutput.write(String.valueOf(u.getStatus()));
				csvOutput.endRecord();
			}
			csvOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public officer get_currentOfficer() {
		return this.currentOfficer;
	}
	
	public ArrayList<customer> get_customerDB(){
		return this.customerDB;
	}
	
	public ArrayList<officer> get_officerDB(){
		return this.officerDB;
	}
	
	
	public boolean add_plot(int id) throws Exception {
		if(this.currentOfficer.getAuthority()==2 && this.currentOfficer.getStatus()==1) {
			maintainPlot tmp = new maintainPlot();
			if (tmp.add_plot(id)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean remove_plot(int id) throws Exception {
		if(this.currentOfficer.getAuthority()==2 && this.currentOfficer.getStatus()==1) {
			maintainPlot tmp = new maintainPlot();
			if (tmp.remove_plot(id)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean add_plot() throws Exception {
		if(this.currentOfficer.getAuthority()==2 && this.currentOfficer.getStatus()==1) {
			maintainPlot tmp = new maintainPlot();
			if (tmp.add_plot()) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	
	@Override
	public boolean login(String email, String password) throws Exception {
		if (this.currentOfficer.getAuthority() == 2 || this.currentOfficer.getAuthority() == 3) {	// officer login
			this.officerDB = loadOfficer();
			for(officer o: this.officerDB) {
				if(o.getEmail().equals(email)) {
					if(o.getPassword().equals(password) && o.getStatus()==0) {
						o.setStatus(1);
						this.currentOfficer = o;
						update(officerDB);
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean logout() {
		if(this.currentOfficer.getAuthority()==2 || this.currentOfficer.getAuthority()==3) {
			for(officer o: this.officerDB) {
				if(o.getEmail().equals(this.currentOfficer.getEmail()) && this.currentOfficer.getStatus()==1) {
					o.setStatus(0);
					this.currentOfficer = this.currentOfficer.clone();
					update(this.officerDB);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean register(String name, String email, String password) throws Exception {
		if(this.currentOfficer.getAuthority()==2 && this.currentOfficer.getStatus()==1) {	// officer register for users;
			this.customerDB = loadCustomer();
			for(customer c: this.customerDB) {
				if(email.equals(c.getEmail())) {
					return false; // email exists
				}
			}
			customer newCustomer = new customer(name, email, password);
			this.customerDB.add(newCustomer);
			update_customer(this.customerDB);
			return true;
		}
		
		return false;
	}
	
	public boolean register_officer(String name, String email, String password) throws Exception {
		if(this.currentOfficer.getAuthority()==3 && this.currentOfficer.getStatus()==1) {	//  admin register officer
			this.officerDB = loadOfficer();
			this.customerDB = loadCustomer();
			for(customer c: this.customerDB) {
				if(email.equals(c.getEmail())) {
					return false; // email exists
				}
			}
			
			for(officer o: this.officerDB) {
				if(email.equals(o.getEmail())) {
					return false; // email exists
				}
			}
			
			customer newCustomer = new customer(name, email, password);
			this.customerDB.add(newCustomer);
			officer newOfficer = new officer(name, email, password);
			this.officerDB.add(newOfficer);
			update_customer(this.customerDB);
			update(this.officerDB);
			return true;
		}
		return false;
	}
	
	public boolean remove_officer(String email) throws Exception {
		boolean tmp1 = false;
		boolean tmp2 = false;
		if(this.currentOfficer.getAuthority()==3 && this.currentOfficer.getStatus()==1) {
			this.officerDB = loadOfficer();
			this.customerDB = loadCustomer();
			officer tmpo = null;
			customer tmpc = null;
			for(officer u: this.officerDB) {
				if(u.getEmail().equals(email) && u.getStatus()==0) {
//					if(this.officerDB.remove(u)) {
//						tmp1 = true;
//					}
					tmpo =u;
					tmp1 = true;
				}
			}
			if(tmp1) {
				tmp1 = tmp1 && this.officerDB.remove(tmpo);
			}
			
			for(customer c: this.customerDB) {
				if(c.getEmail().equals(email) && c.getStatus()==0) {
//					if(this.customerDB.remove(c)) {
//						
//						tmp2 = true;
//					}
					tmpc = c;
					tmp2 = true;
				}
			}
			if(tmp2) {
				tmp2 = tmp2 && this.customerDB.remove(tmpc);
			}
		}
		update(this.officerDB);
		update_customer(this.customerDB);
		return tmp1&&tmp2;
	}
	
	public boolean email_exist(String email) throws Exception {
		this.customerDB=loadCustomer();
		for(customer c: this.customerDB) {
			if(c.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<bookingInfo> retrieveAvaliableOrder(String email) throws Exception{
		if(email_exist(email)) {
			maintainOrder tmp = new maintainOrder();
			return tmp.get_validateOrder(email);
		}
		return null;
	}
	
	
	public int get_totalFee(String email) throws Exception {
		if(email_exist(email)) {
			maintainOrder tmp = new maintainOrder();
			return tmp.get_totalFee(email);
		}
		return 0;
	}
	
	public boolean make_payment(String email, String cardNum) throws Exception{ 
		if(email_exist(email)) {
			maintainOrder tmp = new maintainOrder();
			if(tmp.make_payment(cardNum, email)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean cancel_order(String email, int bid) throws Exception{
		if(email_exist(email)) {
			maintainOrder tmp = new maintainOrder();
			return tmp.cancel_order(email, bid);
		}
		return false;
	}
	
	public boolean make_booking(int pid,String email, int duration) throws Exception {
		maintainOrder tmp = new maintainOrder();
		return tmp.booking_plot(pid, email, duration);
	}
	
	public ArrayList<officer> retrieve_officer() throws Exception{
		ArrayList<officer> tmp = new ArrayList<officer>();
		if(this.currentOfficer.getAuthority()==3) {
			this.officerDB=loadOfficer();
			for(officer o: this.officerDB) {
				if(o.getAuthority()==2) {
					tmp.add(o);
				}
			}
		}
		return tmp;
	}
	
//	public static void main(String[] args) {
//        maintainOfficer testDB = new maintainOfficer();
//        try {
//			boolean test1 = testDB.login("admin01@yorku.ca", "123");
//			System.out.println("login is " + test1);
////			System.out.println("autho is " + testDB.currentOfficer.getAuthority());
//			boolean test2 = testDB.register_officer("officer123", "123@gmail.com", "111");
//			System.out.println("adding is " + test2);
//			boolean test3 = testDB.remove_officer( "123@gmail.com");
//			System.out.println("remove is " + test3);
//			boolean test4 = testDB.logout();
//			System.out.println("logout is " + test4);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//    }

}
