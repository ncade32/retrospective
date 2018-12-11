package com.selenium;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.interfaces.FeedbackDAOLocal;

public class VerifyPageTest {
	@EJB
	FeedbackDAOLocal feedback;

	@Test
	public void verifyPageTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ncade/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		//Make sure user is redirected to login page
		driver.get("http://localhost:8080/RetroWeb/verify.jsp");
		driver.manage().window();
		
		String teamNum= "4", projName="project2", sprintNum= "1", user="user", pass="pass";
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
		//Make sure user is redirected to verification page after hitting submit
		at = driver.getTitle();
		et = "Verification";
		assertEquals(et,at);
		
		//Make sure logout button works and user can not navigate back to page
		driver.findElement(By.id("logout")).click();
		driver.navigate().back();
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
		
		//Make sure all the entered data appears correctly on the verification page
		at = driver.findElement(By.id("teamNum")).getText();
		assertEquals(teamNum,at);
		
		at = driver.findElement(By.id("projName")).getText();
		assertEquals(projName,at);
		
		at = driver.findElement(By.id("sprintNum")).getText();
		assertEquals(sprintNum,at);
		
		List<WebElement> enteredComments = driver.findElements(By.className("li-tasks-group"));
		assertEquals(comments[0], enteredComments.get(0).getText() );
		assertEquals(comments[1], enteredComments.get(1).getText() );
		assertEquals(comments[2], enteredComments.get(2).getText() );
		
		driver.navigate().back();
		//Making sure the verification page can display none for the optional fields
		driver.findElement(By.id("new-task")).sendKeys(comments[0]);
		driver.findElement(By.id("addWrong")).click();
		driver.findElement(By.name("submit")).click();
		enteredComments = driver.findElements(By.className("li-tasks-group"));
		assertEquals(comments[0], enteredComments.get(0).getText() );
		assertEquals("none", enteredComments.get(1).getText());
		assertEquals("none", enteredComments.get(2).getText());
		
		//Make sure the alert for saved retrospective appears
		driver.findElement(By.id("done")).click();
		Alert alert = driver.switchTo().alert();
		at = alert.getText();
		et = "Retrospective Comments Saved";
		alert.accept();
		assertEquals(et,at);
		//Make sure user is not able to use back button once retrospective entered
		driver.navigate().back();
		at = driver.getTitle();
		et = "Welcome To Retrospective";
		assertEquals(et,at);
		
		/*Create another entry with same data as previous entry to make sure the alert
		 *for entering data for the same project, team, and sprint appears*/
		driver.findElement(By.name("teamNum")).sendKeys(teamNum);
		mySelectElement = driver.findElement(By.id("chooseProj"));
		dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText(projName);
		driver.findElement(By.name("sprintNum")).sendKeys(sprintNum);
		driver.findElement(By.id("new-task")).sendKeys(comments[0]);
		driver.findElement(By.id("addWrong")).click();
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.id("done")).click();
		alert = driver.switchTo().alert();
		at = alert.getText();
		et = "Comments Already Entered For This Team, Project, And Sprint";
		alert.accept();
		assertEquals(et,at);
		
		driver.close();
		
		boolean enteredCorrectly = feedback.isDuplicateEntry(user, Integer.parseInt(teamNum), projName, Integer.parseInt(sprintNum));
		
		assertEquals(true, enteredCorrectly);
	
		/*
		Connection conn = DbManager.connect();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select user, teamNum, projectName, sprintNum from feedback where user = '"+user+"' and teamNum = '"+teamNum+"' "
					+ "and sprintNum = '"+sprintNum+"' and projectName = '"+projName+"';" );
			rs.next();
			//Make sure information is put into the database correctly
			assertEquals(user, rs.getString(1));
			assertEquals(Integer.parseInt(teamNum), rs.getInt(2));
			assertEquals(projName, rs.getString(3));
			assertEquals(Integer.parseInt(sprintNum), rs.getInt(4));
			
			//Delete new entry
			st.execute("delete from feedback where user = '"+user+"' and teamNum = '"+teamNum+"' "
						+ "and sprintNum = '"+sprintNum+"' and projectName = '"+projName+"';" );
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
