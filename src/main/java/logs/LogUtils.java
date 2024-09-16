package logs;

import org.openqa.selenium.devtools.v126.v126Log;
import org.openqa.selenium.logging.Logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
	//Initialize Log4j instance
	private static final Logger Log =  LogManager.getLogger(v126Log.class);
	private static final Logger customLogger = LogManager.getLogger("customLogger");     // logger riêng

	//Info Level Logs
	public static void info (String message) {
		Log.info(message);
	}
	public static void infoCustom (String message) {
		customLogger.info(message);
	}
	public static void info (Object object) {
		Log.info(object);
	}

	//Warn Level Logs
	public static void warn (String message) {
		Log.warn(message);
	}
	public static void warn (Object object) {
		Log.warn(object);
	}

	//Error Level Logs
	public static void error (String message) {
		Log.error(message);
	}
	public static void error (Object object) {
		Log.error(object);
	}

	//Fatal Level Logs
	public static void fatal (String message) {
		Log.fatal(message);
	}

	//Debug Level Logs
	public static void debug (String message) {
		Log.debug(message);
	}
	public static void debug (Object object) {
		Log.debug(object);
	}
}
