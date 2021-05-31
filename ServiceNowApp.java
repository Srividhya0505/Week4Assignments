package week4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowApp {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://dev103117.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

		driver.switchTo().frame("gsft_main");
		
	    driver.findElementById("user_name").sendKeys("admin");
	    driver.findElementById("user_password").sendKeys("India@123");
	    Thread.sleep(500);
	    driver.findElementByXPath("//button[@type='submit']").click();
	    
	    /*
	    WebElement firstData = driver.findElementByXPath("//span [text() = 'Self-Service']");*/
	    WebElement resolved = driver.findElementByXPath("//div[text()='Resolved']");
		WebElement incidentAll = driver.findElementByXPath("(//div [text()='All'])[2]");
      
	    WebElement navigator = driver.findElementById("filter");
	    navigator.sendKeys("Incident");
	    
		Actions builder = new Actions(driver);
		builder.moveToElement(resolved).pause(500).moveToElement(incidentAll).click().perform();

		driver.switchTo().frame("gsft_main");
		driver.findElementByXPath("//button [@id='sysverb_new']").click();
		WebElement callerID = driver.findElementByXPath("//input [@id='sys_display.original.incident.caller_id']/../div[2]/input");
		callerID.sendKeys("jew");
		callerID.sendKeys(Keys.TAB);
		
		driver.findElementById("incident.short_description").sendKeys("Test Assignment");
		
		WebElement contactType = driver.findElementById("incident.contact_type");
		WebElement incident = driver.findElementById("incident.number");
		builder.moveToElement(contactType).pause(500).moveToElement(incident).click().perform();
		  String incidentID = driver.findElementById("sys_original.incident.number").getAttribute("value");  
		  Thread.sleep(1000);
		  System.out.println("Incident ID:    " + incidentID);
		   driver.findElementByXPath("//button [text()='Submit']").click();
		 
		  //Search Incident ID
		  WebElement searchIncidentID = driver.findElementByXPath("//tr [@class='list_header list_header_search_row']//div//input");
		    searchIncidentID.sendKeys(incidentID);
		    searchIncidentID.sendKeys(Keys.ENTER);
		    Thread.sleep(1000);
		    
		 //Get Created Record ID   
		   WebElement recordID = driver.findElementByXPath("//span [contains(text(), 'Select record for action')]");
		     String recordIDCheck = recordID.getText();
		          System.out.println("RecordID:    "+ recordIDCheck);
		  
		   //Validation of created Record ID with Incident ID         
		        if (recordIDCheck.contains(incidentID)) {
					System.out.println("Record is created successfully");
		        }
		        else {
		        	System.out.println("Record is not created successfully");
				}
		       
		  
	}

}
