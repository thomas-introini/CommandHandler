package settings;

import java.io.FileInputStream;
import java.util.Properties;

import logger.Logger;

public class Settings {
	private static Properties properties;

	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("settings.properties"));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.log(e.getMessage());
			System.exit(-1);
		}
	}

	private Settings() {
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static String getURL() {
		return properties.getProperty("URL");
	}

	public static String getDownloadDir() {
		return properties.getProperty("DownloadDir");
	}

	public static String getJID() {
		return properties.getProperty("JID");
	}

	public static String getLookUpDir() {
		return properties.getProperty("lookupDir");
	}

	public static String getPasswd() {
		return properties.getProperty("passwd");
	}

	public static String getUsername() {
		return properties.getProperty("username");
	}

	public static String getLogFile() {
		return properties.getProperty("logFile");
	}

	public static String getOutFile() {
		return properties.getProperty("outFile");
	}

	public static String getErrFile() {
		return properties.getProperty("errFile");
	}
}
