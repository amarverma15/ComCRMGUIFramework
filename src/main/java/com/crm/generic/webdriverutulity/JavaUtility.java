package com.crm.generic.webdriverutulity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber()
	{
		Random random=new Random();
		int randomnum = random.nextInt(5000);
		return randomnum;
	}
	
	public String getSystemDateYYYYDDMM()
	{
		Date dateobj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		return date;
	}
	public String getRequiredDateYYYYDDMM(int days)//if i pass positive data after days and negative means previous days
	{
		
			//System.out.println(dateobj.toString());
			
			/*SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");//SimpleDateFormt it is a class and Month should be uppercase
			
			 Calender cal = sim.;//getCalendar() it will return the calendar object.
	//using calendar object we can mention how many days or years before and after we can mention
			cal.add(Calendar.DAY_OF_MONTH, days);//this method will asking the fied. There is astatic  var is called days of month or year whatever..
			String reqDate=sim.format(cal.getTime());
			return reqDate;*/
		
		String reqdate=LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return reqdate;
	}

}
