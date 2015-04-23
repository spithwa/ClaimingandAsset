package com.makerstudios.aud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import com.maker.helpers.sql.S3Helper;
import com.maker.helpers.sql.RedshiftHelperBase;
import com.makerstudios.aud.Findhost;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class testing {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  private String chromeDriverExecutable;
	private String killCommand;

  
  
  
  public static void main(String[] args) throws Exception {
		testing reader = new testing();
		
		RedshiftHelperBase rsClient = new RedshiftHelperBase();
		
		rsClient.populatecolumns();

		
	}
}
