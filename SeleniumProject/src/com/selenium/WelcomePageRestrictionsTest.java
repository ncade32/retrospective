package com.selenium;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WelcomePageRestrictionsTest {

	@Test
	public void welcomePageRestrictionsTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/Retrospective/loginPage.jsp");
		driver.manage().window();
		
		String teamNum= "4", projName="project2", sprintNum= "1", user="user", pass="pass",
				invalidEntry="abc";
		
		//Make sure all error codes are showing correctly
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("pass")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
		driver.findElement(By.name("submit")).click();
		Alert alert = driver.switchTo().alert();
		String at = alert.getText();
		String et = "A team's number must be entered";
		alert.accept();
		assertEquals(at,et);
		
		driver.findElement(By.name("teamNum")).sendKeys(invalidEntry);
		driver.findElement(By.name("sprintNum")).sendKeys(invalidEntry);
		driver.findElement(By.name("submit")).click();
		//alert = driver.switchTo().alert();
		at = alert.getText();
		et = "A project must be chosen";
		alert.accept();
		assertEquals(at,et);
		
		driver.findElement(By.name("teamNum")).sendKeys(invalidEntry);
		WebElement mySelectElement = driver.findElement(By.id("chooseProj"));
		Select dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).sendKeys(invalidEntry);
		driver.findElement(By.name("submit")).click();
		//alert = driver.switchTo().alert();
		at = alert.getText();
		et = "Team number must be a number";
		alert.accept();
		assertEquals(et,at);
		
		driver.findElement(By.name("teamNum")).clear();
		driver.findElement(By.name("teamNum")).sendKeys(teamNum);
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).sendKeys(invalidEntry);
		driver.findElement(By.name("submit")).click();
		alert = driver.switchTo().alert();
		at = alert.getText();
		et = "Sprint number must be a number";
		alert.accept();
		assertEquals(et,at);
		
		driver.findElement(By.name("teamNum")).clear();
		driver.findElement(By.name("teamNum")).sendKeys(invalidEntry);
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).clear();
		driver.findElement(By.name("sprintNum")).sendKeys(sprintNum);
		driver.findElement(By.name("submit")).click();
		alert = driver.switchTo().alert();
		at = alert.getText();
		et = "Team number must be a number";
		alert.accept();
		assertEquals(et,at);
		
		driver.findElement(By.name("teamNum")).clear();
		driver.findElement(By.name("teamNum")).sendKeys(teamNum);
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).sendKeys(sprintNum);
		driver.findElement(By.name("submit")).click();
		alert = driver.switchTo().alert();
		at = alert.getText();
		et = "What went wrong must be filled out";
		alert.accept();
		assertEquals(at,et);
		
		driver.findElement(By.name("teamNum")).sendKeys(teamNum);
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).clear();
		driver.findElement(By.name("submit")).click();
		alert = driver.switchTo().alert();
		at = alert.getText();
		et = "A sprint number must be entered";
		alert.accept();
		assertEquals(at,et);
		
		driver.findElement(By.name("teamNum")).clear();
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).sendKeys(sprintNum);
		driver.findElement(By.name("submit")).click();
		alert = driver.switchTo().alert();
		at = alert.getText();
		et = "A team's number must be entered";
		alert.accept();
		assertEquals(at,et);
		
		driver.close();
		
		
	}

}
