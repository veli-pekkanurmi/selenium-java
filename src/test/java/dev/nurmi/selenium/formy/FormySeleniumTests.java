package dev.nurmi.selenium.formy;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;

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

	/*
	 * JAVASCRIPT
	 * executeAsyncScript - ensure finished / callback
	 * executeScript - body of anonymous function
	 * 
	 * when..
	 * - driver fail to click on any element (find element)
	 * - trigger actions on page
	 * - perform movement on page
	 */

	@Test
	public void executeJavaScriptModal() {

		this.driver.get("http://formy-project.herokuapp.com/modal");

		WebElement modalButton = this.driver.findElement(By.id("modal-button"));
		modalButton.click();

		WebElement closeButton = this.driver.findElement(By.id("close-button"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
		jsExecutor.executeScript("arguments[0].click()", closeButton);
	}

	@Test
	public void dragAndDrop() {

		this.driver.get("http://formy-project.herokuapp.com/dragdrop");

		WebElement image = this.driver.findElement(By.id("image"));
		WebElement box = this.driver.findElement(By.id("box"));

		Actions actions = new Actions(this.driver);
		actions.dragAndDrop(image, box).build().perform();
	}

	//
	// Advanced selectors

	@Test
	public void multipleCSSSelectors() {

		this.driver.get("http://formy-project.herokuapp.com/");

		// tag and ..
		WebElement tagAndClass = this.driver.findElement(By.cssSelector("input.q"));
		WebElement tagAndId = this.driver.findElement(By.cssSelector("input#q"));
		WebElement tagAndAttributeType = this.driver.findElement(By.cssSelector("input[type='radio']"));
		WebElement tagAndAttributeValue = this.driver.findElement(By.cssSelector("input[value='radio button']"));
		WebElement multipleClasses = this.driver.findElement(By.cssSelector(".btn.btn-lg.btn-success"));

		// match by text ..
		// - exact Match
		// - prefix
		// - suffix
		// - substring
	}

	/*
	 * Child nodes
	 * Nth child
	 */

	// COMPONENTS

	@Test
	public void radioButton() throws InterruptedException {

		this.driver.get("http://formy-project.herokuapp.com/radiobutton");

		WebElement radioButton1 = this.driver.findElement(By.id("radio-button-1"));
		radioButton1.click();
		Thread.sleep(1000);

		WebElement radioButton2 = this.driver.findElement(By.cssSelector("input[value='option2']"));
		radioButton2.click();
		Thread.sleep(1000);

		WebElement radioButton3 = this.driver.findElement(By.xpath("/html/body/div/div[3]/input"));
		System.out.println("is null: " + (null == radioButton3));
		radioButton3.click();
		Thread.sleep(1000);

	}

}
