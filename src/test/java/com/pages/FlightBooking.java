package com.pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.setup.*;

import io.cucumber.java.Scenario;

public class FlightBooking {
	WebDriver driver = SetupDriver.chromeDriver();
	Actions actions;
	
	@FindBy(xpath = "//button[text()='Round Trip']")
	WebElement round_trip_button;
	
	@FindBy(xpath = "//p[text()='From']")
	WebElement src_field;
	
	@FindBy(xpath = "//label[text()='From']/following-sibling::input")
	WebElement src_input;
	
	@FindBy(xpath = "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/li")
	WebElement first_sug_src;
	
	@FindBy(xpath = "//label[text()='To']/following-sibling::input")
	WebElement dest_input;
	
	@FindBy(xpath = "/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[3]/div[1]/li")
	WebElement first_sug_dest;
	
	@FindBy(xpath = "//button[text()='Done']")
	WebElement done_button;
	@FindBy(xpath = "//input[@value='hotelUpsell']")
	WebElement hotelCheck;
	
	@FindBy(xpath = "//button[text()='Search']")
	WebElement search_button;
	
	@FindBy(xpath = "//div[@class='flex flex-col']")
	WebElement oneWayFlights;
	
	@FindBy(xpath = "//h6[text()='No flights found!']")
	WebElement isFlightShown;
	
	@FindBy(xpath = "//li[//p[text()='Non-stop']]//span/input")
	WebElement nonStopCheck;
	
//	@FindBy(xpath = "//div[@class='w-1/2 flex flex-col']")
//	List<WebElement> Flights;
	
	@FindBy(xpath = "//p[@class='body-sm text-warning-moderate']")
	WebElement passengerSection;
	
	@FindBy(xpath = "//li/div/div[1]/span/input")
	List<WebElement> passengerList;
	
	@FindBy(xpath = "//button[text()='Continue']")
	WebElement continue_button;
	
	@FindBy(xpath = "//button[text()='No, Thanks']")
	WebElement no_thanks_button;
	
	@FindBy(xpath = "//span[@class='flex gap-5 items-center']")
	WebElement skip_button;
	
	FileInputStream FRead;
	 
	FileOutputStream FWrite;
 
	XSSFWorkbook wb;
 
	XSSFSheet sh;
 
	public FlightBooking() {
        //this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	public void launchApplication() {
		SetupDriver.loadurl();
	}
	public void selectTripType(String s) {
		String trip_type = "//button[text()='" +s+ "']";
		driver.findElement(By.xpath(trip_type)).click();
		hotelCheck.click();
	}
	public void clickAndEnterSrc(String s)
	{
		src_field.click();
		src_input.sendKeys(s);
		SetupDriver.waitForElement(first_sug_src, 5);
		first_sug_src.click();
	}
	public void clickAndEnterDest(String s)
	{
		dest_input.sendKeys(s);
		//Thread.sleep(2000);
		SetupDriver.waitForElement(first_sug_dest, 5);
		first_sug_dest.click();
	}
	public void enterDate(String s) {
		String input_date = "//abbr[contains(@aria-label,'"+s+"')]";
		SetupDriver.waitForElement(driver.findElement(By.xpath(input_date)), 0);
		driver.findElement(By.xpath(input_date)).click();
//		Thread.sleep(1000);
	}
	public void selectNoOfPassengers(String s)
	{
		String p_no = "//button[@data-testid='"+ s +"']";
		SetupDriver.waitForElement(driver.findElement(By.xpath(p_no)), 3);
		driver.findElement(By.xpath(p_no)).click();
	}
	public void clickSearch() throws InterruptedException {
		search_button.click();
//		Thread.sleep(3000);
	}
	public void enterDataFromExcel() throws Exception {
		FRead=new FileInputStream("C:\\Users\\roharish\\Desktop\\ixigo_input.xlsx");
		
		wb=new XSSFWorkbook(FRead);
		sh= wb.getSheet("Sheet1");
		
		String src = sh.getRow(1).getCell(0).getStringCellValue();
		String dest = sh.getRow(1).getCell(1).getStringCellValue();
		String t_date = sh.getRow(1).getCell(2).getStringCellValue();
		
		clickAndEnterSrc(src);
		clickAndEnterDest(dest);
		enterDate(t_date);
	}
	public void selectFilter(String s)
	{
		if(s.equals("Non-Stop")) {
			SetupDriver.waitForElement(nonStopCheck, 5);
			nonStopCheck.click();
		}
	}
	
