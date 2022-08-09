package junitTest;


import java.util.ArrayList;

import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import parkingDB.bookingInfo;
import parkingDB.customer;
import parkingDB.maintain;
import parkingDB.maintainCustomer;

class customerTest {
	public maintainCustomer testDB;
	@BeforeEach
	public void setUP() throws Exception {
		customer tmp = new customer();
		testDB = new maintainCustomer(tmp);
	}
	
	
	@Test
	public void test_logInandOut_1() throws Exception {
		boolean login = testDB.login("test01@yorku.ca", "123");
		int status = testDB.get_currentUser().getStatus();
		boolean logout = testDB.logout();
		Assert.assertEquals(true, login);
		Assert.assertEquals(1, status);
		Assert.assertEquals(true, logout);
	}
	
	@Test
	public void test_logInandOut_2() throws Exception {
		boolean login = testDB.login("test01@yorku.ca", "121");
		boolean login2 = testDB.login("test01@yorku.ca", "123");
		boolean login3 = testDB.login("test01@yorku.ca", "123");
		int status = testDB.get_currentUser().getStatus();
		boolean logout = testDB.logout();
		boolean logout2 = testDB.logout();
		
		Assert.assertEquals(false, login);
		Assert.assertEquals(true, login2);
		Assert.assertEquals(false, login3);
		Assert.assertEquals(1, status);
		Assert.assertEquals(true, logout);
		Assert.assertEquals(false,logout2);
	}
	
	@Test
	public void test_Register() throws Exception {
		boolean register = testDB.register("test03", "test01@yorku.ca", "121");
		
		Assert.assertEquals(false, register);
	}
	
	
	@Test
	public void test_BookingandCancel() throws Exception{
		boolean login = testDB.login("test01@yorku.ca", "123");
		int status = testDB.get_currentUser().getStatus();
		boolean booking1 = testDB.bookingPlot(1, 60);
		boolean booking2 = testDB.bookingPlot(2, 60);
		boolean booking3 = testDB.bookingPlot(1, 60);
		int totalFee1 = testDB.get_Totalfee();
		boolean payment1 = testDB.make_payment("123456788765432");
		boolean payment2 = testDB.make_payment("1234567887654321");
	//	System.out.println("total fee is " + totalFee1);
		ArrayList<bookingInfo> tmp = testDB.retriveAvaliableOrder();
		boolean cancel1 = testDB.cancelOrder(tmp.get(0).getID());
		boolean cancel2 = testDB.cancelOrder(tmp.get(1).getID());
		boolean logout = testDB.logout();
		
		
		
		Assert.assertEquals(true, login);
		Assert.assertEquals(1, status);
		Assert.assertEquals(true, booking1);
		Assert.assertEquals(true, booking2);
		Assert.assertEquals(false, booking3);
		Assert.assertEquals(30, totalFee1);
		Assert.assertEquals(false, payment1);
		Assert.assertEquals(true, payment2);
		Assert.assertEquals(true, cancel1);
		Assert.assertEquals(true, cancel2);
		Assert.assertEquals(true, logout);
	}
	
	@Test
	public void test_Register2() throws Exception{
		int size = testDB.size_of_CustomerDB();
		boolean register1 = testDB.register("test00", "testAuto"+size+"@yorku.ca", "121");
		String email = "testAuto"+size+"@yorku.ca";
		boolean login = testDB.login(email, "121");
		int status = testDB.get_currentUser().getStatus();
		boolean logout = testDB.logout();
		
		
		Assert.assertEquals(true, register1);
		Assert.assertEquals(true, login);
		Assert.assertEquals(1, status);
		Assert.assertEquals(true, logout);
	}
	
	

}
