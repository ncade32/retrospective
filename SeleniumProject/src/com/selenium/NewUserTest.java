package com.selenium;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.retrospective.*;

public class NewUserTest {

	@Test
	public void newUserTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/Retrospective/register.jsp");
		driver.manage().window();
		
		// Register a new user
		driver.findElement(By.name("first")).sendKeys("first");
		driver.findElement(By.name("last")).sendKeys("last");
		driver.findElement(By.name("email")).sendKeys("email");
		driver.findElement(By.name("userReg")).sendKeys("userReg");
		driver.findElement(By.name("passReg")).sendKeys("passReg");
		driver.findElement(By.name("confirmPass")).sendKeys("passReg");
		driver.findElement(By.name("submit")).click();
		String at = driver.getTitle();
		String et = "Retrospective Login";
		
		// Make sure user can login in with new account
		driver.findElement(By.name("user")).sendKeys("userReg");
		driver.findElement(By.name("pass")).sendKeys("passReg");
		driver.findElement(By.name("login")).click();
		String at1 = driver.getTitle();
		String et1 = "Welcome To Retrospective";
		assertEquals(at,et);
		assertEquals(at1,et1);
		
		// Register a scrum master
		driver.findElement(By.id("logout")).click();
		System.out.println(driver.getTitle());
		driver.findElement(By.tagName("a")).click();
		driver.findElement(By.name("first")).sendKeys("first2");
		driver.findElement(By.name("last")).sendKeys("last2");
		driver.findElement(By.name("email")).sendKeys("email2");
		driver.findElement(By.name("userReg")).sendKeys("scrumReg");
		driver.findElement(By.name("passReg")).sendKeys("passReg");
		driver.findElement(By.name("confirmPass")).sendKeys("passReg");
		driver.findElement(By.id("scrumMaster")).click();
		driver.findElement(By.id("code")).sendKeys("test");
		driver.findElement(By.name("submit")).click();
		// Make sure scrum master can login with new account
		driver.findElement(By.name("user")).sendKeys("scrumReg");
		driver.findElement(By.name("pass")).sendKeys("passReg");
		driver.findElement(By.name("login")).click();
		at = driver.getTitle();
		et = "Welcome Scrum Master";
		assertEquals(et,at);
		
		
		driver.close();
		
		Connection conn = DbManager.connect();
		Statement st;
		try {
			st = conn.createStatement();
			st.execute("delete from admin where user = 'userReg';" );
			st.execute("delete from admin where user = 'ScrumReg';" );
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
