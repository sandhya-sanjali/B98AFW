package generic;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public abstract class BaseTest implements IAutoConst
{
	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest test;

	
	@BeforeSuite
	public void initReport()
	{

		ExtentSparkReporter spark=new ExtentSparkReporter("./target/spark.html");
		extent.attachReporter(spark);
	}
	
	@AfterSuite
	public void publishReport()
	{
		extent.flush();
	}
	
	@Parameters({"browser","grid","gridURL"})
	@BeforeMethod
	public void preCondition(Method method,@Optional(BROWSER) String browser
			,@Optional(GRID) String grid,@Optional(GRID_URL) String gridURL) throws Exception
	{
		String testName = method.getName();
		
		test = extent.createTest(testName);
		
		String AppURL = Utility.getProperty(CONFIG_PATH, "APPURL");
		test.info("APPURL:"+AppURL);
		
		String strITO = Utility.getProperty(CONFIG_PATH, "ITO");
		long lngITO = Long.parseLong(strITO);
		test.info("ITO:"+lngITO);
		
		
		String strETO =  Utility.getProperty(CONFIG_PATH, "ETO");
		long lngETO= Long.parseLong(strETO);
		test.info("ETO:"+lngETO);
		
		if(grid.equalsIgnoreCase("yes"))
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				driver=new RemoteWebDriver(new URL(gridURL),new ChromeOptions());
				test.info("Open Chrome Browser in Remote System");
			}
			else
			{
				driver=new RemoteWebDriver(new URL(gridURL),new EdgeOptions());
				test.info("Open Edge Browser in Remote System");
			}
		}
		else
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver();
				test.info("Open Chrome Browser in Local System");
			}
			else
			{
				driver=new EdgeDriver();
				test.info("Open Edge Browser in Local System");
			}
		}
		
		
		
		
		driver.get(AppURL);
		test.info("Enter the URL:"+AppURL);
		
		driver.manage().window().maximize();
		test.info("maximize the browser");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(lngITO));
		test.info("Set ITO:"+lngITO);
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(lngETO));
		test.info("Set ETO:"+lngETO);
	}
	
	@AfterMethod
	public void postCondition(ITestResult testResult ) throws Exception
	{
		String testName = testResult.getName();
		int status = testResult.getStatus();
		
		if(status==2)
		{
			TakesScreenshot t=(TakesScreenshot)driver;
			File srcImage = t.getScreenshotAs(OutputType.FILE);
			File dstIamge =new File("./target/screenshots/"+testName+".png");
			FileUtils.copyFile(srcImage, dstIamge);
			String msg = testResult.getThrowable().getMessage();
			test.fail(msg,MediaEntityBuilder.createScreenCaptureFromPath("./screenshots/"+testName+".png").build());
		}
		Thread.sleep(2000);
		driver.quit();
		test.info("Close the browser");
	}
}
