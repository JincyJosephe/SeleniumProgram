package javalearning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SearchTest {
	
	public String baseUrl = "https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_csharp_search/862b0faa506b8487c25a3384cfde8af4/static/attachments/reference_page.html";
	String driverPath = "/Users/jincyjoseph/Downloads/chromedriver";
	public WebDriver driver;
	
	@BeforeTest
	public void test_launchBrowser() {
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}
	
	@Test
	public void test_existenceOfInputAndSearch() {
		Boolean input = driver.findElement(By.id("search-input")).isDisplayed();
		System.out.println("Search Input is displayed");
		Boolean search = driver.findElement(By.id("search-button")).isDisplayed();
		System.out.println("Search Button is displayed");
	}
	
	@Test
	public void test_emptyQueryIsNotAllowed() {
		WebElement inputKeyword = driver.findElement(By.id("search-input"));
		inputKeyword.clear();
		inputKeyword.sendKeys("");
		WebElement searchButton = driver.findElement(By.id("search-button"));
		searchButton.click();
		
		String actual_msg=driver.findElement(By.id("error-empty-query")).getText();
		String expect="Provide some query";
		Assert.assertEquals(actual_msg, expect);
	}
		
	@AfterTest
	public void test_terminateBrowser() {
		driver.close();
	}

}
