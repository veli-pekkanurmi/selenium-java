package dev.nurmi.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class KeyboardandMouseInput {

	
	
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		driver.get("http://formy-project.herokuapp.com/autocomplete");

		WebElement autocomplete = driver.findElement(By.id("autocomplete"));

		autocomplete.click();
		autocomplete.sendKeys("Mannerheimintie 15 Helsi"); // just 1 suggestion

		WebElement autocompleteResult = driver.findElement(By.className("pac-item"));
		autocompleteResult.click();

		driver.quit();
	}

}
