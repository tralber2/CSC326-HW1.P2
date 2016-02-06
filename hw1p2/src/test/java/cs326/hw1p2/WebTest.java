package cs326.hw1p2;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class WebTest
{
	private static WebDriver driver;

    @BeforeClass
    public static void setUp() throws Exception 
    {
        //driver = new HtmlUnitDriver(true);
        //System.setProperty("webdriver.chrome.driver", "/Users/gameweld/classes/326/HW1.P2/hw1p2/chromedriver");
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }
	
    
	@Test
	public void googleExists() throws Exception
	{
		this.driver.get("http://www.google.com");
        assertEquals("Google", this.driver.getTitle());		
	}
	
	@Test
	public void googleiTrustNumberOne() throws Exception
	{
		this.driver.get("http://www.google.com");
		//this.driver.
		WebElement search = this.driver.findElement(By.name("q"));
		search.sendKeys("ncsu iTrust");
		search.sendKeys(Keys.RETURN);
		//this.driver.findElement(By.name("btnK")).click();
		
		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));

		List<WebElement> links = this.driver.findElements(By.xpath("//a[@data-href]"));
		int rank = 0;
		for( WebElement link : links )
		{
			if( link.getAttribute("data-href").equals("http://agile.csc.ncsu.edu/iTrust/wiki/"))
			{
				break;  
			}  
			
			rank++;
		}
		
		assertEquals(0, rank);
	}
	@Test
	public void participationCount() {
		this.driver.get("http://checkbox.io/studies.html");
		WebElement search = this.driver.findElement(By.xpath("//span[@class='backers']))
	}
	
	@Test
	public void totalClosedStudies() {
		
	}
	
	@Test
	public void canParticipateInOpenStudy() {
		
	}
	
	@Test
	public void canEnterTextInStudy() {
//		try {
//		this.driver.get("http://checkbox.io/studies/?id=569e667f12101f8a12000001");
//		WebElement search = this.driver.findElement(By.name("q"));
//		search.sendKeys("ncsu iTrust");
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}
	@AfterClass
    public static void  tearDown() throws Exception
    {
        driver.close();
    }
}
