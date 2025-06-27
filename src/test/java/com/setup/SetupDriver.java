package com.setup;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetupDriver {
	public static ChromeOptions options;
	public static WebDriver driver;
	
	
	public static WebDriver chromeDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
		String userProfile= "C:\\Users\\ROHARISH\\AppData\\Local\\Google\\Chrome\\User Data\\";
        options = new ChromeOptions();
        options.addArguments("--user-data-dir="+userProfile);
		options.addArguments("--profile-directory=Default");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
		
        return driver;
    }
	public static void waitForElement(WebElement element,int time_in_sec)
	{
		 WebDriverWait wait = new WebDriverWait(driver, time_in_sec);
		 wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void waitForElementVis(WebElement element,int time_in_sec)
	{
		 WebDriverWait wait = new WebDriverWait(driver, time_in_sec);
		 wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void loadurl() {
		driver.manage().window().maximize();
		System.out.println("Launching Chrome Browser");
		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
		driver.get("https://www.ixigo.com/");
	}
	public static void teardown() throws Exception {
    	System.out.println("i m in teardown");
    	Thread.sleep(5000);
        driver.close();
        driver.quit();
    }
}
