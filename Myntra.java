package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

		Actions builder = new Actions(driver);
		WebElement women = driver.findElementByXPath("//a[@data-group ='women']");
		WebElement womenJacketsAndCoats = driver.findElementByXPath("//a[text() ='Jackets & Coats']");

		builder.moveToElement(women).pause(1000).moveToElement(womenJacketsAndCoats).pause(1000).click().perform();
		Thread.sleep(2000);

		// Total Count of Jackets and Coats//Replace the special char and string and
		// Trim the String
		String totalCount = driver
				.findElementByXPath("//h1 [text() ='Coats & Jackets For Women']/following-sibling::span").getText();
		totalCount = totalCount.replace("-", " ");
		totalCount = totalCount.replace("items", " ");
		totalCount = totalCount.trim();
		// String to Int Conversion
		int totalofJacketsCoats = Integer.parseInt(totalCount);
		System.out.println("Total Count of Jackets and Coats:   " + totalofJacketsCoats);

		String jacketTextCount = driver.findElementByXPath("//input [@value ='Jackets']/following-sibling::span")
				.getText();
		jacketTextCount = jacketTextCount.replace("(", " ");
		jacketTextCount = jacketTextCount.replace(")", " ");
		jacketTextCount = jacketTextCount.trim();
		int countofJackets = Integer.parseInt(jacketTextCount);
		System.out.println("Total Count of Jacket " + countofJackets);

		String coatTextCount = driver.findElementByXPath("//input [@value ='Coats']/following-sibling::span").getText();
		coatTextCount = coatTextCount.replace("(", " ");
		coatTextCount = coatTextCount.replace(")", " ");
		coatTextCount = coatTextCount.trim();
		int countofCoats = Integer.parseInt(coatTextCount);
		System.out.println("Total Count of Coat " + countofCoats);

		// Validation for Total Count vs Verify Total Count
		int verifyTotalCount = countofJackets + countofCoats;
		System.out.println("Verify Total Count of Jackets and Coats:  " + verifyTotalCount);

		if (totalofJacketsCoats == verifyTotalCount) {
			System.out.println("The total count is same");
		} else {
			System.out.println("The total count is not same");
		}
		// Check Coats - Used Grandchild to GrandParent XPath - to be checked form here
		driver.findElementByXPath(
				"(//label [@class='common-customCheckbox vertical-filters-label']//following-sibling::div)[2]").click();
		// Click + More option under BRAND
		driver.findElementByXPath("//div [@class='vertical-filters-filters brand-container']/div[2]").click();
		// Type MANGO and click checkbox
		WebElement searchBrand = driver.findElementByXPath("//input [@placeholder='Search brand']");
		searchBrand.sendKeys("MANGO");
		Thread.sleep(1000);
		driver.findElementByXPath("//label[@class=' common-customCheckbox']/div").click();
		// Close the pop-up x
		driver.findElementByXPath("//span [@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		Thread.sleep(2000);
		// Confirm all the Coats are of brand MANGO
		List<WebElement> searchResults = driver.findElementsByXPath("//h3[@class='product-brand']");
		List<String> namesofProduct = new ArrayList<String>();

		int searchResultssize = searchResults.size();
		System.out.println("Search Results Size count" + searchResultssize);

		for (WebElement eachResult : searchResults) {
			namesofProduct.add(eachResult.getText());
			// System.out.println(namesofProduct);
		}
		boolean mangoProductcheck = namesofProduct.contains("MANGO");

		if (mangoProductcheck) {
			System.out.println("All Products are of Mango Brand");
		} else {
			System.out.println("The Products are not of same Brand");
		}
       //Sort by Better Discount
		WebElement sortBy = driver.findElementByXPath("//div [@class ='sort-sortBy']");
		WebElement betterDiscount = driver.findElementByXPath("(//ul [@class='sort-list']//label)[3]");
		Actions builder2 = new Actions (driver);
		
		builder2.moveToElement(sortBy).pause(1000).moveToElement(betterDiscount).click().perform();
		Thread.sleep(2000);
		//Find the price of first displayed item
		String firstProductPrice = driver.findElementByXPath("(//span [@class = 'product-discountedPrice'])[1]").getText();
		System.out.println("First displayed item Price:   " + firstProductPrice);
		
		//Mouse over on size of the first item & //Click on WishList Now
		WebElement firstProductclick = driver.findElementByXPath("(//span [@class = 'product-discountedPrice'])[1]");
		WebElement wishList = driver.findElementByXPath ("(//span  [@class = 'product-wishlistFlex product-actionsButton product-wishlist '])[1]");
		
		Actions builder3 = new Actions(driver);
		builder3.moveToElement(firstProductclick).pause(1000).moveToElement(wishList).click().perform();
		
		//Close Browser
		//driver.close();
	}

}
