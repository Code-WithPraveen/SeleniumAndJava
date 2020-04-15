package testCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Myntra {

	public static void main(String[] args) throws InterruptedException {
		
		//Browser Set up
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		
		//mouse over option on Women and select Jackets and coats
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[@data-group='women']")).perform();
		driver.findElementByLinkText("Jackets & Coats").click();
		
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
		
		//To disable the notification
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		//Getting the count of all Products
		String text = driver.findElementByClassName("title-count").getText();
		String text1 = text.replaceAll("\\D", "");
		int FinalCount = Integer.parseInt(text1);
		
		//Displaying total count
		System.out.println("************************************************************************************************" );
		System.out.println("********************** Total Count of dresses in this page is " +FinalCount+ " *************************");
		System.out.println("************************************************************************************************" );
		
		//Conversion of string to Integer
		String JacketsCounttext = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String CoatsCounttext = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		
		int JacketsCount = Integer.parseInt(JacketsCounttext.replaceAll("\\D", ""));
		int CoatsCount = Integer.parseInt(CoatsCounttext.replaceAll("\\D", ""));
		
		//Displaying the Jackets Count and Coats Count
		System.out.println("************************************************************************************************" );
		System.out.println("************************** Total Count of Jackets in this page is " +JacketsCount+ " *************************");
		System.out.println("************************** Total Count of Coats in this page is " +CoatsCount+ "    *************************");
		System.out.println("************************************************************************************************" );
		
		//Validating the total count
		if(FinalCount==(JacketsCount+CoatsCount))
		{
			System.out.println("************* Number of Jackets and Number of Coats sum up the Final Count**********************");
		}
		else
		{
			System.out.println("********************** Number of Jackets and Number of Coats doesn't sum up the Final Count****************************");
		}
		
		//Select Coats check box
		driver.findElementByXPath("//Label[text()='Coats']").click();
		Thread.sleep(5000);
		System.out.println("******************************** COATS SELECTED ************************************************");
		
		String numberOfCoats = driver.findElementByClassName("title-count").getText();
		
		int FinalNumberOfCoats = Integer.parseInt(numberOfCoats.replaceAll("\\D", ""));
		
		System.out.println("********************************* NUMBER OF COATS DISPLAYED IS "+FinalNumberOfCoats+ " *****************************");
		
		//Selecting More Brands and selecting MANGO
		driver.findElementByXPath("//div[@class='brand-more']").click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
		driver.findElementByXPath("(//input[@value='MANGO']/parent::label)[2]").click();
		driver.findElementByXPath("//span[contains(@class,'FilterDirectory-close')]").click();
		Thread.sleep(10000);
		
		int count=0;
		List<WebElement> listOfBrandName = driver.findElementsByXPath("//div[@class='product-productMetaInfo']//h3[1]");
		for (WebElement brand : listOfBrandName) {
			String brandName = brand.getText();
			if (brandName.equalsIgnoreCase("MANGO")) {
				count=count+1;
			} 
		}
		if (count==listOfBrandName.size()) {
			System.out.println("******************************* ALL Products are of Brand MANGO   ******************************");
		} else {
			System.out.println("Not MANGO Products");
		}
		
		Actions sort = new Actions(driver);
		WebElement sortDropDown = driver.findElementByClassName("sort-sortBy");
		sort.moveToElement(sortDropDown).perform();
		driver.findElementByXPath("//label[text()='Better Discount']").click();
		
		//Sort by Better Discount
		WebElement ele2 = driver.findElementByXPath("//div[@class='sort-sortBy']");
		action.moveToElement(ele2).perform();
		driver.findElementByXPath("//ul[@class='sort-list']/li[3]/label").click();
		
		//Find the price of first displayed item
		List<WebElement> allitemsprice = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		String firstitemprice = allitemsprice.get(0).getText();
		System.out.println("*************** Price of first displayed coat is: " + firstitemprice+ "****************");
		
		//Mouse over on size of the first item
		WebElement ele3 = driver.findElementByXPath("//span[@class='product-discountedPrice']");
		action.moveToElement(ele3).perform();
		
		//Click on WishList Now and ensure being re-directed to login
		driver.findElementByXPath("//div[contains(@class,'product-actions')]/span/span").click();
		String logintitle = driver.getTitle();
		System.out.println("Yaaaaaayyyyyyyyy..... Back to login page: "+logintitle+ "***********************************");
		
		//Close Browser
		driver.close();
		
		
	}

}
