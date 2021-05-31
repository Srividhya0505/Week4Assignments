package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IrctcScreenshot {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.irctc.co.in/nget/train-search/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

		driver.findElementByXPath("//button [@type ='submit']  ").click();
		Thread.sleep(2000);

		driver.findElementByXPath("//a [contains(text(),'FLIGHT')]").click();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listHandles = new ArrayList<String>(windowHandles);
		String secondWin = listHandles.get(1);

		driver.switchTo().window(secondWin);
		// driver.switchTo().alert().accept();

		driver.findElementByXPath("//div[@class='modal-dialog modal-lg modal-dialog-centered']//div/div[2]/button")
				.click();
		//To take screenshot of 2nd window - Flights Page
		File source1 = driver.getScreenshotAs(OutputType.FILE);
        File target1 = new File ("./snaps/flight2.jpg");
		FileUtils.copyFile(source1, target1);
		System.out.println("Screenshot of Flights Page taken");
		
		//To take screenshot of single Element
		WebElement screenShot = driver.findElementByXPath("//img [@alt='Indigo']");
		File source = screenShot.getScreenshotAs(OutputType.FILE);
         File target = new File ("./snaps/flight1.jpg");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot of single WebElement taken");

		// close first window
		driver.switchTo().window(listHandles.get(0));
		driver.close();
	}

}
