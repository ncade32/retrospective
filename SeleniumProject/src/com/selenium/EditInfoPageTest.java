package com.selenium;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class EditInfoPageTest {

	@Test
	public void editInfoPageTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		//Make sure user is redirected back to login page
		driver.get("http://localhost:8080/Retrospective/editInfo.jsp");
		driver.manage().window();
		
		String teamNum= "4", projName="project2", sprintNum= "1", user="user", pass="pass";
		String [] comments = {"hello", "goodbye", "hola", "sand", "hoop", "can"};
		String [] newComments = {"belt", "sheep", "plane"};
		
		String at = driver.getTitle();
		String et = "Retrospective Login";
		assertEquals(et,at);
		
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("pass")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
		
		//Go to edit retrospective page
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
		driver.findElement(By.name("edit")).click();
		at = driver.getTitle();
		et = "Edit Retrospective";
		assertEquals(et,at);
		
		driver.findElement(By.id("logout")).click();
		at = driver.getTitle();
		et = "Retrospective Login";
		assertEquals(et,at);
		
		driver.navigate().back();
		driver.navigate().refresh();
		at = driver.getTitle();
		et = "Retrospective Login";
		assertEquals(et,at);
		
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("pass")).sendKeys(pass);
		driver.findElement(By.name("login")).click();
		
		driver.findElement(By.name("teamNum")).sendKeys(teamNum);
		mySelectElement = driver.findElement(By.id("chooseProj"));
		dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).sendKeys(sprintNum);
		driver.findElement(By.id("new-task")).sendKeys(comments[0]);
		driver.findElement(By.id("addWrong")).click();
		driver.findElement(By.id("wellNew-task")).sendKeys(comments[1]);
		driver.findElement(By.id("addWell")).click();
		driver.findElement(By.id("improveNew-task")).sendKeys(comments[2]);
		driver.findElement(By.id("addImprove")).click();
		driver.findElement(By.name("submit")).click();	
		driver.findElement(By.name("edit")).click();
		
		//Make sure all information is retrieved and displayed on edit page correctly
		assertEquals(teamNum, driver.findElement(By.name("teamNum")).getAttribute("value"));
		assertEquals(projName, driver.findElement(By.id("chooseProj")).getAttribute("value"));
		assertEquals(sprintNum, driver.findElement(By.name("sprintNum")).getAttribute("value"));
		List<WebElement> wrongComments = driver.findElements(By.name("wrong"));
		List<WebElement> wellComments = driver.findElements(By.name("well"));
		List<WebElement> improveComments = driver.findElements(By.name("improve"));
		assertEquals(comments[0], wrongComments.get(0).getText());
		assertEquals(comments[1], wellComments.get(0).getText());
		assertEquals(comments[2], improveComments.get(0).getText());
		
		//Make sure user is able to add on to previous comments from edit page
		driver.findElement(By.id("new-task2")).sendKeys(comments[3]);
		driver.findElement(By.id("addWrong")).click();
		driver.findElement(By.id("wellNew-task2")).sendKeys(comments[4]);
		driver.findElement(By.id("addWell")).click();
		driver.findElement(By.id("improveNew-task2")).sendKeys(comments[5]);
		driver.findElement(By.id("addImprove")).click();
		driver.findElement(By.name("submit")).click();
		at = driver.getTitle();
		et = "Verification";
		assertEquals(et,at);
		
		//Make sure new comments were added to database and appear correctly on verify page
		List<WebElement> enteredComments = driver.findElements(By.className("li-tasks-group"));
		assertEquals(comments[0], enteredComments.get(0).getText() );
		assertEquals(comments[3], enteredComments.get(1).getText() );
		assertEquals(comments[1], enteredComments.get(2).getText() );
		assertEquals(comments[4], enteredComments.get(3).getText() );
		assertEquals(comments[2], enteredComments.get(4).getText() );
		assertEquals(comments[5], enteredComments.get(5).getText() );
		
		driver.findElement(By.name("edit")).click();
		
		//Make sure delete buttons operate correctly
		List<WebElement> deleteBtns = driver.findElements(By.className("delete"));
		deleteBtns.get(0).click();
		deleteBtns.get(2).click();
		deleteBtns.get(4).click();
		wrongComments = driver.findElements(By.name("wrong"));
		wellComments = driver.findElements(By.name("well"));
		improveComments = driver.findElements(By.name("improve"));
		assertEquals(comments[3], wrongComments.get(0).getText() );
		assertEquals(comments[4], wellComments.get(0).getText() );
		assertEquals(comments[5], improveComments.get(0).getText() );
		
		List<WebElement> editBtns = driver.findElements(By.className("edit"));
		//Make sure edit buttons work correctly
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
		
		driver.close();
	}

}
