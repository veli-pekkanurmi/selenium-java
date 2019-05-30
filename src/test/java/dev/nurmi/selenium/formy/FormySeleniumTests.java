package dev.nurmi.selenium.formy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormySeleniumTests {

	WebDriver driver;

	@Before
	public void initDriver() {
		this.driver = new ChromeDriver();
	}

	@After
	public void closeDriver() {
		this.driver.quit();
	}

	@Test
	public void KeyboardAndMouseInputTest() {
		this.driver.get("http://formy-project.herokuapp.com/keypress");

		WebElement name = driver.findElement(By.id("name"));

		name.click(); // make field actice
		name.sendKeys("Vellu");

		WebElement button = driver.findElement(By.id("button"));
		button.click();
	}

}
