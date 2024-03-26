package script;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;



import generic.BaseTest;

public class DemoTest extends BaseTest
{
	@Test
	public void testValidLogin()
	{
		String title=driver.getTitle();
		Reporter.log(title,true);
		test.info("title:"+title);
//		Assert.assertEquals("abc","xyz");
	}
}