	public void displayFlights() {
		try {
//			WebElement isFlightShown = driver.findElement(By.xpath("//h6[text()='No flights found!']"));
			SetupDriver.waitForElementVis(isFlightShown, 3);
			if(isFlightShown.isDisplayed()) {
				System.out.println(isFlightShown.getText());
				return;
			}
		}catch (Exception e) {
			System.out.println("Following flights are found: ");
		}
		
//		WebElement oneWayFlights = driver.findElement(By.xpath("//div[@class='flex flex-col']"));
		SetupDriver.waitForElement(oneWayFlights, 10);
		List<WebElement> flightNames = oneWayFlights.findElements(By.xpath("//p[@class='body-md text-primary truncate max-w-[125px] airlineTruncate font-medium']"));
		List<WebElement> flightNumbers = oneWayFlights.findElements(By.xpath("//p[@class='body-sm text-secondary truncate max-w-[115px]']"));
		List<WebElement> flightPrices = oneWayFlights.findElements(By.xpath("//h5[@data-testid='pricing']"));
		for(int i=0;i<flightNames.size();i++)
		{
			System.out.println(flightNames.get(i).getText() + " " + flightNumbers.get(i).getText() + " " + flightPrices.get(i).getText());
		}
	}
	public void displayRoundTripFlights() {
		//System.out.println(Flights.size());
		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> Flights = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='w-1/2 flex flex-col']")));
		WebElement leftFlights = Flights.get(0);
		WebElement rightFlights = Flights.get(1);
		
		List<WebElement> flightNames1 = leftFlights.findElements(By.xpath(".//p[@class='body-sm body-sm']"));
		List<WebElement> flightPrices1 = leftFlights.findElements(By.xpath(".//h5[@data-testid='pricing']"));
		System.out.println("--Onset journey : --");
		for(int i=0;i<flightNames1.size();i++)
		{
			System.out.println(flightNames1.get(i).getText() +" "+ flightPrices1.get(i).getText());
		}
		
		List<WebElement> flightNames2 = rightFlights.findElements(By.xpath(".//p[@class='body-sm body-sm']"));
		List<WebElement> flightPrices2 = rightFlights.findElements(By.xpath(".//h5[@data-testid='pricing']"));
		System.out.println("--Return journey : --");
		for(int i=0;i<flightNames2.size();i++)
		{
			System.out.println(flightNames2.get(i).getText()+ " " + flightPrices2.get(i).getText());
		}
	}
	public void storeResultinExcel() throws Exception {
		FWrite=new FileOutputStream("C:\\Users\\roharish\\Desktop\\ixigo_input.xlsx");
		Thread.sleep(2000);
//		WebElement oneWayFlights = driver.findElement(By.xpath("//div[@class='flex flex-col']"));
		SetupDriver.waitForElementVis(oneWayFlights, 10);
		List<WebElement> flightNames = oneWayFlights.findElements(By.xpath("//p[@class='body-md text-primary truncate max-w-[125px] airlineTruncate font-medium']"));
		List<WebElement> flightNumbers = oneWayFlights.findElements(By.xpath("//p[@class='body-sm text-secondary truncate max-w-[115px]']"));
		List<WebElement> flightPrices = oneWayFlights.findElements(By.xpath("//h5[@data-testid='pricing']"));
		for(int i=0;i<flightNames.size();i++)
		{
//			String flight_result = flightNames.get(i).getText() + " " + flightNumbers.get(i).getText() + " " + flightPrices.get(i).getText();
			sh.getRow(1).createCell(3).setCellValue(flightNames.get(i).getText());
			sh.getRow(1).createCell(4).setCellValue(flightNumbers.get(i).getText());
			sh.getRow(1).createCell(5).setCellValue(flightPrices.get(i).getText());
			
		}
		wb.write(FWrite);
		
	}
	public void selectFlight() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		List<WebElement> bookButtons=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[text()='Book']")));
		System.out.println(bookButtons.size());
