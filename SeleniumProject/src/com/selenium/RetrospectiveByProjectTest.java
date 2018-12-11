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


public class RetrospectiveByProjectTest {

	@Test
	public void retrospectiveByProjectTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		//Make sure user is redirected to login page
		driver.get("http://localhost:8080/RetroWeb/retroCommentsByProject.jsp");
		driver.manage().window();
		
		String findTitle = "TEST TESTS\'S COMMENTS", teamNum= "4", projName="project2", sprintNum= "1", user="scrum", pass="test";
		String [] comments = {"hello", "goodbye", "hola"};
		
		String at = driver.getTitle();
		String et = "Retrospective Login";
		assertEquals(et,at);
		
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("pass")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
		//Create a new entry
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
		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.get(1).click();
		driver.findElement(By.id("test-;tests")).click();
		at = driver.getTitle();
		et = "Retrospective Comments By Project";
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
		links = driver.findElements(By.tagName("a"));
		links.get(1).click();
		driver.findElement(By.id("test-;tests")).click();
		assertEquals(findTitle, driver.findElement(By.tagName("h3")).getText());
		List<WebElement> allRows = driver.findElements(By.tagName("tr"));
		assertEquals(3, allRows.size());
		//Make sure correct information is retrieved for the user new entry
		assertEquals(teamNum+"-;"+projName+"-;"+sprintNum, allRows.get(2).getAttribute("id"));
		//Make sure user is redirected to page to view the comments for the new entry
		allRows.get(1).click();
		at = driver.getTitle();
		et = "View Retrospective Comments";
		assertEquals(et,at);
		
		
		driver.close();
		
		//Delete new entry
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
