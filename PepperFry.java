package testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PepperFry {

	public static RemoteWebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {


		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		
		driver = new ChromeDriver(options);
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		// Get the URL
		driver.get("https://www.pepperfry.com/");
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		builder.moveToElement(driver.findElementByLinkText("Furniture")).build().perform();
		builder.click(driver.findElementByLinkText("Office Chairs")).build().perform(); 
		Thread.sleep(5000);
		
		// Setting the Height filter 
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").clear(); 
		driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").sendKeys("50", Keys.ENTER); 
				
		Thread.sleep(8000);
		
		try {
			driver.findElementByXPath("//div[@id='regPopUp']//a[@class='popup-close']").click(); 
			System.out.println("Login popup closed.");
		} catch (Exception e) {
			System.out.println("Login popup not found.");
			e.printStackTrace();
		}
		
		// Adding to Wishlist 
		driver.findElementByXPath("//a[contains(@data-productname,'Poise Executive Chair in Black Colour')]").click(); 
		System.out.println("Poise Executive Chair in Black Colour added to wishlist");		
		
		//Mouse hover on Homeware and Click Pressure Cookers under Cookware
		WebElement eleHomeware = driver.findElementByLinkText("Homeware");
		builder.moveToElement(eleHomeware).build().perform();
				
		WebElement elePressureCooker = driver.findElementByLinkText("Pressure Cookers");
		wait.until(ExpectedConditions.elementToBeClickable(elePressureCooker));
		elePressureCooker.click();
				
		// Select Prestige as Brand
		WebElement elePrestige = driver.findElementByXPath("//label[@for='brandsnamePrestige']");
		wait.until(ExpectedConditions.elementToBeClickable(elePrestige));
		elePrestige.click();
				
		// Select Capacity as 1-3 Ltr
		Thread.sleep(2000);
		WebElement eleLtr = driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='capacity_db1_Ltr_-_3_Ltr']")));
		eleLtr.click();
				
		// Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		Thread.sleep(3000);
		WebElement eleWish2 = driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")));
		eleWish2.click();
						
		// Verify the number of items in Wishlist
		Thread.sleep(2000);
		WebElement WishCount = driver.findElementByXPath("(//span[@class='count_alert'])[2]");
		wait.until(ExpectedConditions.visibilityOf(WishCount));
		String count = WishCount.getText();
		System.out.println("Number of items in the Wishlist: "+count);
				
		// Navigate to Wishlist
		driver.findElementByXPath("//a[contains(@class,'pf-icon pf-icon-heart')]").click();
		Thread.sleep(2000);
				
		// Move Pressure Cooker only to Cart from Wishlist
		JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].scrollIntoView();",driver.findElementByXPath("//div[@id='cart_item_holder']//a[contains(text(),'Cooker')]/following::div[1]//a[contains(text(),'Add to Cart')]"));
				WebElement eleCooker = driver.findElementByXPath("//div[@id='cart_item_holder']//a[contains(text(),'Cooker')]/following::div[1]//a[contains(text(),'Add to Cart')]");
				wait.until(ExpectedConditions.visibilityOf(eleCooker));
				eleCooker.click();
				Thread.sleep(3000);
				
				//13) Check for the availability for Pincode 600128
				driver.findElementByClassName("srvc_pin_text").sendKeys("600117");
				Thread.sleep(2000);
				driver.findElementByClassName("check_available").click();
				
				//14) Click Proceed to Pay Securely
				driver.findElementByClassName("proceed_cta").click();
				
				//15 Click Place Order
				WebElement eleOrder = driver.findElementByLinkText("PLACE ORDER");
				wait.until(ExpectedConditions.visibilityOf(eleOrder));
				eleOrder.click();
				
				//16) Capture the screenshot of the item under Order Item
				WebElement eleExpand = driver.findElementByXPath("(//div[@class='nCheckout__accrodian-header-right']//span)[1]");
				wait.until(ExpectedConditions.visibilityOf(eleExpand));
				eleExpand.click();
				
				File screenShot = driver.findElementByXPath("//li[@data-slick-index='0']").getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenShot, new File("./snaps/image.png"));
				
				//17) Close the browser
				driver.close();
		
	}

}