//		List<WebElement> bookButtons = driver.findElements(By.xpath("//button[text()='Book']"));
//		SetupDriver.waitForElement(bookButtons.get(0), 5);
		bookButtons.get(0).click();
//		Thread.sleep(3000);
	}
	public void enterPassengerDetails() throws Exception {
//		Thread.sleep(500);
//		actions = new Actions(driver);
//		actions.sendKeys(Keys.PAGE_UP).perform();
//		actions.sendKeys(Keys.PAGE_DOWN).perform();
//		actions.sendKeys(Keys.PAGE_DOWN).perform();
//		Thread.sleep(500);
		SetupDriver.waitForElementVis(passengerSection, 5);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", passengerSection);
        Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, 5);
//		List<WebElement> passengerList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li/div/div[1]/span/input")));
//		List<WebElement> passengerList = driver.findElements(By.xpath("//li/div/div[1]/span/input"));
//        SetupDriver.waitForElement(passengerList.get(0), 5);
		passengerList.get(0).click();
	}
	public void passengerDetailForRoundTrip() throws InterruptedException {
		actions = new Actions(driver);
		actions.sendKeys(Keys.PAGE_UP).perform();
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(500);
		
		List<WebElement> passengerList = driver.findElements(By.xpath("//li/div/div[1]/span/input"));
		passengerList.get(0).click();
	}
	public void enterMultiplePassengerDetail(Integer n) throws InterruptedException {
//		actions = new Actions(driver);
//		actions.sendKeys(Keys.PAGE_UP).perform();
//		actions.sendKeys(Keys.PAGE_DOWN).perform();
//		actions.sendKeys(Keys.PAGE_DOWN).perform();
//		Thread.sleep(500);
		SetupDriver.waitForElementVis(passengerSection, 5);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", passengerSection);
//		List<WebElement> passengerList = driver.findElements(By.xpath("//li/div/div[1]/span/input"));
		for(int i=0;i<n;i++)
		{
			passengerList.get(i).click();
		}
	}
	public void goToPaymentPage() throws Exception
	{
		try {
			SetupDriver.waitForElement(continue_button, 4);
			if(continue_button.isDisplayed())
				continue_button.click();
//			Thread.sleep(1000);
			SetupDriver.waitForElement(no_thanks_button, 2);
			if(no_thanks_button.isDisplayed())
				no_thanks_button.click();
//			Thread.sleep(1000);
			SetupDriver.waitForElement(skip_button, 4);
			if(skip_button.isDisplayed())
				skip_button.click();
		}catch (Exception e) {
			System.out.println("button not displayed.");
		}
		Thread.sleep(2000);
//		SetupDriver.teardown();
	}
	public void goToPaymentPage2() throws Exception
	{
		try {
			SetupDriver.waitForElement(continue_button, 2);
			if(continue_button.isDisplayed())
				continue_button.click();
//			Thread.sleep(1000);
			SetupDriver.waitForElement(no_thanks_button, 2);
			if(no_thanks_button.isDisplayed())
				no_thanks_button.click();
//			Thread.sleep(1000);
			SetupDriver.waitForElement(skip_button, 2);
			if(skip_button.isDisplayed())
				skip_button.click();
		}catch (Exception e) {
			System.out.println("button not displayed.");
		}
		Thread.sleep(2000);
//		SetupDriver.teardown();
	}
	public void addScreenshot_failed(Scenario scenario){

		//validate if scenario has failed
		if(scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "image"); 
		}
		
	}
}
