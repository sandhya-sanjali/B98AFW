package script;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;



import generic.BaseTest;

public class DemoTest2 extends BaseTest
{
	@Test(enabled=false)
	public void testInvalidLogin()
	{
		String title=driver.getTitle();
		Reporter.log(title,true);
		test.info("title:"+title);
		
	}
}
