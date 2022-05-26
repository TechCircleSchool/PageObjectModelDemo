package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	public static Properties configfile;

	// example.
	// public static WebDriver driver;

	static {

		try {
			String path = "src/test/resources/propertiesFolder/config.properties";
			FileInputStream input = new FileInputStream(path);

			configfile = new Properties();
			configfile.load(input);

			input.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String keyName) {

		return configfile.getProperty(keyName);
	}

}
