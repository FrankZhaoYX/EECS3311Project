package parkingDB;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class maintainCustomer implements maintain {

	
	private ArrayList<customer> customerDB = new ArrayList<customer>();
	private final String customerPath = "D:\\EECS3311\\Winter\\Project\\customer.csv";
	private user currentUser;
	
	public maintainCustomer() throws Exception {
		super();
		this.currentUser = new customer();
		this.customerDB = loadCustomer();
	}
	
	
	public maintainCustomer(customer user) throws Exception {
		super();
		this.currentUser = user;
		this.customerDB = loadCustomer();
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
	
	
	public void update(ArrayList<customer> user) {
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
	
	public user get_currentUser() {
		return this.currentUser;
	}

	@Override
	public boolean login(String email, String password) throws Exception {
		if(this.currentUser.getAuthority()==1) {	// customer login
			this.customerDB= loadCustomer();
			for(customer c: this.customerDB) {
				if(c.getEmail().equals(email)) {
					if(c.getPassword().equals(password) && c.getStatus()==0) {
						c.setStatus(1);
						this.currentUser = c;
						update(customerDB);
						return true;
					}
				}
			}
		}
		return false;
	}

	

	@Override
	public boolean logout() {
		if(this.currentUser.getAuthority()==1) {
			for(customer c: this.customerDB) {
				if(c.getEmail().equals(this.currentUser.getEmail()) && this.currentUser.getStatus()==1) {
					c.setStatus(0);
					this.currentUser = this.currentUser.clone();
					update(this.customerDB);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean register(String name, String email, String password) throws Exception {
		if(this.currentUser.getAuthority()==1 && this.currentUser.getStatus()==0) { // user register
			this.customerDB = loadCustomer();
			for(customer c: this.customerDB) {
				if(email.equals(c.getEmail())) {
					return false; // email exists
				}
			}
			customer newCustomer = new customer(name, email, password);
			this.customerDB.add(newCustomer);
			update(this.customerDB);
			return true;
		}
		return false;
	}
	
	public boolean bookingPlot(int pid, int duration) throws Exception {
		maintainOrder tmp = new maintainOrder();
		if(this.currentUser.getStatus()==1) {
			if(tmp.booking_plot(pid, this.currentUser.getEmail(), duration)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<bookingInfo> retriveAvaliableOrder() throws Exception{
		maintainOrder tmp = new maintainOrder();
		return tmp.get_validateOrder(this.currentUser.getEmail());
	}
	
	public boolean cancelOrder(int bid) throws Exception {
		maintainOrder tmp = new maintainOrder();
		return tmp.cancel_order(this.currentUser.getEmail(), bid);
	}
	
	public int get_Totalfee() throws Exception{
		maintainOrder tmp = new maintainOrder();
		return tmp.get_totalFee(this.currentUser.getEmail());
	}
	
	public boolean make_payment(String cardNum) throws Exception{
		maintainOrder tmp = new maintainOrder();
		return tmp.make_payment(cardNum, this.currentUser.getEmail());
	}
	
	public int size_of_CustomerDB() throws Exception {
		this.customerDB = loadCustomer();
		return this.customerDB.size();
	}
	
}
