package dev.nurmi.selenium.formy;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormySeleniumWorkflowTest {

	WebDriver driver;

	@Before
	public void initDriver() {
		this.driver = new ChromeDriver();
	}

	@After
	public void closeDriver() throws InterruptedException {
		Thread.sleep(5000);
		this.driver.quit();
	}
	
	
	@Test
	public void completeForm() {
		this.driver.get("http://formy-project.herokuapp.com/form");

		WebElement name = this.driver.findElement(By.id("first-name"));
		name.sendKeys("John");
		WebElement lastname = this.driver.findElement(By.id("last-name"));
		lastname.sendKeys("Doe");
		WebElement jobtitle = this.driver.findElement(By.id("job-title"));
		jobtitle.sendKeys("Tester");
		
		WebElement educationsRadiobox = this.driver.findElement(By.id("radio-button-1"));
		educationsRadiobox.click();
	
		WebElement genderCheckbox = this.driver.findElement(By.id("checkbox-1"));
		genderCheckbox.click();
		
		Select experienceDropdown = new Select(this.driver.findElement(By.id("select-menu")));
		experienceDropdown.selectByValue("1");
	
		WebElement dateField = this.driver.findElement(By.id("datepicker"));
		dateField.sendKeys("07/08/1985");
		dateField.sendKeys(Keys.RETURN); // close with enter
			
		WebElement submitBtn = this.driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary"));
		submitBtn.click();
		
		
		WebDriverWait wait = new WebDriverWait(this.driver, 50); // new page
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
		
		String alertText = alert.getText();
	
		assertEquals("The form was successfully submitted!", alertText);
		
	}
	
	/*
	 * Page object pattern -> separate pages to classes
	 * 
	 * */
	
}
