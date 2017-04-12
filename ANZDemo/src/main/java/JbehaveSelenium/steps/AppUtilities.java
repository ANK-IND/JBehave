package JbehaveSelenium.steps;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class AppUtilities {
	
	public static Properties prop = new Properties();
	
	static {
		try(FileInputStream fis = new FileInputStream(new File("./Config.properties"))) {
			prop.load(fis);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}


