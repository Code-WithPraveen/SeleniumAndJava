package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ajio {

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
		
		
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(20000);
		
		//2) Enter Bags in the Search field and Select Bags in Women Handbags
		WebElement search = driver.findElementByXPath("//input[@placeholder = 'Search AJIO']");
		action.click(search).build().perform();
		search.sendKeys("Bags");
		WebElement WomenBags = driver.findElementByXPath("(//span[text() = 'Women Handbags'])[1]");
		wait.until(ExpectedConditions.elementToBeClickable(WomenBags));
		action.click(WomenBags).build().perform();
		
		//System.out.println("Bags are searched");
		Thread.sleep(10000);
		
		//3) Click on five grid and Select SORT BY as "What's New"
		WebElement fiveGrid = driver.findElementByXPath("//div[@class = 'five-grid']");
		action.click(fiveGrid).build().perform();
		Thread.sleep(5000);
		
		//4) Enter Price Range Min as 2000 and Max as 5000
		driver.findElementByXPath("//span[text() = 'price']").click();
		
		WebElement minPrice = driver.findElementByXPath("//input[@id = 'minPrice']");
		WebElement maxPrice = driver.findElementByXPath("//input[@id = 'maxPrice']");
		
		wait.until(ExpectedConditions.visibilityOf(minPrice));
		
		minPrice.sendKeys("2000");
		maxPrice.sendKeys("5000");
		
		driver.findElementByXPath("(//button[@type = 'submit'])[2]").click();
		//System.out.println("Product filtered with Price range");
		
		//5) Click on the product "Puma Ferrari LS Shoulder Bag"
		
		driver.findElementByXPath("//img[contains(@alt,'Ferrari LS Shoulder Bag')]").click();
		//System.out.println("Puma Bag selected");
		Thread.sleep(10000);
		
		Set<String> windows = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(windows);
		driver.switchTo().window(windowsList.get(1));
		System.out.println("User Navigated to new Window");
		System.out.println(driver.getTitle());
		
		
		//6) Verify the Coupon code for the price above 2690 is applicable for your product, 
		//if applicable the get the Coupon Code and Calculate the discount price for the coupon
		WebElement eleBagPrice = driver.findElementByXPath("//div[@class='prod-sp']");
		String BagPriceStr = eleBagPrice.getText();
		Integer BagPrice = Integer.parseInt(BagPriceStr.replaceAll("\\D", ""));
		System.out.println("Bag Price is "+ BagPrice);
		
		WebElement elePromoPrice = driver.findElementByXPath("//div[@class='promo-desc']");
		String strPromoPrice = elePromoPrice.getText();
		//System.out.println(strPromoPrice);
		Integer DiscountPercentage = Integer.parseInt(strPromoPrice.replaceAll("\\D", "").substring(0, 2)); 
		Integer PromoPrice = Integer.parseInt(strPromoPrice.replaceAll("\\D", "").substring(2, 6)); //Because replacing all the non integers give the percentage and price alone. Discount can never be 3 digits - 100%, hence always this code gives a 6 digit number
		
		System.out.println("Promotional Price Limit is " +PromoPrice);
		//System.out.println("Discount Percentage is " +DiscountPercentage);
		int intPriceBeforeCheckout = 0;
		if (BagPrice>PromoPrice) {
			System.out.println("Product Applicable for Discount");
			double DiscountedPrice = BagPrice - (DiscountPercentage*BagPrice/100);
			intPriceBeforeCheckout= (int)Math.round(DiscountedPrice);
			System.out.println("Bag Price after Discount is " + intPriceBeforeCheckout);
			
			}
		else
		{
			System.out.println("Product Not Applicable for Discount");
		}
		
		
		//7) Check the availability of the product for pincode 560043, 
		//print the expected delivery date if it is available
		WebElement enterPincode = driver.findElementByXPath("//span[contains(text(),'Enter pin-code')]");
		action.click(enterPincode).build().perform();
		
		WebElement pincodePopup = driver.findElementByXPath("//input[@name='pincode']");
		wait.until(ExpectedConditions.elementToBeClickable(pincodePopup));
		
		pincodePopup.sendKeys("560043", Keys.ENTER);
		
		String EstimatedDelivery = driver.findElementByXPath("//div[contains(@class,'edd-pincode-msg-details')]").getText();
		System.out.println("Estimated Delivery date is " +EstimatedDelivery);
		
		
		//8) Click on Other Informations under Product Details and 
		//Print the Customer Care address, phone and email
		WebElement moreInformation = driver.findElementByXPath("//div[@class='other-info-toggle']");
		moreInformation.click();
		//System.out.println("More information about the Product is displayed");
		
		WebElement CustomerCare = driver.findElementByXPath("//span[text()='Customer Care Address']//following::span[@class='other-info']");
		System.out.println(CustomerCare.getText());
		
		
		//9) Click on ADD TO BAG and then GO TO BAG
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		//System.out.println("Product Added to Bag");
		Thread.sleep(10000);
		WebElement eleGoToBag = driver.findElementByXPath("//span[text()='GO TO BAG']");
		wait.until(ExpectedConditions.elementToBeClickable(eleGoToBag));
		
		action.click(eleGoToBag).build().perform();
		//System.out.println("User navigates to Cart page");
		
		//10) Check the Order Total before apply coupon
		WebElement eleOrderTotal = driver.findElementByXPath("//span[text()='Order Total']//following-sibling::span");
		String strOrderTotal = eleOrderTotal.getText();
		
		Double doubleOrderTotal = Double.parseDouble(strOrderTotal.replaceAll("\\D", ""));
		int OrderTotal = (int) Math.round(doubleOrderTotal);
		System.out.println(doubleOrderTotal);
		System.out.println("Order Total of the Product is "+OrderTotal);
		
		//11) Enter Coupon Code and Click Apply
		WebElement eleCouponCode = driver.findElementByXPath("//input[@id='EPIC']");
		action.click(eleCouponCode).build().perform();
		
		driver.findElementByXPath("//button[text()='Apply']").click();
		//System.out.println("Coupon Code Applied");
		
		//12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary 
		//and the matches the amount calculated in Product details
		WebElement elePriceAfterDiscount = driver.findElementByXPath("//div[contains(@class,'net-price best-price-strip')]");
		String strPriceAfterDiscount = elePriceAfterDiscount.getText();
		System.out.println(strPriceAfterDiscount);
		Double PriceAfterDiscount = Double.parseDouble(strPriceAfterDiscount.replaceAll("\\D", ""));
		int intPriceAfterCheckout = (int) Math.round(PriceAfterDiscount);
		System.out.println("Price After Discount is " +intPriceAfterCheckout);
		
		if(intPriceAfterCheckout == intPriceBeforeCheckout)
		{
			System.out.println("Price Before Checkout and Price After Checkout is a match");
		}
		
		//13) Click on Delete and Delete the item from Bag

		driver.findElementByXPath("//div[text()='delete-btn'").click();
		
		//14) Close all the browsers
		driver.quit();


	}

}
