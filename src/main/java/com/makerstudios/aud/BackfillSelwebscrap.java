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

public class BackfillSelwebscrap {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  private String chromeDriverExecutable;
	private String killCommand;

  
  public void setUp() throws Exception {
	  if (Findhost.isMac()) {
			chromeDriverExecutable = "chromedriver_mac";
			killCommand = "killall ";
		} else if (Findhost.isWindows()) {
			chromeDriverExecutable = "chromedriver.exe";
			killCommand = "taskkill /F /IM ";
		} else {
			chromeDriverExecutable = "chromedriver_linux";
			killCommand = "killall ";
		}
		System.setProperty("webdriver.chrome.driver", chromeDriverExecutable);
		
    driver = new ChromeDriver();
    baseUrl = "https://accounts.google.com/";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }
  
  
  public void testSelwebscraplogin(String account,String pass) throws Exception {
	  
	  driver.manage().deleteAllCookies();
	    
	    driver.get(baseUrl + "/ServiceLogin?continue=https%3A%2F%2Fcms.youtube.com%2Fsignup%3Fapp%3Ddesktop%26hl%3Den%26next%3D%252F&service=youtubecms&hl=en&passive=true&uilel=2&ltmpl=default");
	    driver.findElement(By.id("Email")).clear();
	    driver.findElement(By.id("Email")).sendKeys(account);
	    driver.findElement(By.id("Passwd")).clear();
	    driver.findElement(By.id("Passwd")).sendKeys(pass);
	    driver.findElement(By.id("signIn")).click();
	    
	    driver.findElement(By.cssSelector("#creator-sidebar-section-id-reports > h3 > a.creator-sidebar-section-link.yt-uix-sessionlink > span.header-text")).click();
	    //driver.findElement(By.xpath("//li[@id='creator-sidebar-section-id-reports']/ul/li["+pos+"]/a/span")).click();
	    
	        driver.findElement(By.linkText("Performance Reports")).click();
	    
	    
  }
  
public int testSelwebscrapdownload(String MonthlyAssetFullFileName,String path, int loopno) throws Exception {
	  
		String pathvar = "["+loopno+"]";
		try {
			    
	    driver.findElement(By.xpath("//div[@id='creator-page-content']/div["+path+"]/table/tbody/tr"+pathvar+"/td[5]/a[3]/span")).click();
	}	 catch (NoSuchElementException e) {
		   return 0;
		}
							   
	    
	    File f3 = new File("/users/shrey.pithwa/downloads/"+MonthlyAssetFullFileName+".crdownload");

	    do {
	    	Thread.sleep(1000);
	      
	    } while (f3.exists());
	       
	    	
	    //driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return 1;
	  
    
    //driver.findElement(By.id("tipsy-profile-menu").id("header_logout")).click();
  }

  
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
                   if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  
  
