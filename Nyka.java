package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Nyka {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		
		//Browser Set up
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
		
		//Mouse Over on Brands, Popular and then Loreal click
		Actions action = new Actions(driver);
		WebElement brands = driver.findElementByXPath("//a[text()='brands']");
		action.moveToElement(brands).perform();
		Thread.sleep(3000);
		WebElement Popular = driver.findElementByXPath("//a[text()='Popular']");  
		action.moveToElement(Popular).perform();
		
		action.moveToElement(driver.findElementByXPath("(//li[@class = 'brand-logo menu-links']//img)[5]")).click().build().perform();
		
		//Opens a new window
		//Get the Window handles in a set
		Set<String> set = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(set);
		
		//Switch to the newly opened Window
		driver.switchTo().window(windows.get(1));
		String newPageTitle = driver.getTitle();
		System.out.println(driver.getTitle());
		if (newPageTitle.contains("L'Oreal Paris"))
		{
			System.out.println("Navigated to L'Oreal Paris Page");
		}
		else
		{
			System.out.println("Try Again");
		}
		
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		driver.findElementByXPath("(//span[text()='popularity'])[1]").click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.findElementByXPath("(//span[text()='customer top rated'])[1]").click();
		System.out.println("Mathavan lam solradha kettu dhan vaanguvom!!!");
		
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		
		action.moveToElement(driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//span)[1]")).click().build().perform();
		//driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//span)[1]").click();
		System.out.println("Shampoo Clicked");System.out.println("Category Shampoo selected");
		
		//SELECT COLOR PROTECT SHAMPOO 
		driver.findElementByXPath("//div[@class='m-content__product-list__title']//span[contains(text(), 'Protect')]").click();
		System.out.println("Clicked on Color Protect Shampoo - Already naraicha mudikku edhukku ya color'u???");
		System.out.println("Navigated to Product Page - Product Page ku vandhuttom!!!");
		Thread.sleep(10000);
		Set<String> productWindowSet = driver.getWindowHandles();
		List<String> productWindow = new ArrayList<String>(productWindowSet);
		driver.switchTo().window(productWindow.get(2));
		
		driver.findElementByXPath("//span[contains(text(),'175')]").click();
		System.out.println("175 ML bottle selected - Oru maasathukku podhum");
		
		String PriceEvlo = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		System.out.println("Ivlo chinna Bottle ku ivlo price ah ????   " +PriceEvlo);
		Thread.sleep(3000);
		
		driver.findElementByXPath("//div[@class='pull-left']//button").click();
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		System.out.println("Enna panradhu??? Vaangi dhana aaganum???? ");
		
		
		Thread.sleep(10000);
		String grandTotal = driver.findElementByXPath("(//div[@class='value'])[2]").getText();
		System.out.println("Motha Purse um gaali...   "+grandTotal.replaceAll("\\D", ""));
		
		//14) Click Proceed
		driver.findElementByXPath("(//button[contains(@class,'btn full')])[2]").click();
		
		//16) Print the warning message (delay in shipment)
		System.out.println("Warning Message : "+driver.findElementByXPath("//div[@class='message']").getText());
		
		//17) Close all windows
		driver.quit();
		
		
		
	}

}
