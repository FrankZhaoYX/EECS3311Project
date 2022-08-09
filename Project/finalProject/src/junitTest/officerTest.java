package junitTest;

import java.util.ArrayList;

import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import parkingDB.bookingInfo;
import parkingDB.maintainOfficer;
import parkingDB.maintainPlot;
import parkingDB.officer;
import parkingDB.parkingPlot;

class officerTest {
	public maintainOfficer testDB = new maintainOfficer();
	
	@BeforeEach
	public void setUP() throws Exception {
		officer tmp = new officer();
		testDB = new maintainOfficer(tmp); 
	}
	
	@Test
	public void test_logInandOut() throws Exception {
		int status1 = testDB.get_currentOfficer().getStatus();
		boolean login = testDB.login("test01@yorku.ca", "123");
		int status2 = testDB.get_currentOfficer().getStatus();
		boolean logout = testDB.logout();
		
		Assert.assertEquals(0,status1);
		Assert.assertEquals(true, login);
		Assert.assertEquals(1, status2);
		Assert.assertEquals(true, logout);
	}
	
	@Test
	public void test_addAndRemoveplot() throws Exception {
		boolean login = testDB.login("test01@yorku.ca", "123");
		int status2 = testDB.get_currentOfficer().getStatus();
		boolean add1 = testDB.add_plot();
		boolean add2 = testDB.add_plot(4);
		boolean add3 = testDB.add_plot(5);
		maintainPlot tmp1 = new maintainPlot();
		ArrayList<parkingPlot> tmp2 = tmp1.get_parkingPlot();
		ArrayList<parkingPlot> tmp3 = tmp1.Available_plot();
		int plotNum1 = tmp2.size();
		int plotNum2 = tmp3.size();
		boolean remove1 = testDB.remove_plot(4);
		boolean remove2 = testDB.remove_plot(5); 
		boolean logout = testDB.logout();
		
		Assert.assertEquals(true, login);
		Assert.assertEquals(1, status2);
		Assert.assertEquals(true, add1);
		Assert.assertEquals(false, add2);
		Assert.assertEquals(true, add3);
		Assert.assertEquals(5, plotNum1);
		Assert.assertEquals(5, plotNum2);
		Assert.assertEquals(true, remove1);
		Assert.assertEquals(true, remove2);
		Assert.assertEquals(true, logout);
	}

	@Test
	public void test_Rgister_users() throws Exception {
		
		boolean login = testDB.login("test01@yorku.ca", "123");
		int status2 = testDB.get_currentOfficer().getStatus();
		boolean register1 = testDB.register("test01", "test01@yorku.ca", "121");
		int size = testDB.get_customerDB().size();
		boolean register2 = testDB.register("test00", "testAuto"+size+"@yorku.ca", "121");
		boolean logout = testDB.logout();
		
		Assert.assertEquals(true, login);
		Assert.assertEquals(1, status2);
		Assert.assertEquals(false, register1);
		Assert.assertEquals(true, register2);
		Assert.assertEquals(true, logout);
	}
	
	@Test 
	public void test_Register_remove_officer() throws Exception{
		boolean login1 = testDB.login("test01@yorku.ca", "123");
		int status1 = testDB.get_currentOfficer().getStatus();
		boolean register1 = testDB.register_officer("testOfficer02", "test03@yorku.ca", "123");
		boolean logout1 = testDB.logout();
		
		boolean login2 = testDB.login("admin01@yorku.ca", "123");
		int status2 = testDB.get_currentOfficer().getStatus();
		boolean register2 = testDB.register_officer("testOfficer02", "test03@yorku.ca", "123");
		ArrayList<officer> tmp = testDB.retrieve_officer();
		int officerNum = tmp.size();
		boolean remove = testDB.remove_officer("test03@yorku.ca");
		boolean logout2 = testDB.logout();
		
		Assert.assertEquals(true, login1);
		Assert.assertEquals(1, status1);
		Assert.assertEquals(false, register1);
		Assert.assertEquals(true, logout1);
		Assert.assertEquals(true, login2);
		Assert.assertEquals(1, status2);
		Assert.assertEquals(true, register2);
		Assert.assertEquals(3, officerNum);
		Assert.assertEquals(true, remove);
		Assert.assertEquals(true, logout2);
	}
	
	@Test
	public void test_BOOKING_payment() throws Exception{
		boolean login = testDB.login("test01@yorku.ca", "123");
		int status1 = testDB.get_currentOfficer().getStatus();
		String email = "test01@yorku.ca";
		boolean email_exist = testDB.email_exist("test01@gmail.com");
		boolean booking1 = testDB.make_booking(1, email, 30);
		boolean booking2 = testDB.make_booking(2, email, 30);
		boolean booking3 = testDB.make_booking(1, email, 30);
		int totalfee = testDB.get_totalFee(email);
		boolean payment1 = testDB.make_payment(email, "12345");
		boolean payment2 = testDB.make_payment(email, "1234567887654321"); 
		ArrayList<bookingInfo> tmp = testDB.retrieveAvaliableOrder(email);
		boolean cancel1 = testDB.cancel_order(email, tmp.get(0).getID());
		boolean cancel2 = testDB.cancel_order(email, tmp.get(1).getID());
		boolean cancel3 = testDB.cancel_order(email, tmp.get(1).getID());
		boolean logout = testDB.logout();
		
		Assert.assertEquals(true, login);
		Assert.assertEquals(1, status1);
		Assert.assertEquals(false, email_exist);
		Assert.assertEquals(true, booking1);
		Assert.assertEquals(true, booking2);
		Assert.assertEquals(false, booking3);
		Assert.assertEquals(15, totalfee);
		Assert.assertEquals(false, payment1);
		Assert.assertEquals(true, payment2);
		Assert.assertEquals(true, cancel1);
		Assert.assertEquals(true, cancel2);
		Assert.assertEquals(false, cancel3);
		Assert.assertEquals(true, logout);

	}

	
}
