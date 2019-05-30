package dev.nurmi.selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class Hello {

	public static void main(String[] args) {
		// Set the property for webdriver.chrome.driver to be the location to your local
		// download of chromedriver
		//System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");


		
		// Create new instance of ChromeDriver
		WebDriver driver = new ChromeDriver();

		// And now use this to visit Google
		driver.get("http://www.google.com");

		// Find the text input element by its name
		WebElement element = driver.findElement(By.name("q"));

		// Enter something to search for
		element.sendKeys("testijuttu!");

		// Now submit the form
		element.submit();

		// Close the browser
		driver.quit();
	}

	// http://formy-project.herokuapp.com/
}