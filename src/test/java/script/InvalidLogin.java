package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import pages.LoginPage;

public class InvalidLogin extends BaseTest
{
	@Test(priority = 2)
	public void testInvalidLogin()
	{
		String un=Utility.getExcelData(XL_PATH,"InvalidLogin",1,0);
		String pw=Utility.getExcelData(XL_PATH,"InvalidLogin",1,1);
		
		test.info("enter invalid un");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(un);
		
		test.info("enter invalid pwd");
		loginPage.setPassword(pw);
		
		test.info("click go");
		loginPage.clickGoButton();
		
		test.info("verify err msg is displayed");
		boolean result = loginPage.verifyErrMsgIsDisplayed(wait);
		Assert.assertTrue(result);
	}
}
