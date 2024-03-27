package generic;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public abstract class Utility {

	public static String getProperty(String path,String key)
	{
		String v="";
		try 
		{
			Properties p=new Properties();
			p.load(new FileInputStream(path));
			v=p.getProperty(key);
			System.out.println(v);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return v;
	}
	
	public static String getExcelData(String path,String sheet,int r,int c)
	{
		String v="";
		try 
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			v=wb.getSheet(sheet).getRow(r).getCell(c).toString();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return v;
	}
	
	//count rows, count columns, write data
}
