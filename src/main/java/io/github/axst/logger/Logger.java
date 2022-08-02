package io.github.axst.logger;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    @Getter
    private final String loggerName;

    @Getter
    private final SimpleDateFormat format;

    @Getter
    private final Date data;

    public Logger(BuilderLogger builder) {
        this.loggerName = builder.loggerName;
        this.format = builder.format;
        this.data = builder.data;
    }

    /**
     * Send log on console
     *
     * @param message  The message to print
     * @param LogLevel Logger level
     */
    public void sendLog(String message, LogLevel LogLevel) {
        switch (LogLevel) {
            case INFO:
                System.out.println("[" + getFormat().format(getData()) + "] " + "[" + loggerName + "] [Main/INFO]: " + message);
            case WARN:
                System.out.println("[" + getFormat().format(getData()) + "] " + "[" + loggerName + "] [Main/WARN]: " + message);
            case ERROR:
                System.out.println("[" + getFormat().format(getData()) + "] " + "[" + loggerName + "] [Main/ERROR]: " + message);
        }
    }

    public enum LogLevel {
        INFO, ERROR, WARN
    }

    public static class BuilderLogger {
        private final String loggerName;
        private final Date data;
        private SimpleDateFormat format;

        public BuilderLogger(String name) {
            this.loggerName = name;
            data = new Date();
        }

        /**
         * THIS ISN'T OPTIONAL
         *
         * @param dateFormat Please check date format online or just put null for use default
         * @return The date format
         */
        public BuilderLogger dateFormat(String dateFormat) {
            if (dateFormat != null) format = new SimpleDateFormat(dateFormat);
            else format = new SimpleDateFormat("HH:mm:ss");
            return this;
        }

        public Logger build() {
            Logger logger = new Logger(this);
            validateLogger(logger);
            return logger;
        }

        void validateLogger(Logger logger) {
            if (logger.getLoggerName() == null) {
                System.out.println("[" + format.format(data) + "] " + "[Limee] [Main/FATAL]: Our logger is built wrong.");
            }
        }

    }
}

