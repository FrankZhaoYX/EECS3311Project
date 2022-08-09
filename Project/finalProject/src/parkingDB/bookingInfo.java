package parkingDB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class bookingInfo {
	private int id;
	private String email;
	private int pid;
	private Date start;
	private Date end;
	private int duration;
	private int status;  // 0 is expired, 1 is holding, 2 is paid
	
	public int getID() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public int getPid() {
		return this.pid;
	}
	
	public Date getStart() {
		return this.start;
	}
	
	public Date getEnd() {
		return this.end;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public int getDuation() {
		return this.duration;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public void setDurantion(int duration) {
		this.duration =duration;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	
//    public static void main(String [] args) throws Exception{
//    	final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
//    	
//    	Calendar date = Calendar.getInstance();
//    	long t= date.getTimeInMillis();
//    	Date afterAddingTenMins=new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
//    	SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//    	String tmp1 = dt.format(afterAddingTenMins);
//    	Date tmp2 = dt.parse(tmp1);
//    	Boolean tmp3 = dt.format(afterAddingTenMins).equals(dt.format(tmp2));
//		System.out.println("Time is " + tmp2);
//		System.out.println("Time is " + dt.format(afterAddingTenMins));
//		System.out.println("Tmp1 and tmp2 equal is " + tmp3);
//	}
	
}
