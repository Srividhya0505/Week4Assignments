package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		// Click L'Oreal Paris
		WebElement brands = driver.findElementByXPath("//a [text()='brands']");
		WebElement popular = driver.findElementByXPath("//a [text()='Popular']");
		WebElement lorealLink = driver.findElementByXPath("//div [@id='brandCont_Popular']/ul/li[5]/a/img");

		Actions builder = new Actions(driver);
		builder.moveToElement(brands).pause(400).moveToElement(popular).moveToElement(lorealLink).click().perform();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

		// Windows Handle to handle new window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listHandles = new ArrayList<String>(windowHandles);
		String secondWin = listHandles.get(1);
		driver.switchTo().window(secondWin);

		Actions builder1 = new Actions(driver);
		WebElement nykaaLogo = driver.findElementById("NykaaLogo");
		builder.moveToElement(nykaaLogo);
		// to perform Scroll on application using Selenium
		// Javascript executor
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500)");
		Thread.sleep(1000);
		// Click sort By and select customer top rated
		WebElement sortBy = driver.findElementByXPath("//span [text() = 'Sort By : ']/following::i");
		sortBy.click();
		driver.findElementByXPath("(//div [@class='sort-container show-sort'])/div[4]").click();
		Thread.sleep(1000);
		// Javascript executor
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500)");
		Thread.sleep(1000);

		// To select Category, Hair, Hair Care, and Shampoo
		WebElement category = driver.findElementByXPath("//div [text()='Category']");
		Actions builder2 = new Actions(driver);
		builder.moveToElement(category).click().perform();

		driver.findElementByXPath("//span [text()='Hair']").click();
		driver.findElementByXPath("//span [text()='Hair Care']").click();
		driver.findElementByXPath("//label [@for='chk_Shampoo_undefined']//span").click();
		// Check for applied filter
		String appliedFilter = driver.findElementByXPath("//ul [@class='pull-left applied-filter-lists']/li").getText();
		if (appliedFilter.contains("Shampoo")) {
			System.out.println("Filter is applied correctly");
		} else {
			System.out.println("Filter is not applied correctly");
		}
		// to find Color Protect Shampoo
		driver.findElementByXPath("//span[contains(text(),'Paris Colour Protect Shampoo')]").click();
		Thread.sleep(3000);
		// GO to the new window
		Set<String> windowHandle = driver.getWindowHandles();
		List<String> listHandles2 = new ArrayList<String>(windowHandle);
		String thridWin = listHandles2.get(2);
		driver.switchTo().window(thridWin);
		
		// select size as 175ml
		driver.findElementByXPath("//span [contains (text(), '175ml')]").click();
		Thread.sleep(1000);
		// Print the MRP of the product
	     String shampooPrice = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		System.out.println("MRP of 175ml Shampoo Price:   " + shampooPrice);
		Thread.sleep(3000);
		// Click on ADD to BAG
		WebElement addToBagButton = driver.findElementByXPath("//button [@class='combo-add-to-btn prdt-des-btn js--toggle-sbag nk-btn nk-btn-rnd atc-simple m-content__product-list__cart-btn  ']");
		  addToBagButton.click();
          Thread.sleep(3000); 
		//Go to Shopping Bag
		WebElement addToBag = driver.findElementByXPath("//div [@class ='AddBagIcon']");
		addToBag.click();
		// Go to Grand Total Amount//// Print the Grand Total amount
		String price = driver.findElementByXPath("//div[@class='first-col']/div").getText();
		System.out.println("Grand Total Price:    " + price);
		Thread.sleep(2000);
		//Click Proceed
		driver.findElementByXPath("//button [@class ='btn full fill no-radius proceed ']").click();
		//Click on Continue as Guest
		driver.findElementByXPath("//button [@class='btn full big']").click();
        //Check if this grand total is the same in step 13
		String grandTotal = driver.findElementByXPath("//div [text()='Grand Total']/following::div").getText();
		 System.out.println("Grand Total Price Check during Payment:    "+ grandTotal);
		 
		 if(grandTotal.matches(price)) {
			 System.out.println("The Total Price is same as checkout Price");}
		 else {
			 System.out.println("The Total Price is not same as checkout Price");
		 }
		driver.quit();
	}

}
