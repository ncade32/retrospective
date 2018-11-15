package com.selenium;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterBtnTest {

	@Test
	public void registerBtnTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/Retrospective/loginPage.jsp");
		driver.manage().window();
		
		//Make sure registration button works on login page
		driver.findElement(By.tagName("a")).click();
		String at = driver.getTitle();
		String et = "Registration";
		driver.close();
		
		assertEquals(at,et);
		
	}

}
