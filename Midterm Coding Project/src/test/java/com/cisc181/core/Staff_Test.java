package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;

public class Staff_Test{

	static ArrayList<Staff> stafflist = new ArrayList<Staff>();
	@BeforeClass
	public static void setUpBeforeClass() throws PersonException {
	}

	@AfterClass
	public static void tearDownAfterClass() throws PersonException {
	}

	@Before
	public void setUp() throws PersonException {
	}

	@After
	public void tearDown() throws PersonException {
	}
	
	@BeforeClass
	public static void setup() throws PersonException{

		Date DOB = new Date(1);
		Date hireDate = new Date();
		
		Staff staff1 = new Staff(eTitle.MR);
		Staff staff2 = new Staff(eTitle.MRS);
		Staff staff3 = new Staff(eTitle.MR);
		Staff staff4 = new Staff(eTitle.MS);
		Staff staff5 = new Staff(eTitle.MR);
		
		staff1.setSalary(100);
		staff2.setSalary(200);
		staff3.setSalary(300);
		staff4.setSalary(400);
		staff5.setSalary(500);
		
		stafflist.add(staff1);
		stafflist.add(staff2);
		stafflist.add(staff3);
		stafflist.add(staff4);
		stafflist.add(staff5);
	}
	
	@Test
	public void testSalary(){
		double sum = 0;
		for (Staff i:stafflist)
			sum += i.getSalary();
		
		assertEquals(300, (sum/5.0), 0);
	}
	
	@Test
	public void testPhoneNumber() throws PersonException{
		boolean Exception = true;
		boolean Actual= false;
		Date DOB = new Date(1);
		Date hireDate = new Date();
		try{
			stafflist.add(new Staff("Matt","D", "Lee",  DOB,"USA","1111111-1111",
					"email@gmail.com","1 to 2", 1, 1000, hireDate, eTitle.MR ));
		} catch(PersonException e){
			Actual = true;
		}
		assertTrue(true);
	}
	


	@Test
	public void testDOB() throws PersonException{
		boolean Exception = true;
		boolean Actual= false;
		Calendar birthDate = Calendar.getInstance();
		birthDate.set(1900, 1, 1);
		Date DOB = birthDate.getTime();
		Date hireDate = new Date();
		try{
			stafflist.add(new Staff("name1","name", "name",DOB,"country1","(111)-1111-1111",
					"email@mail.com","1 to 2", 1, 1000, hireDate, eTitle.MR ));
		} catch(PersonException e){
			assertTrue(true);
		}
		assertEquals(Exception, Actual);
	}
}