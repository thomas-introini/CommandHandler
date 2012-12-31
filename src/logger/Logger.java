package logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Logger {
	private static String logFile;
	private static File file;

	private Logger() {
	}

	public static String getLogFile() {
		return logFile;
	}

	public static void setLogFile(String logFile) {
		Logger.logFile = logFile;
		file = new File(logFile);
	}

	public static void log(String mex) {
		try {
			FileOutputStream fos = new FileOutputStream(file, true);
			StackTraceElement ste = new Throwable().getStackTrace()[1];
			String sb = "[" + new Date() + "] " + ste + " -> " + mex + "\n";
			fos.write(sb.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
