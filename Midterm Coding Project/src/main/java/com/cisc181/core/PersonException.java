package com.cisc181.core;

import java.io.*;
import java.util.Date;

public class PersonException extends Exception{

	
	private String inv_number;
	private Date inv_DOB;
	
	public PersonException(Date invalid_DOB)
	{
		this.inv_DOB = invalid_DOB;
	}
	
	public PersonException(String invalid_number)
	{
		this.inv_number = invalid_number;
	}
	
}