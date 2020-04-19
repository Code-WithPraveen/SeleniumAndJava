package testCases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Browser Set up
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		
		// Invoke the Browser
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
		
		//Click on Hotels
		driver.findElementByXPath("//span[text()='Hotels']").click();
		System.out.println("Navigated to Hotels Page");
		Thread.sleep(3000);
		
		//Entering the city name as Goa
		driver.findElementById("city").click();
		driver.findElementByXPath("//input[@class='react-autosuggest__input react-autosuggest__input--open']").sendKeys("Goa", Keys.TAB);
		
		//LocalDate function is to get the current date - DD-MM-YYYY in a string format
		String datestamp = LocalDate.now().toString();
		System.out.println("Current date is "+datestamp);
		//substring function to get the date from that timestamp
		String date1 = datestamp.substring(8, 10);
		int date = Integer.parseInt(date1)+5;
		//substring function to get the month from that datestamp
		String month = datestamp.substring(5, 7);
		//Converting the received datastamp into int
		int monthInt = Integer.parseUnsignedInt(month);
		//getting the next Month by adding 1
		int nextMonth = monthInt+1;
		System.out.println("Next Month is " + nextMonth);
		String nextMonthString = Month.of(nextMonth).toString();
		nextMonthString = nextMonthString.substring(0, 1)+nextMonthString.substring(1, nextMonthString.length()).toLowerCase();
		
		/*
		 * // //get current date time with Date() Date date = new Date(); // //Get only
		 * the date(and not month,year,time,etc) DateFormat dateFormat = new
		 * SimpleDateFormat("mm"); // // Now format the date String today=
		 * dateFormat.format(date); int today1 = Integer.parseInt(today); int tommorow =
		 * Integer.parseInt(today)+1; // Print the tommorow's date
		 * System.out.println(today1); System.out.println(tommorow);
		 */
		
		
		WebElement webElement1 = driver.findElementByXPath("//div[text()='"+nextMonthString+"']/ancestor::div[@class='DayPicker-Month']//div[text()='"+date+"']");
		String startDate = webElement1.getText();
		System.out.println("Stay starts on " +startDate);
		webElement1.click();
		
		int oneWeek = Integer.parseInt(startDate);
		int nextWeek = oneWeek+7;
		
		
		WebElement webElement2 = driver.findElementByXPath("//div[text()='"+nextMonthString+"']/ancestor::div[@class='DayPicker-Month']//div[text()='"+nextWeek+"']");
		System.out.println("End date is on "+webElement2.getText());
		webElement2.click();
		
		driver.findElementById("guest").click();
		driver.findElementByXPath("(//li[text()='2'])[1]").click();
		driver.findElementByXPath("(//li[text()='2'])[2]").click();
		driver.findElementByXPath("//button[text()='APPLY']").click();
		System.out.println("Selected the number of guests");
		
		driver.findElementByXPath("//button[text()='Search']").click();
		System.out.println("Searching for rooms");
		
		//clicking the black screen that appears after search button click
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='mmBackdrop wholeBlack']")));
				driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Baga']")));
		driver.findElementByXPath("//label[text()='Baga']").click();
		System.out.println("Baga Selected");
		
		
		
		driver.findElementByXPath("//label[text()='5 Star']").click();
		System.out.println("5 star hotels displayed");
		
		Thread.sleep(5000);
		
		driver.findElementByXPath("(//div[contains(@class, 'listingRow')])[1]").click();
		System.out.println("First hotel in the list is selected");
		
		Set<String> windowSet = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowSet);
		
		driver.switchTo().window(windowList.get(1));
		System.out.println("New window title is "+driver.getTitle());
		
		
		driver.findElementByXPath("(//span[text() = 'MORE OPTIONS'])[1]").click();
		System.out.println("EMI option selected");
		
		driver.findElementByXPath("(//span[text() = 'SELECT'])[1]").click();
		System.out.println("Selected 3 months option for EMI");
		
		driver.findElementByXPath("//span[@class='close']").click();
		System.out.println("Closed the EMI window");
		
		System.out.println("Page Title is "+driver.getTitle());
		
		driver.close();
		System.out.println("Hotels Page closed");
		driver.switchTo().window(windowList.get(0));
		System.out.println("Main Page title is "+driver.getTitle());
		driver.close();
		
		System.out.println("Main Page Closed");
		
		
	}

}
