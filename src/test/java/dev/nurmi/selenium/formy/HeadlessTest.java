package dev.nurmi.selenium.formy;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessTest {

	WebDriver driver;

	@Before
	public void initDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		this.driver = new ChromeDriver(chromeOptions);
	}

	@After
	public void closeDriver() throws InterruptedException {
		this.driver.quit();
	}

	@Test
	public void allLinksTest() {

		this.driver.get("http://formy-project.herokuapp.com");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.forEach(x -> System.out.println(x.getText()));

	}

}
