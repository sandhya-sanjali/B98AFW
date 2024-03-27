package generic;

import com.aventstack.extentreports.ExtentReports;

public interface IAutoConst {
	//all variables present in interface are public static final
	ExtentReports extent=new ExtentReports();
	String CONFIG_PATH ="./config.properties";
	String XL_PATH="./data/input.xlsx";
	String BROWSER="CHROME";
	String GRID="NO";
	String GRID_URL="http://localhost:4444";
}
