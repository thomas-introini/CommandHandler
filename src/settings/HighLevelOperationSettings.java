package settings;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import logger.Logger;

public class HighLevelOperationSettings {
	private static HashMap<String, String> mapping;
	private static HashMap<String, String> translate;
	public static Properties m = new Properties();
	public static Properties t = new Properties();
	static {
		
		try {
			m.load(new FileInputStream("mapping.properties"));
			t.load(new FileInputStream("translate.properties"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.log(e.getMessage());
		}
	}

	private HighLevelOperationSettings() {
	}
	
	public static Map<String, String> getMappings() {
		return mapping;
	}

	public static Map<String, String> getTranslates() {
		return translate;
	}
}
