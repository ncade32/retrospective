package com.selenium;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SuccessLoginTest {

	@Test
	public void successLogintest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/RetroWeb/loginPage.jsp");
		driver.manage().window();
		//Make sure user is able to login 
		driver.findElement(By.name("user")).sendKeys("user");
		driver.findElement(By.name("pass")).sendKeys("pass");
		driver.findElement(By.name("login")).click();
		String at = driver.getTitle();
		String et = "Welcome To Retrospective";
		driver.close();
		
		assertEquals(et,at);
		
	}

}

