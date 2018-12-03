package com.selenium;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FailLoginTest {

	@Test
	public void failLoginTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/RetroWeb/loginPage.jsp");
		driver.manage().window();
		// Make sure user is denied access for wrong credentials
		driver.findElement(By.name("user")).sendKeys("user");
		driver.findElement(By.name("pass")).sendKeys("fail");
		driver.findElement(By.name("login")).click();
		String at = driver.getTitle();
		String et = "Retrospective Login";
		boolean isError = driver.findElement(By.className("error-group")).isDisplayed();
		driver.close();
		
		assertEquals(at,et);
		assertEquals(isError,true);
		
	}

}
