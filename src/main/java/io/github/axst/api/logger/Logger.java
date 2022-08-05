package io.github.axst.api.logger;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    @Getter
    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    @Getter
    private static final Date data = new Date();
    @Getter
    private static String loggerName;

    public static void register(String name) {
        loggerName = name;
    }

    /**
     * Send log on console
     *
     * @param message  The message to print
     * @param LogLevel Logger level
     */
    public static void sendLog(String message, LogLevel LogLevel) {
        if (getLoggerName() != null) {
            switch (LogLevel) {
                case INFO:
                    System.out.println("[" + getFormat().format(getData()) + "] " + "[" + loggerName + "] [" + Thread.currentThread().getName() + "/INFO]: " + message);
                    break;
                case WARN:
                    System.out.println("[" + getFormat().format(getData()) + "] " + "[" + loggerName + "] [" + Thread.currentThread().getName() + "/WARN]: " + message);
                    break;
                case ERROR:
                    System.out.println("[" + getFormat().format(getData()) + "] " + "[" + loggerName + "] [" + Thread.currentThread().getName() + "/ERROR]: " + message);
                    break;
            }
        } else {
            System.out.println("[" + getFormat().format(getData()) + "] " + "[LimeeNull] [" + Thread.currentThread().getName() + "/FATAL]: There are no name for the logger.");
        }
    }

    public enum LogLevel {
        INFO, ERROR, WARN
    }
}

