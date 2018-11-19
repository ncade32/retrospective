package com.selenium;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.retrospective.DbManager;
import com.retrospective.GetData;

public class WelcomePageTest {

	@Test
	public void welcomePageTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		//Make sure user is redirected back to login page
		driver.get("http://localhost:8080/Retrospective/welcome.jsp");
		driver.manage().window();
		
		String team, proj, sprint, teamNum= "4", projName="project2", sprintNum= "1", user="user", pass="pass";
		String [] comments = {"hello", "goodbye", "hola"};
		String [] newComments = {"belt", "sheep", "plane"};
		
		String at = driver.getTitle();
		String et = "Retrospective Login";
		assertEquals(at,et);
		
		//Make sure logout button works
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("pass")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
		driver.findElement(By.id("logout")).click();
		at = driver.getTitle();
		et = "Retrospective Login";
		assertEquals(et,at);
		
		//Make sure user cannot navigate back to welcome page after logging out
		driver.navigate().back();
		at = driver.getTitle();
		et = "Retrospective Login";
		assertEquals(at,et);
		
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("pass")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
		at = driver.getTitle();
		et = "Welcome To Retrospective";
		assertEquals(et,at);
		
		//Create new entry
		driver.findElement(By.name("teamNum")).sendKeys(teamNum);
		WebElement mySelectElement = driver.findElement(By.id("chooseProj"));
		Select dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).sendKeys(sprintNum);
		driver.findElement(By.id("new-task")).sendKeys(comments[0]);
		driver.findElement(By.id("addWrong")).click();
		driver.findElement(By.id("wellNew-task")).sendKeys(comments[1]);
		driver.findElement(By.id("addWell")).click();
		driver.findElement(By.id("improveNew-task")).sendKeys(comments[2]);
		driver.findElement(By.id("addImprove")).click();
		driver.findElement(By.name("submit")).click();
		
		//Make sure user is directed to verification page after hitting submit
		at = driver.getTitle();
		et = "Verification";
		assertEquals(at,et);
		
		driver.navigate().back();
		driver.findElement(By.name("teamNum")).clear();
		driver.findElement(By.name("teamNum")).sendKeys(teamNum);
		mySelectElement = driver.findElement(By.id("chooseProj"));
		dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).clear();
		driver.findElement(By.name("sprintNum")).sendKeys(sprintNum);
		driver.findElement(By.id("new-task")).sendKeys(comments[0]);
		driver.findElement(By.id("addWrong")).click();
		driver.findElement(By.id("wellNew-task")).sendKeys(comments[1]);
		driver.findElement(By.id("addWell")).click();
		driver.findElement(By.id("improveNew-task")).sendKeys(comments[2]);
		driver.findElement(By.id("addImprove")).click();
		
		//Make sure delete buttons work correctly
		List<WebElement> deleteBtns = driver.findElements(By.className("delete"));
		deleteBtns.get(0).click();
		deleteBtns.get(1).click();
		deleteBtns.get(2).click();
		assertEquals(0, driver.findElements(By.name("wrong")).size());
		assertEquals(0, driver.findElements(By.name("well")).size());
		assertEquals(0, driver.findElements(By.name("improve")).size());
		
		driver.findElement(By.id("new-task")).sendKeys(comments[0]);
		driver.findElement(By.id("addWrong")).click();
		driver.findElement(By.id("wellNew-task")).sendKeys(comments[1]);
		driver.findElement(By.id("addWell")).click();
		driver.findElement(By.id("improveNew-task")).sendKeys(comments[2]);
		driver.findElement(By.id("addImprove")).click();
		//Make sure edit buttons work correctly
		List<WebElement> editBtns = driver.findElements(By.className("edit"));
		
		editBtns.get(0).click();
		assertEquals("Save", editBtns.get(0).getText());
		driver.findElement(By.name("wrong")).clear();
		driver.findElement(By.name("wrong")).sendKeys(newComments[0]);
		editBtns.get(0).click();
		assertEquals("Edit", editBtns.get(0).getText());
		
		editBtns.get(1).click();
		assertEquals("Save", editBtns.get(1).getText());
		driver.findElement(By.name("well")).clear();
		driver.findElement(By.name("well")).sendKeys(newComments[1]);
		editBtns.get(1).click();
		assertEquals("Edit", editBtns.get(1).getText());
		
		editBtns.get(2).click();
		assertEquals("Save", editBtns.get(2).getText());
		driver.findElement(By.name("improve")).clear();
		driver.findElement(By.name("improve")).sendKeys(newComments[2]);
		editBtns.get(2).click();
		assertEquals("Edit", editBtns.get(2).getText());
		
		
		Connection conn = DbManager.connect();
		//Make sure data is entered in the database correctly
		try {
			team = GetData.getTeamNumByUser(conn, user);
			proj	= GetData.getProjectNameByUser(conn, user);
			sprint = GetData.getSprintNumByUser(conn, user);
			assertEquals(team, teamNum);
			assertEquals(proj, projName);
			assertEquals(sprint, sprintNum);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		driver.close();
	}

}
