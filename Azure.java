package testCases;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Azure {

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
		
		
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(20000);
		System.out.println("User Enters Home Screen");
		
		WebElement elePricing = driver.findElementByLinkText("Pricing");
		action.click(elePricing).build().perform();
		Thread.sleep(5000);
		System.out.println("Pricing Clicked");
		
		driver.findElementByLinkText("Pricing calculator").click();;
		Thread.sleep(5000);
		System.out.println("User Navigates to Pricing Calculator page");
		
		driver.findElementByXPath("//button[text()='Containers']").click();
		
		System.out.println("Containers clicked");
		
		
		//5) Select Container Instances 
		WebElement eleContainerInstances = driver.findElementByXPath("(//span[text()='Container Instances'])[3]");
		wait.until(ExpectedConditions.elementToBeClickable(eleContainerInstances));
		action.click(eleContainerInstances).build().perform();
		System.out.println("Container Instances Selected ");
		
		//6) Click on Container Instance Added View 
		WebElement ContainerInstanceView = driver.findElementByXPath("//span[@class='module-summary']");
		System.out.println("Container Instance Added View is "+ContainerInstanceView.getText());
		
		//7) Select Region as "South India"
		WebElement eleRegion = driver.findElementByXPath("//select[@name='region']");
		Select Region = new Select(eleRegion);
		Region.selectByVisibleText("South India");
		System.out.println("Region selected is South India");
				
		//8) Set the Duration as 180000 seconds 
		WebElement eleDuration = driver.findElementByXPath("//input[@name='seconds']");
		eleDuration.clear();
		eleDuration.sendKeys("180000");
				
		//9) Select the Memory as 4GB 
		WebElement eleMemory = driver.findElementByXPath("//select[@name='memory']");
		Select selMemory = new Select(eleMemory);
		selMemory.selectByValue("4");
				
		//10) Enable SHOW DEV/TEST PRICING 
		WebElement eleToggle1 = driver.findElementByXPath("(//div[@class='toggler-slide '])[1]");
		action.click(eleToggle1).build().perform();
		
		//11) Select Indian Rupee  as currency 
		WebElement eleCurrency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		
		Select selCurrency = new Select(eleCurrency);
		selCurrency.selectByValue("INR");
		
		//12) Print the Estimated monthly price 
		
		WebElement eleMonthlyPrice = driver.findElementByXPath("(//span[text()= 'Monthly cost']//following::span[@class='numeric'])[3]");
		String MonthlyPrice = eleMonthlyPrice.getText();
		System.out.println("Estimated Monthly Price is " +MonthlyPrice);
		
		//13) Click on Export to download the estimate as excel 
		WebElement eleExport = driver.findElementByXPath("//button[text()='Export']");
		action.click(eleExport).build().perform();
		System.out.println("Estimates Exported Successfully");
		File Export1 = new File("C:\\Users\\praveenram.ravi\\Downloads\\ExportedEstimate(1).xlsx");
		if(Export1.exists())
		{
		System.out.println("Export is successful");
		}
		else
		{
		System.out.println("Export is not successful");
		}


		//14) Navigate to Example Scenarios and Select CI/CD for Containers
		action.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).click().build().perform();
		System.out.println("User Navigated to Example Scenarios");
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
		System.out.println("CI/CD for containers selected");
	

		//15) Click Add to Estimate
		WebElement eleAddToEstimate = driver.findElementByXPath("//button[text()='Add to estimate']");
		wait.until(ExpectedConditions.elementToBeClickable(eleAddToEstimate));
		action.moveToElement(eleAddToEstimate).click().build().perform();
	
		System.out.println("Added to Estimates");

		//16) Change the Currency as Indian Rupee
		
		WebElement eleCurrency1 = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select Currency1 = new Select(eleCurrency1);
		Currency1.selectByValue("INR");
		System.out.println("Indian Rupees Selected");

		//17) Enable SHOW DEV/TEST PRICING
		action.moveToElement(driver.findElementByXPath("//div[contains(@class,'toggler-slide')]")).click().build().perform();
		System.out.println("SHOW DEV/TEST PRICING clicked");

		//18) Export the Estimate
		action.moveToElement(driver.findElementByXPath("//button[text()='Export']")).click().build().perform();;
		File Export2 = new File("C:\\Users\\praveenram.ravi\\Downloads\\ExportedEstimate(1).xlsx");
		if(Export2.exists())
		{
		System.out.println("Export is successful");
		}
		else
		{
		System.out.println("Export is not successful");
		}

		driver.quit();
		
		
	}

}
