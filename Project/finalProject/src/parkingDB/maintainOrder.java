package parkingDB;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class maintainOrder {
	private final String orderPath = "D:\\EECS3311\\Winter\\Project\\bookinginfo.csv";
	private maintainPlot plot_manage; 
	public SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private ArrayList<bookingInfo> orderDB = new ArrayList<bookingInfo>();
	private final long ONE_MINUTE_IN_MILLIS=60000;//millisecs;
	private int parkingRate = 15;  // 15 dollar per hour
	
	public maintainOrder() throws Exception {
		super();
		this.plot_manage = new maintainPlot();
	}
	
	
	public ArrayList<bookingInfo> loadBookinginfo() throws Exception{
		CsvReader reader = new CsvReader(this.orderPath); 
		reader.readHeaders();
		ArrayList<bookingInfo> orders = new ArrayList<bookingInfo>();
		while(reader.readRecord()){ 
			bookingInfo tmp = new bookingInfo();
			tmp.setId(Integer.valueOf(reader.get("id")));
			tmp.setEmail(reader.get("email"));
			tmp.setPid(Integer.valueOf(reader.get("pid")));
			tmp.setStart(dt.parse(reader.get("start")));
			tmp.setEnd(dt.parse(reader.get("end")));
			tmp.setDurantion(Integer.valueOf(reader.get("duration")));
			tmp.setStatus(Integer.valueOf(reader.get("status")));
			orders.add(tmp);
		}
		return orders;
	}
	
	public void update_order() throws Exception {
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(this.orderPath, false), ',');
			csvOutput.write("id");
			csvOutput.write("email");
	    	csvOutput.write("pid");
			csvOutput.write("start");
			csvOutput.write("end");
			csvOutput.write("duration");
			csvOutput.write("status");
			csvOutput.endRecord();
			
			for(bookingInfo b: this.orderDB) {
				csvOutput.write(String.valueOf(b.getID()));
				csvOutput.write(b.getEmail());
				csvOutput.write(String.valueOf(b.getPid()));
				csvOutput.write(dt.format(b.getStart()));
				csvOutput.write(dt.format(b.getEnd()));
				csvOutput.write(String.valueOf(b.getDuation()));
				csvOutput.write(String.valueOf(b.getStatus()));
				csvOutput.endRecord();
			}
			csvOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean booking_plot(int id, String email, int duration) throws Exception {
		refresh_bookingSystem();
		this.orderDB = loadBookinginfo();
		if(this.get_validateOrder(email).size()>=3) {
			return false;
		}
		
		if(plot_manage.check_plot_Available(id)) {
//			Calendar date = Calendar.getInstance();
//	    	long t= date.getTimeInMillis();
			long t = System.currentTimeMillis();
	    	Date beforeAddingTwoMins=new Date(t);
	    	Date afterAddingTwoMins=new Date(t + (2 * ONE_MINUTE_IN_MILLIS));
	    	bookingInfo newBooking = new bookingInfo();
	    	newBooking.setId(this.orderDB.size()+1);
	    	newBooking.setEmail(email);
	    	newBooking.setPid(id);
	    	newBooking.setStart(beforeAddingTwoMins);
	    	newBooking.setEnd(afterAddingTwoMins);
	    	newBooking.setDurantion(duration);
	    	newBooking.setStatus(1);
	    	this.orderDB.add(newBooking);
	    	plot_manage.Occupid_plot(id);
	    	update_order();
	    	return true;
		} 
		return false;
	}
	
	public void refresh_bookingSystem() throws Exception {
		this.orderDB = loadBookinginfo();
		long tmp = System.currentTimeMillis();
		Date currentTime = new Date(tmp);
		for(bookingInfo b: this.orderDB) {
//			System.out.println("current time is " + currentTime);
//			System.out.println("the end is " + b.getEnd());
			if(b.getStatus()!=0) {
				if(currentTime.compareTo(b.getEnd())>0) {
					b.setStatus(0);
					plot_manage.Free_plot(b.getPid());
//					System.out.println("FInd");
				}
			}
			
		}
		update_order();
	}
	
	public int get_totalFee(String email) throws Exception {
		refresh_bookingSystem();
		int total_time =0;
		for(bookingInfo b: this.orderDB) {
			if(b.getEmail().equals(email) && b.getStatus()==1) {
				total_time = total_time + b.getDuation();
			}
		}
		return (total_time/60)*15;
	}
	
	public boolean make_payment(String cardNum, String email) throws Exception {
		refresh_bookingSystem();
		if(isNumeric(cardNum) && cardNum.length()==16) {
			for(bookingInfo b: this.orderDB) {
				if(b.getEmail().equals(email) && b.getStatus()==1) {
					int duration = b.getDuation();
					long t = System.currentTimeMillis();
			    	Date beforeAdding=new Date(t);
			    	Date afterAdding=new Date(t + (duration * ONE_MINUTE_IN_MILLIS));
					b.setStatus(2);
					b.setStart(beforeAdding);
					b.setEnd(afterAdding);
				}
			}
			update_order();
			return true;
		}
		return false;
	}
	
	//validate cardNum
	public static boolean isNumeric(String string) {
	    long intValue;
			
	    System.out.println(String.format("Parsing string: \"%s\"", string));
			
	    if(string == null || string.equals("")) {
	        System.out.println("String cannot be parsed, it is null or empty.");
	        return false;
	    }
	    
	    try {
	        intValue = Long.parseLong(string);
	        return true;
	    } catch (NumberFormatException e) {
	        //System.out.println("Input String cannot be parsed to Integer.");
	    }
	    return false;
	}
	
	
	//cancel order
	public boolean cancel_order(String email, int id) throws Exception {
		refresh_bookingSystem();
		long tmp = System.currentTimeMillis();
		Date currentTime = new Date(tmp);
		for(bookingInfo b: this.orderDB) {
//			System.out.println("enter");
			if(b.getID()==id && b.getEmail().equals(email) && b.getStatus()>0) {		// confirm the order
				if(currentTime.compareTo(b.getEnd())<0) {
					b.setStatus(0);
					plot_manage.Free_plot(b.getPid());
					update_order();
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<bookingInfo> get_validateOrder(String email) throws Exception{
		refresh_bookingSystem();
		ArrayList<bookingInfo> validateOrder = new ArrayList<bookingInfo>();
		for(bookingInfo b: this.orderDB) {
			if(b.getEmail().equals(email) && b.getStatus()!=0) {
				validateOrder.add(b);
			}
		}
		return validateOrder;
	}
	
	
}
