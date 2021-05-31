package week4.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailWebTable {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

		driver.findElementById("txtStationFrom").clear();
		Thread.sleep(1000);

		WebElement fromStation = driver.findElementById("txtStationFrom");
		fromStation.sendKeys("ms");
		fromStation.sendKeys(Keys.TAB);

		driver.findElementById("txtStationTo").clear();
		WebElement toStation = driver.findElementById("txtStationTo");
		toStation.sendKeys("mdu");
		;
		toStation.sendKeys(Keys.TAB);
		driver.findElementByXPath("//label [text()='Sort on Date']").click();

		// driver.findElementByXPath("//input [@value='Get Trains']").click();
		Thread.sleep(1000);

		/*List<WebElement> allRows = driver
				.findElementsByXPath("//table [@class ='DataTable TrainList TrainListHeader']//tr");
		int rowCount = allRows.size();*/

		List<WebElement> listTrainNames = driver
				.findElementsByXPath("//table[@class='DataTable TrainList TrainListHeader']//tr//td[2]");
		List<String> trainNames = new ArrayList<String>();

		for (WebElement eachTrainName : listTrainNames) {
			trainNames.add(eachTrainName.getText());
		}
		Collections.sort(trainNames);

		// copy the list to set and to remove duplicates
		// Set<String> setTrainNames = new LinkedHashSet<String>(listTrainNames);

		System.out.println("Train Names List");

		for (String eachTrainName : trainNames) {
			System.out.println(eachTrainName);

		}
	}
}
