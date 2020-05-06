package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Honda {
	public static RemoteWebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		driver = new ChromeDriver(options);
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//Browser Set up
		
		
		
		driver.get("https://www.honda2wheelersindia.com/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
		System.out.println("User enters Honda Home page");
		driver.findElementByXPath("//button[@class='close']").click();
		
		System.out.println("Pop out closed");
		
		driver.findElementByLinkText("Scooter").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']")));
		
		driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']").click();
		
		System.out.println("Dio Selected");
		
		driver.findElementByLinkText("Specifications").click();
		System.out.println("Specifications for DIO displayed");

		Thread.sleep(10000);
		WebElement Engine = driver.findElementByXPath("//a[@name='2']");
		action.moveToElement(Engine).perform();
		
		System.out.println("Engine specifications selected");
		
		WebElement webele1 = driver.findElementByXPath("//span[text()='Displacement']/following::span[1]");
		String Diodisplacement1 = webele1.getText().replaceAll("//D", "").substring(0, 3);
		System.out.println("Diodisplacement1 " + Diodisplacement1);
		double DioDisplacement = Double.parseDouble(Diodisplacement1);
		System.out.println("Displacement of Dio Scooter is " +DioDisplacement);
		
		//to get Activa's details
		
		driver.findElementByLinkText("Scooter").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']")));
		
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();
		
		System.out.println("Activa Selected");
		
		driver.findElementByLinkText("Specifications").click();
		System.out.println("Specifications for Activa displayed");

		Thread.sleep(10000);
		WebElement ActivaEngine = driver.findElementByXPath("//a[@name='4']");
		action.moveToElement(ActivaEngine).perform();
		
		System.out.println("Activa Engine specifications selected");
		
		WebElement webele2 = driver.findElementByXPath("//span[text()='Displacement']/following::span[1]");
		System.out.println(webele2.getText());
		String ActivaDisplacement1 = webele2.getText().replaceAll("//D", "").substring(0, 3);
		System.out.println("Activa Displacement is "+ActivaDisplacement1);
		
		double ActivaDisplacement = Double.parseDouble(ActivaDisplacement1);
		System.out.println("Displacement of Activa Scooter is " +ActivaDisplacement);
		
		//compare the displacement
		if(DioDisplacement>ActivaDisplacement)
		{
			System.out.println("Dio displacement is Better");
		}
		else
		{
			System.out.println("Activa Displacement is Better");
		}

		// Click FAQ from Menu
		driver.findElementByLinkText("FAQ").click();
				
		// Click Activa 125 BS-VI under Browse By Product
		WebElement eleBSVI = driver.findElementByLinkText("Activa 125 BS-VI");
		wait.until(ExpectedConditions.visibilityOf(eleBSVI));
		eleBSVI.click();
				
		// Click  Vehicle Price 
		WebElement eleVprice = driver.findElementByXPath("//a[text()=' Vehicle Price']");
		wait.until(ExpectedConditions.visibilityOf(eleVprice));
		eleVprice.click();
				
		// Make sure Activa 125 BS-VI selected and click submit
				
		WebElement eleScooter = driver.findElementById("ModelID6");
		wait.until(ExpectedConditions.visibilityOf(eleScooter));
		Select dropdown = new Select(eleScooter);
		WebElement firstSelectedOption = dropdown.getFirstSelectedOption();
		String model = firstSelectedOption.getText();
				
		if(model.contains("Activa 125 BS-VI"))
		driver.findElementByXPath("//button[@id='submit6']").click();
				
		// click the price link
		WebElement priceLink = driver.findElementByLinkText("Click here to know the price of Activa 125 BS-VI.");
		wait.until(ExpectedConditions.elementToBeClickable(priceLink));
		priceLink.click();
				
		// Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1));
				
		WebElement eleState = driver.findElementById("StateID");
		Select state = new Select(eleState);
		state.selectByVisibleText("Tamil Nadu");
				
		WebElement eleCity = driver.findElementById("CityID");
		wait.until(ExpectedConditions.elementToBeClickable(eleCity));
		Select city = new Select(eleCity);
		city.selectByVisibleText("Chennai");		
				
		// Click Search
		WebElement eleSearch = driver.findElementByXPath("//button[text()='Search']");
		wait.until(ExpectedConditions.elementToBeClickable(eleSearch));
		eleSearch.click();
				
		// Print all the 3 models and their prices
		WebElement table = driver.findElementById("gvshow");
		List<WebElement> rows = table.findElements(By.tagName("td"));

		for (WebElement eachmodel : rows) 
		{
		System.out.println(eachmodel.getText());
		}
				
		// Close all Browsers
		driver.quit();;
	}

}
