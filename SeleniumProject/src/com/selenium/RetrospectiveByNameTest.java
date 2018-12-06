package com.selenium;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RetrospectiveByNameTest {

	@Test
	public void retrospectiveByNameTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		//Make sure user is redirected to login page
		driver.get("http://localhost:8080/RetroWeb/retroCommentsByName.jsp");
		driver.manage().window();
		
		String findName = "Norman Cade", teamNum= "4", projName="project2", sprintNum= "1", user="scrum", pass="test";
		String [] comments = {"hello", "goodbye", "hola"};
		
		String at = driver.getTitle();
		String et = "Retrospective Login";
		assertEquals(et,at);
		
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("pass")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.get(2).click();
		at = driver.getTitle();
		et = "Retrospective Comments By Name";
		assertEquals(et,at);
		//Make sure logout button works
		driver.findElement(By.id("logout")).click();
		at = driver.getTitle();
		et = "Retrospective Login";
		assertEquals(et,at);
		//Make sure user is redirected to login page after logging out and hitting back button
		driver.navigate().back();
		at = driver.getTitle();
		et = "Retrospective Login";
		assertEquals(et,at);
		
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("pass")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
		//Create a new retrospective entry
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
		driver.findElement(By.name("done")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		links = driver.findElements(By.tagName("a"));
		links.get(2).click();
		//Check to see that the new entry shows up in the table by the user's name
		assertEquals(findName, driver.findElement(By.id("Norman-;Cade")).getText());
		//Make sure search functionality works
		driver.findElement(By.tagName("input")).sendKeys(findName);
		List<WebElement> allRows = driver.findElements(By.tagName("tr"));
		assertEquals(2, allRows.size());
		assertEquals(findName, allRows.get(1).getText());
		//Make sure user is redirected to the his/her entry by clicking on his/her name
		allRows.get(1).click();
		at = driver.getTitle();
		et = "Retrospective Comments By Project";
		assertEquals(et,at);
		
		driver.close();
		
		//Delete the new entry from the database
		/*Connection conn = DbManager.connect();
		Statement st;
		try {
			st = conn.createStatement();
			st.execute("delete from feedback where user = '"+user+"' and teamNum = '"+teamNum+"' "
						+ "and sprintNum = '"+sprintNum+"' and projectName = '"+projName+"';" );
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
