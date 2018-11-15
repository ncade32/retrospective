package com.selenium;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegPageRestrictionsTest {

	@Test
	public void regPageRestrictionsTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/Retrospective/register.jsp");
		driver.manage().window();
		String first= "first", last= "last", email="email", user="user1", pass="pass",
				code="incorrect";
		//Make sure all error codes are displayed when nothing is entered
		driver.findElement(By.name("submit")).click();
		List<WebElement> Errors = driver.findElements(By.className("error-group"));
		/*for(int i = 0; i < Errors.size(); i++) {
			System.out.println(Errors.get(i).getText());
		}*/
		assertEquals(Errors.get(0).getText(),"First Name Must Be Entered");
		assertEquals(Errors.get(1).getText(),"Last Name Must Be Entered");
		assertEquals(Errors.get(2).getText(),"Email Must Be Entered");
		assertEquals(Errors.get(3).getText(),"Username Must Be Entered");
		assertEquals(Errors.get(5).getText(),"Password Must Be Entered");
		
		/*Go one field at a time making sure all error codes show except for the 
		 *one with info entered in it*/
		driver.findElement(By.name("first")).sendKeys(first);
		driver.findElement(By.name("submit")).click();
		Errors = driver.findElements(By.className("error-group"));
		assertEquals(Errors.get(1).getText(),"Last Name Must Be Entered");
		assertEquals(Errors.get(2).getText(),"Email Must Be Entered");
		assertEquals(Errors.get(3).getText(),"Username Must Be Entered");
		assertEquals(Errors.get(5).getText(),"Password Must Be Entered");
		assertEquals(driver.findElement(By.name("first")).getAttribute("value"), first);
		//Last name entered
		driver.findElement(By.name("last")).sendKeys(last);
		driver.findElement(By.name("submit")).click();
		Errors = driver.findElements(By.className("error-group"));
		assertEquals(Errors.get(2).getText(),"Email Must Be Entered");
		assertEquals(Errors.get(3).getText(),"Username Must Be Entered");
		assertEquals(Errors.get(5).getText(),"Password Must Be Entered");
		assertEquals(driver.findElement(By.name("last")).getAttribute("value"),last);
		//Email entered
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("submit")).click();
		Errors = driver.findElements(By.className("error-group"));
		assertEquals(Errors.get(3).getText(),"Username Must Be Entered");
		assertEquals(Errors.get(5).getText(),"Password Must Be Entered");
		assertEquals(driver.findElement(By.name("email")).getAttribute("value"),email);
		//User entered
		driver.findElement(By.name("userReg")).sendKeys(user);
		driver.findElement(By.name("submit")).click();
		Errors = driver.findElements(By.className("error-group"));
		assertEquals(Errors.get(5).getText(),"Password Must Be Entered");
		assertEquals(driver.findElement(By.name("userReg")).getAttribute("value"),user);
		//Password entered
		driver.findElement(By.name("passReg")).sendKeys(pass);
		driver.findElement(By.name("submit")).click();
		Errors = driver.findElements(By.className("error-group"));
		assertEquals(Errors.get(6).getText(),"Passwords Do Not Match");
		assertEquals(driver.findElement(By.name("passReg")).getAttribute("value"),"");
		//Confirm password entered
		driver.findElement(By.name("passReg")).sendKeys(pass);
		driver.findElement(By.name("confirmPass")).sendKeys(pass);
		driver.findElement(By.id("scrumMaster")).click();
		driver.findElement(By.name("submit")).click();
		Errors = driver.findElements(By.className("error-group"));
		assertEquals(Errors.get(7).getText(),"Incorrect Scrum Code");
		assertEquals(driver.findElement(By.name("first")).getAttribute("value"), first);
		assertEquals(driver.findElement(By.name("last")).getAttribute("value"), last);
		assertEquals(driver.findElement(By.name("email")).getAttribute("value"), email);
		assertEquals(driver.findElement(By.name("userReg")).getAttribute("value"), user);
		//Check scrum code error
		driver.findElement(By.name("passReg")).sendKeys(pass);
		driver.findElement(By.name("confirmPass")).sendKeys(pass);
		driver.findElement(By.id("scrumMaster")).click();
		driver.findElement(By.name("code")).sendKeys(code);
		driver.findElement(By.name("submit")).click();
		Errors = driver.findElements(By.className("error-group"));
		assertEquals(Errors.get(7).getText(),"Incorrect Scrum Code");
		assertEquals(driver.findElement(By.name("first")).getAttribute("value"), first);
		assertEquals(driver.findElement(By.name("last")).getAttribute("value"), last);
		assertEquals(driver.findElement(By.name("email")).getAttribute("value"), email);
		assertEquals(driver.findElement(By.name("userReg")).getAttribute("value"), user);
		
		driver.close();
		
		
	}

}
