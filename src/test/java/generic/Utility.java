package generic;

import java.io.FileInputStream;
import java.util.Properties;

public class Utility {

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
}