  public static void main(String[] args) throws Exception {
		BackfillSelwebscrap reader = new BackfillSelwebscrap();
		
		RedshiftHelperBase rsClient = new RedshiftHelperBase();
		
		Set<String> val1 = rsClient.GetCMSvalues();

		Iterator itr = val1.iterator();

		while(itr.hasNext())
		{
			String val = (String) itr.next();
			String pass = null;
			String filename = val.substring(0,val.indexOf(" account :"));
			String account = val.substring(val.indexOf("account : ")+10,val.length());
			//String pos = "5";
			String path = "2";
			int diff = 0;
			
			
			System.out.println("******************Processing For******************");
			System.out.println("Filename :" + filename);
			System.out.println("Account :" + account);
			
			
             if (account.equalsIgnoreCase("datawarehouse4@makerstudios.com"))
			{
				pass = "tF6JILckXQmYyyVVBhZXlDikR";
			}
             if (account.equalsIgnoreCase("datawarehouse3@makerstudios.com"))
			{
				pass = "VDxIbtkBTYxI9WyznFPX1ElM7";
			}
             if (account.equalsIgnoreCase("datawarehouse5@makerstudios.com"))
			{
				pass = "HENiJ3LW1zvjUBqhqHJEtTvfB";
			}
             if (account.equalsIgnoreCase("datawarehouse7@makerstudios.com"))
			{
				pass = "5IofplTfc324QXkNPP8QyKtLU";
			}
             if (account.equalsIgnoreCase("datawarehouse25@makerstudios.com"))
			{
				pass = "v7QTnvixr1yXZmd1OnYB";
			}
             if (account.equalsIgnoreCase("datawarehouse17@makerstudios.com"))
			{
				pass = "i7CIEvVRk9vpA5tb";
				path = "3";
			}
             if (account.equalsIgnoreCase("datawarehouse16@makerstudios.com"))
			{
				pass = "r07NU3MIVrSZibhv";
				path = "3";
			}
             if (account.equalsIgnoreCase("datawarehouse18@makerstudios.com"))
			{
				pass = "CvoMrezooFJ7Vey9kriK";
			}
             if (account.equalsIgnoreCase("datawarehouse11@makerstudios.com"))
			{
				pass = "5iz7VLkmnMmi";
			}
             if (account.equalsIgnoreCase("datawarehouse13@makerstudios.com"))
			{
				pass = "fCgPoqxvtqsYzPsbkcTPN89TpJasah";
			}
             if (account.equalsIgnoreCase("datawarehouse22@makerstudios.com"))
			{
				pass = "E2kxsArF3mRBX7ewJTpr";
				path = "3";
			}
             if (account.equalsIgnoreCase("datawarehouse23@makerstudios.com"))
			{
				pass = "XQIFiEc0JfK3x6ZkjjSm";
				path = "3";
			}
             if (account.equalsIgnoreCase("datawarehouse20@makerstudios.com"))
			{
				pass = "gYSdHPHci0IWGG4BugeI";
			}
             if (account.equalsIgnoreCase("datawarehouse21@makerstudios.com"))
			{
				pass = "M3QbUSAVjRTJrksd73ZY";
			}
             if (account.equalsIgnoreCase("datawarehouse6@makerstudios.com"))
			{
				pass = "8kEuqwXJpb4di9OuP71C";
			}
             if (account.equalsIgnoreCase("datawarehouse15@makerstudios.com"))
			{
				pass = "7Zy3NM2R3os92Z3u";
				path = "3";
			}
             if (account.equalsIgnoreCase("datawarehouse19@makerstudios.com"))
			{
				pass = "MwyT6ufaGdjOzwDQDssb";
			}
             if (account.equalsIgnoreCase("datawarehouse@makerstudios.com"))
			{
				pass = "VOi2eQr2FPXhRqEmc3vzYCvMw";
			}
             if (account.equalsIgnoreCase("datawarehouse2@makerstudios.com"))
			{
				pass = "vwalsJ8WoZrG0DHlKxFaTyE7d";
			}
             if (account.equalsIgnoreCase("datawarehouse14@makerstudios.com"))
			{
				pass = "qubaQfuMittZPRdhzC9oF9WphbyCNJ";
			}
            if (account.equalsIgnoreCase("datawarehousedisney1@makerstudios.com"))
			{
				pass = "pZbAD8oNv282Rzgly17v";
			}
             if (account.equalsIgnoreCase("datawarehousedisney2@makerstudios.com"))
			{
				pass = "cGGyEorZJYz2sX7QnRyx";
			}
             if (account.equalsIgnoreCase("datawarehousedisney24@makerstudios.com"))
			{
				pass = "vyvBm6r3XM48Aod7xyhU";
			}
             if (account.equalsIgnoreCase("datawarehousedisney35@makerstudios.com"))
			{
				pass = "DisneyWelcome1";
			}
             if (account.equalsIgnoreCase("datawarehousedisney3@makerstudios.com"))
			{
				pass = "bt8FzxwYpnoBPnHoSkLN";
			}
             if (account.equalsIgnoreCase("datawarehousedisney4@makerstudios.com"))
			{
				pass = "iR4HzwT3BDQoHtP72l6A";
			}
             if (account.equalsIgnoreCase("datawarehousedisney5@makerstudios.com"))
			{
				pass = "pzIzOMxrhYmoScCgeeEl";
			}
             if (account.equalsIgnoreCase("datawarehousedisney6@makerstudios.com"))
			{
				pass = "AgySCLlBVFmCadYpWj3d";
			}
             if (account.equalsIgnoreCase("datawarehousedisney7@makerstudios.com"))
			{
				pass = "J9jcNlzQyaJfb6S9RgFS";
			}
             if (account.equalsIgnoreCase("datawarehousedisney27@makerstudios.com"))
			{
				pass = "NcbHLlLaGJKYNbc74hoQ";
			}
             if (account.equalsIgnoreCase("datawarehousedisney29@makerstudios.com"))
			{
				pass = "S5MjgEB4wpARcNE7evrr";
			}
             if (account.equalsIgnoreCase("datawarehousedisney8@makerstudios.com"))
			{
				pass = "UqrqkgpDTEf1Lau7G9kR";
			}
             if (account.equalsIgnoreCase("datawarehousedisney9@makerstudios.com"))
			{
				pass = "z7utdebxeP4Xx4rBwfbO";
			}
             if (account.equalsIgnoreCase("datawarehousedisney16@makerstudios.com"))
			{
				pass = "5iJLxtI55G6GwOzU40CZ";
			}
             if (account.equalsIgnoreCase("datawarehousedisney10@makerstudios.com"))
			{
				pass = "c9cdKyCZ91sItP9MqoaS";
			}
             if (account.equalsIgnoreCase("datawarehousedisney11@makerstudios.com"))
			{
				pass = "JabXRvKhBtkWxdeb3mKH";
			}
             if (account.equalsIgnoreCase("datawarehousedisney12@makerstudios.com"))
			{
				pass = "0v648KKZwWpA7m7M5GJw";
			}
             if (account.equalsIgnoreCase("datawarehousedisney13@makerstudios.com"))
			{
				pass = "pjrHlWFl2rzZrvcXxj7n";
			}
             if (account.equalsIgnoreCase("datawarehousedisney14@makerstudios.com"))
			{
				pass = "AErTaEWtpxXeZaCx0STN";
			}
             if (account.equalsIgnoreCase("datawarehousedisney15@makerstudios.com"))
			{
				pass = "xZ4W0K3fEbf40dL4a6vb";
			}
             if (account.equalsIgnoreCase("datawarehousedisney21@makerstudios.com"))
			{
				pass = "5ZWFpGGvMMnJnoNmU7UZ";
			}
             if (account.equalsIgnoreCase("datawarehousedisney28@makerstudios.com"))
			{
				pass = "lrmt0IEwjTTz8r1XdIiH";
			}
             if (account.equalsIgnoreCase("datawarehousedisney23@makerstudios.com"))
			{
				pass = "9sWhqm3R2p7Ge9mckKxF";
				path = "3";
			}
             if (account.equalsIgnoreCase("datawarehousedisney22@makerstudios.com"))
			{
				pass = "wrvyfgU9asY968BzXJ2X";
			}
             if (account.equalsIgnoreCase("datawarehousedisney17@makerstudios.com"))
			{
				pass = "fMfbwoRLdrvs2qTaKcsK";
			}
             if (account.equalsIgnoreCase("datawarehousedisney26@makerstudios.com"))
			{
				pass = "tRPBGiKCJ4o7IAAxDpVL";
			}
             if (account.equalsIgnoreCase("datawarehousedisney18@makerstudios.com"))
			{
				pass = "6gi47YWVGY0jIQbZHyMd";
			}
             if (account.equalsIgnoreCase("datawarehousedisney19@makerstudios.com"))
			{
				pass = "dEP16LOgs0SAXpLxRtvw";
			}
             if (account.equalsIgnoreCase("datawarehousedisney20@makerstudios.com"))
 			{
 				pass = "dXPc0Cpnfo6bLnADoQh8";
 			}
             if (account.equalsIgnoreCase("datawarehousedisney8@makerstudios.com"))
 			{
 				pass = "UqrqkgpDTEf1Lau7G9kR";
 			}
              if (account.equalsIgnoreCase("datawarehousedisney25@makerstudios.com"))
			{
				pass = "3JHFA9p4rChrAkD7iK4r";
				path = "3";
				diff = 1;
			}
             if (account.equalsIgnoreCase("datawarehouse26@makerstudios.com"))
			{
				pass = "7Ayz5cdzdkxmvWmQ4MSZ";
			}
             if (account.equalsIgnoreCase("datawarehouse27@makerstudios.com"))
 			{
 				pass = "78re19pdzej3kb8ctzne";
 			}
             
reader.setUp();
			
			
			
			
	reader.testSelwebscraplogin(account,pass);

	int loopno = 2;
			
			while (loopno != -1)
			{
								
				SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd");
				SimpleDateFormat insert = new SimpleDateFormat ("MM/dd/yyyy");
				  
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH,-loopno);
				
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				String firstofthemonth = ft.format(cal.getTime());
				String firstofthemonthinsert = insert.format(cal.getTime());
				System.out.println("Processing for the Month : "+firstofthemonthinsert);  
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				String lastofthemonth = ft.format(cal.getTime());
				
				
				String MonthlyAssetFullFileName = "YouTube_"+filename+"_M_"+firstofthemonth+"_"+lastofthemonth+"_rev_views_by_asset.csv";
				
				int checkmax = reader.testSelwebscrapdownload(MonthlyAssetFullFileName,path,loopno);
				
				if (checkmax==0)
				{
					System.out.println("Reached Max lifetime");  
					loopno = -1;
					break;
				}
				
				System.out.println("Reading Headers");    
				
				
				
				//Monthly
				FileReader fileReaderheader3 = new FileReader("/users/shrey.pithwa/downloads/"+MonthlyAssetFullFileName);
	            String NewMonthlyAssetFullFileName = "New_"+MonthlyAssetFullFileName;
	            
	            BufferedReader bufferedReaderheader3 = new BufferedReader(fileReaderheader3);
	            
				String MonthlyAssetheaders = bufferedReaderheader3.readLine();
				MonthlyAssetheaders = MonthlyAssetheaders.replace("-", "_").replace("*", "_").replace("#", "_").replace("!", "_").replace("@", "_").replace("$", "_").replace("+", "_").replace("%", "_").replace("/", "_").replace(")", "_").replace("(", "_").replace(" ", "_").replace(",", " varchar(max) NULL ENCODE LZO, ");
				MonthlyAssetheaders = "Create table claimingdata."+filename.replace("+", "_")+"_"+MonthlyAssetFullFileName.replace("+", "_").replace(".csv", "")+" ("+MonthlyAssetheaders+" varchar(max) NULL ENCODE LZO ) DISTSTYLE EVEN";
				bufferedReaderheader3.close();
				
				System.out.println("Finished");
				
			
				
				System.out.println("Starting : Removing quotes");        
				
						
				
				//Monthly
	            FileReader fileReader3 = new FileReader("/users/shrey.pithwa/downloads/"+MonthlyAssetFullFileName);
	            FileWriter writer3 = new FileWriter("/users/shrey.pithwa/downloads/"+NewMonthlyAssetFullFileName);
				   
				BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
				String line3 = null;
				while ((line3 = bufferedReader3.readLine()) != null) {
					
					writer3.write(line3.toString().replace("\"\"", "").replace("'", "\\'").replace("\\","\\\\"));
					writer3.write(System.lineSeparator());
				    
				}
				bufferedReader3.close();
				writer3.close();
				
				System.out.println("Finished : Removing quotes ");
				
				
				
				long time = System.currentTimeMillis();

				S3Helper s3Helper3 = new S3Helper();
		        
		        
		       //Monthly
		        time = s3Helper3.putFile("/users/shrey.pithwa/downloads/",NewMonthlyAssetFullFileName);
	           
		        //Load data into stage table
		        if (time>=0) {
		                // push to RedShift
		                
		                	time = rsClient.importCSVIntoTableMonthlydiff(MonthlyAssetheaders,NewMonthlyAssetFullFileName,filename.replace("+", "_")+"_"+MonthlyAssetFullFileName.replace("+", "_").replace(".csv", ""),filename,firstofthemonthinsert);
		                
		        }

		       if (time==-1) {
		                
		        System.out.println("Error Loading "+filename+"_"+MonthlyAssetFullFileName+" table : Check - select * from stl_load_errors where filename = 's3://maker_analytics/shadow_youtube/' order by starttime desc limit 10;");
		        }
		        else
		        {
		        	
		        		rsClient.inserttablemonthly(filename.replace("+", "_")+"_"+MonthlyAssetFullFileName.replace("+", "_").replace(".csv", ""));
	                
		        	rsClient.UpdatetableMonthly(filename,firstofthemonthinsert,"asset_report_monthly");    
		            System.out.println("Finished : Pushed into the table Monthly");
		                
		        }
		        
		        //Remove the files from local disk
		      		
				File del5 = new  File("/users/shrey.pithwa/downloads/",MonthlyAssetFullFileName);
			       if(del5.delete())
			        {
			                System.out.println("File :" + del5.getName() +" was deleted successfully!");
			        }
			        else
			        {
			                System.out.println("Error deleteing File :" + del5.getName() );
			        }
			       
			       File del6 = new File("/users/shrey.pithwa/downloads/"+NewMonthlyAssetFullFileName);
					if(del6.delete())
			        {
			                System.out.println("File :" + del6.getName() +" was deleted successfully!\n");
			        }
			        else
			        {
			                System.out.println("Error deleteing File :" + del6.getName() +"\n" );
			        }
					
				loopno = loopno+1;
			}
	
	
			
			
reader.tearDown();
			
			
			
			
		}
		
		
		
		
		/*
		
		reader.setUp();
		reader.testSelwebscrap();
		reader.tearDown();
		
		*/
	}
}
