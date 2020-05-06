package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrmCloud {

	public static RemoteWebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		
		driver = new ChromeDriver(options);
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//1) Go to https://demo.1crmcloud.com/
		driver.get("https://demo.1crmcloud.com/");
		
		
		//2) Give username as admin and password as admin
		
		driver.findElementById("login_user").sendKeys("admin");
		driver.findElementById("login_pass").sendKeys("admin");
		
		//3) Choose theme as Claro Theme
		Select theme = new Select(driver.findElementById("login_theme"));
		System.out.println("Drop down selected");
		theme.selectByVisibleText("Claro Theme");
		System.out.println("Claro Theme selected");
		
		driver.findElementByXPath("//span[text()='Login']").click();
		System.out.println("Logged in");
		
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		 //4) Click on Sales and Marketting 
		
		WebElement SalesAndMarketing = driver.findElementByXPath("//div[text()='Sales & Marketing']");
		SalesAndMarketing.click();
		System.out.println("Sales and Marketing Selected");
		Thread.sleep(10000);
		
		//5) Click Create contact
		driver.findElementByXPath("//div[text()='Create Contact']").click();
		System.out.println("User navigated to Create Contact Page");


		Thread.sleep(10000);
		
		//6) Select Title and type First name, Last Name, Email and Phone Numbers 
		
		Actions builder = new Actions(driver);
		WebElement eleTitle = driver.findElementById("DetailFormsalutation-input");
		wait.until(ExpectedConditions.elementToBeClickable(eleTitle));
		builder.click(eleTitle).build().perform();
		Thread.sleep(2000);
		
		WebElement eleMrs = driver.findElementByXPath("//div[text()='Prof.']");
		wait.until(ExpectedConditions.elementToBeClickable(eleMrs));
		builder.click(eleMrs).build().perform();
		System.out.println("Salutation selected");
		
		driver.findElementById("DetailFormfirst_name-input").sendKeys("Praveenram");
		driver.findElementById("DetailFormlast_name-input").sendKeys("Ravi");
		
		driver.findElementById("DetailFormemail1-input").sendKeys("clikbypravee@gmail.com");
		driver.findElementById("DetailFormphone_mobile-input").sendKeys("9003188294");
		
		//7) Select Lead Source as "Public Relations" 
		WebElement leadSource = driver.findElementById("DetailFormlead_source-input");
		builder.click(leadSource).build().perform();
		Thread.sleep(2000);
		
		WebElement Lead_Source = driver.findElementByXPath("//div[text()='Public Relations']");
		builder.click(Lead_Source).build().perform();
		Thread.sleep(2000);
		System.out.println("Public relations selected");
		
		
		//8) Select Business Roles as "Sales" 
		WebElement BusRole = driver.findElementById("DetailFormbusiness_role-input");
		builder.click(BusRole).build().perform();
		Thread.sleep(2000);
		
		WebElement BusinessRoles = driver.findElementByXPath("//div[text()='Sales']");
		builder.click(BusinessRoles).build().perform();
		System.out.println("Business Role Selected");
		
		//9) Fill the Primary Address, City, State, Country and Postal Code and click Save 
		
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("Tambaram");
		driver.findElementById("DetailFormprimary_address_city-input").sendKeys("Chennai");
		driver.findElementById("DetailFormprimary_address_state-input").sendKeys("Tamilnadu");
		driver.findElementById("DetailFormprimary_address_country-input").sendKeys("India", Keys.ARROW_DOWN, Keys.ENTER);
		driver.findElementById("DetailFormprimary_address_postalcode-input").sendKeys("600045");
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		System.out.println("Form Saved");
		
		Thread.sleep(5000);
		
		String PageTitle = driver.getTitle();
		System.out.println("Page Title is "+PageTitle);
		
		//10) Mouse over on Today's Activities and click Meetings 
		
		WebElement TodaysActivities = driver.findElementByLinkText("Today's Activities");
		wait.until(ExpectedConditions.elementToBeClickable(TodaysActivities));
		builder.moveToElement(TodaysActivities).build().perform();
		Thread.sleep(1000);
		
		WebElement Meetings = driver.findElementByXPath("//div[(contains(text(),'Meetings'))]");
		wait.until(ExpectedConditions.elementToBeClickable(Meetings));
		builder.click(Meetings).build().perform();
		Thread.sleep(3000);
		
		//11) Click Create 
		driver.findElementByLinkText("Create").click();
		
		//12) Type Subject as "Project Status" , Status as "Planned" 
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");
		WebElement MeetingStatus = driver.findElementById("DetailFormstatus-input");
		builder.click(MeetingStatus).build().perform();
		Thread.sleep(2000);
		
		WebElement currentStatus = driver.findElementByXPath("//div[text()='Planned']");
		builder.click(currentStatus).build().perform();
		Thread.sleep(2000);
		
		System.out.println("Meeting Scheduled");
		
		//13) Start Date & Time as tomorrow 3 pm and Duration as 1hr 
		driver.findElementById("DetailFormdate_start").click();
		WebElement tomorrow = driver.findElementByXPath("//div[@class='grid-cell number-cell text-right day inside current selected quiet responsive']/following::div[1]"); 
		wait.until(ExpectedConditions.elementToBeClickable(tomorrow));
		tomorrow.click();
		System.out.println("Meeting scheduled for tomorrow");		
		WebElement Time = driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input[@class='input-text']");
		wait.until(ExpectedConditions.visibilityOf(Time));
		Time.clear();
		Time.sendKeys("3:00pm", Keys.ENTER);
		driver.findElementById("DetailFormduration-time").clear();
		driver.findElementById("DetailFormduration-time").sendKeys("1hr",Keys.TAB);
		System.out.println("Meeting at 3 PM for one hour");
		//14) Click Add paricipants, add your created Contact name and click Save 
		driver.findElementByXPath("//span[text()=' Add Participants']").click();
		
		driver.findElementByXPath("//div[@id='app-search-text']//input[1]").sendKeys("Praveenram");
		Thread.sleep(3000);
		WebElement Contact = driver.findElementByXPath("//div[@id='app-search-list']//div[(contains(text(),'Sample Fname Lname'))]");
		wait.until(ExpectedConditions.visibilityOf(Contact));
		builder.click(Contact).build().perform();
		Thread.sleep(3000);
		
		driver.findElementById("DetailForm_save2-label").click();
		Thread.sleep(2000);
		
		//15) Go to Sales and Marketting-->Contacts 
		builder.moveToElement(SalesAndMarketing).build().perform();
		
		WebElement SMContact = driver.findElementByXPath("//div[text()='Contacts']");
		wait.until(ExpectedConditions.visibilityOf(SMContact));
		builder.click(SMContact).build().perform();
		Thread.sleep(2000);
		
		//16) search the lead Name and click the name from the result 
		WebElement Search = driver.findElementById("filter_text");
		Search.sendKeys("Sample Fname Lname",Keys.ENTER);
		Thread.sleep(5000);
		driver.findElementByXPath("//span[@class='detailLink']//a[1]").click();
		
		//17) Check weather the Meeting is assigned to the contact under Activities Section.
		
		WebElement meetingRecord = driver.findElementByXPath("(//span[@id='subpanel-activities']/ancestor::div[@id='DetailForm-subpanels']//a[contains(text(),'Project Status')])[1]"); 

		if (meetingRecord.isDisplayed()) {
			System.out.println("Meeting is assigned for the Contact."); 
		} else { 
			System.out.println("Meeting is not available for the Contact.");
		}
		

		
		
		

	}

}
