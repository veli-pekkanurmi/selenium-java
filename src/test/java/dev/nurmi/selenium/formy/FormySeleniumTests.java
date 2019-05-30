package dev.nurmi.selenium.formy;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FormySeleniumTests {

	WebDriver driver;

	@Before
	public void initDriver() {
		this.driver = new ChromeDriver();
	}

	@After
	public void closeDriver() throws InterruptedException {
		Thread.sleep(1000);
		this.driver.quit();
	}

	@Test
	public void keyboardAndMouseInputTest() {
		this.driver.get("http://formy-project.herokuapp.com/keypress");

		WebElement name = driver.findElement(By.id("name"));

		name.click(); // make field actice
		name.sendKeys("Vellu");

		WebElement button = driver.findElement(By.id("button"));
		button.click();
	}

	@Test
	public void autocompleate() throws InterruptedException {
		this.driver.get("http://formy-project.herokuapp.com/autocomplete");

		WebElement autocomplete = driver.findElement(By.id("autocomplete"));

		autocomplete.click();
		autocomplete.sendKeys("Mannerheimintie 15 Helsi"); // just 1 suggestion
		Thread.sleep(1000);

		WebElement autocompleteResult = driver.findElement(By.className("pac-item"));
		autocompleteResult.click();
	}

	@Test
	public void scrollPageContent() {

		this.driver.get("http://formy-project.herokuapp.com/scroll");

		WebElement name = driver.findElement(By.id("name"));
		Actions actions = new Actions(this.driver);
		actions.moveToElement(name);
		name.sendKeys("Vellu");

		WebElement date = driver.findElement(By.id("date"));
		date.sendKeys("07/08/1985");
	}

	@Test
	public void switchWindow() {

		this.driver.get("http://formy-project.herokuapp.com/switch-window");

		WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
		newTabButton.click();

		// get original handle
		String originalHandle = this.driver.getWindowHandle();

		// iterate to the new tab
		for (String handle1 : this.driver.getWindowHandles()) {
			this.driver.switchTo().window(handle1);
		}

		// return to the original tab
		this.driver.switchTo().window(originalHandle);
	}

	@Test
	public void switchToAlert() {

		this.driver.get("http://formy-project.herokuapp.com/switch-window");

		WebElement alertButton = driver.findElement(By.id("alert-button"));
		alertButton.click();

		Alert alert = this.driver.switchTo().alert();
		alert.accept();
	}

}
