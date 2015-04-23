package com.makerstudios.aud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import com.maker.helpers.sql.S3Helper;
import com.maker.helpers.sql.RedshiftHelperBase;
import com.makerstudios.aud.Findhost;


public class test {
   
  
  public static void main(String[] args) throws Exception {
	
	  
	  SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd");
	  
	  Calendar cal = Calendar.getInstance();
	  cal.add(Calendar.MONTH,-2);
	  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	  String a = ft.format(cal.getTime());
	  System.out.println(a);
	  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	  String b = ft.format(cal.getTime());
	  System.out.println(b);
	  
		
	}
}
