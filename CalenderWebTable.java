package week4.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderWebTable {

	public static void main(String[] args) {
		 
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        ChromeDriver driver = new ChromeDriver();
        
       driver.get("http://leafground.com/pages/Calendar.html");
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
       
       driver.findElementByXPath("//input [@id='datepicker']").click();
	   driver.findElementByXPath("//table [@class= 'ui-datepicker-calendar']//tr[3]//td[2]/a").click();
	   
	}

}
