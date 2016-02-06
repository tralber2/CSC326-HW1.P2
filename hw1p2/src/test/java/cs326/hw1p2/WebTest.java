package cs326.hw1p2;
/**
 * Selenium test cases for HW1.P2
 * 
 * @author Tyler Albert
 */
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

public class WebTest {
	private static WebDriver driver;

	@BeforeClass
	public static void setUp() throws Exception {
		// driver = new HtmlUnitDriver(true);
		// System.setProperty("webdriver.chrome.driver",
		// "/Users/gameweld/classes/326/HW1.P2/hw1p2/chromedriver");
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
	}
	/**
	 * Tests that the participation count for the Frustration of Software Developers
	 * is 55.
	 * @throws InterruptedException
	 */
	@Test
	public void participationCount() throws InterruptedException {
			this.driver.get("http://checkbox.io/studies.html");
			Thread.sleep(1000);
			String voteCount = null;

			List<WebElement> rowList = this.driver.findElements(By.xpath("//div[@class='row']"));

			for (WebElement rowElement : rowList) {
				List<WebElement> spanList = rowElement
						.findElements(By.xpath("div[@class='span8']/h3/span[@data-bind='text: name']"));
				for (WebElement spanElement : spanList) {
					if (spanElement.getText().equals("Frustration of Software Developers")) {
						WebElement voteElement = rowElement.findElement(By.xpath("div[@class='span4']/p/span[@data-bind='text: votes']"));
						voteCount = voteElement.getText();
					}
				}
			}
			assertTrue(voteCount.equals("55"));
	}
	/**
	 * Tests whether or not Google exists.
	 * @throws Exception
	 */
	@Test
	public void googleExists() throws Exception {
		this.driver.get("http://www.google.com");
		Thread.sleep(1000);
		assertEquals("Google", this.driver.getTitle());
	}
	/**
	 * Tests that iTrust is the number one link
	 * when searching NCSU iTrust
	 * @throws Exception
	 */
	@Test
	public void googleiTrustNumberOne() throws Exception {
		this.driver.get("http://www.google.com");
		Thread.sleep(1000);
		// this.driver.
		WebElement search = this.driver.findElement(By.name("q"));
		search.sendKeys("ncsu iTrust");
		search.sendKeys(Keys.RETURN);
		// this.driver.findElement(By.name("btnK")).click();

		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));

		List<WebElement> links = this.driver.findElements(By.xpath("//a[@data-href]"));
		int rank = 0;
		for (WebElement link : links) {
			if (link.getAttribute("data-href").equals("http://agile.csc.ncsu.edu/iTrust/wiki/")) {
				break;
			}

			rank++;
		}

		assertEquals(0, rank);
	}

	
	/**
	 * Tests that the total number of closed studies is 5.
	 * @throws InterruptedException
	 */
	@Test
	public void totalClosedStudies() throws InterruptedException {

		int numClosedStudies = 0;
		this.driver.get("http://checkbox.io/studies.html");
		Thread.sleep(1000);
		List<WebElement> statusList = this.driver.findElements(By.xpath("//a[@class='status']"));
		for (WebElement statusElement : statusList) {
			if (statusElement.getText().equals("STATUS: CLOSED")) {
				numClosedStudies++;
			}
		}
		assertEquals(numClosedStudies, 5);
	}
	/**
	 * Tests that surveys that can be participated in have a clickable button.
	 * @throws InterruptedException
	 */
	@Test
	public void canParticipateInOpenStudy() throws InterruptedException {
		int numClosedStudies = 0;
		this.driver.get("http://checkbox.io/studies.html");
		Thread.sleep(1000);
		List<WebElement> statusList = this.driver.findElements(By.xpath("//a[@class='status']"));
		for (WebElement statusElement : statusList) {
			WebElement participateButton = null;
			if (statusElement.getText().equals("STATUS: OPEN")) {
				Boolean canParticipate = true;
				try {
					participateButton = statusElement.findElement(By.xpath("//button"));
					WebDriverWait wait = new WebDriverWait(this.driver, 5);
					wait.until(ExpectedConditions.elementToBeClickable(participateButton));
				} catch (Exception e) {
					canParticipate = false;
				}
				assertTrue(canParticipate);
			}
		}
	}
	/**
	 * Tests that on a survey, text can be entered.
	 * @throws InterruptedException
	 */
	@Test
	public void canEnterTextInStudy() throws InterruptedException {
		this.driver.get("http://checkbox.io/studies/?id=569e667f12101f8a12000001");
		Thread.sleep(1000);
		WebElement textBox = this.driver.findElement(By.xpath("//textArea"));
		assertTrue(textBox.getAttribute("value").equals(""));
		textBox.sendKeys("success");
		assertTrue(textBox.getAttribute("value").equals("success"));

	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.close();
	}
}
