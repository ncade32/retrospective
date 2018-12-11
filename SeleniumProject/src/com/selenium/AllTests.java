package com.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ WelcomePageRestrictionsTest.class, WelcomePageTest.class, RegPageRestrictionsTest.class, FailLoginTest.class, 
				EditInfoPageTest.class, VerifyPageTest.class, 
				RetrospectiveByNameTest.class, RetrospectiveByProjectTest.class, ViewCommentsTest.class, RegisterBtnTest.class, 
				SuccessLoginTest.class, NewUserTest.class})
public class AllTests {

}
