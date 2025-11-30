package utiles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsUtils {

    public static final String Logs_Path =  "test-outputs/Logs";
    private static final String delimiter = "  ";

    private static Logger logger() {
        return LogManager.getLogger(
                Thread.currentThread().getStackTrace()[3].getClassName()
        );
    }

    public static void trace(String... message) {
        logger().trace(String.join(delimiter, message) + System.lineSeparator());

    }

    public static void debug(String... message) {
        logger().debug(String.join(delimiter, message) + System.lineSeparator());
    }

    public static void info(String... message) {
        logger().info(String.join(delimiter, message) + System.lineSeparator());
    }

    public static void warn(String... message) {
        logger().warn(String.join(delimiter, message) + System.lineSeparator());
    }

    public static void error(String... message) {
        logger().error(String.join(delimiter, message) + System.lineSeparator());
    }

    public static void fatal(String... message) {
        logger().fatal(String.join(delimiter, message) + System.lineSeparator());
    }
}

