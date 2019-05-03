package cinema.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerService {
    private LoggerService() {}

    static private Formatter CSVFormat = new Formatter() {
        @Override
        public String format(LogRecord record) {
            String level = record.getLevel().toString();

            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli( record.getMillis() ), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");;
            String timestamp = date.format(formatter);

            String action = record.getMessage();
            String name = record.getLoggerName();

            String ans = level + ", " + timestamp + ", " + action + ", " + name + "\n\n";
            return ans;
        }
    };

    static private Formatter LargeFormat = new Formatter() {
        @Override
        public String format(LogRecord record) {
            String level = record.getLevel().toString();

            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli( record.getMillis() ), ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");;
            String timestamp = date.format(formatter);

            String action = record.getMessage();
            String name = record.getLoggerName();

            String ans = "";
            String space = "\n";
            String separator = "=================================================================================\n";

            ans += space;
            ans += separator;

            ans += "Level: " + level + "; Stamp: " + timestamp + "; Logger name: " + name + "\n";
            ans += action + "\n";

            ans += separator;
            ans += space;

            return ans;
        }
    };

    public static Logger getInstance(boolean CSVFormatHandler, boolean LargeFormatHandler) throws IOException {
        Logger logger = Logger.getLogger("cinema.Logger");

        if (CSVFormatHandler) {
            FileHandler handler = new FileHandler("logging/CSVLog.txt", true);
            handler.setFormatter(LoggerService.CSVFormat);
            logger.addHandler(handler);
        }

        if (LargeFormatHandler) {
            FileHandler handler = new FileHandler("logging/LargeLog.txt", true);
            handler.setFormatter(LoggerService.LargeFormat);
            logger.addHandler(handler);
        }


        logger.setUseParentHandlers(false);

        return logger;
    }

    public static Logger getInstance() throws IOException {
        return LoggerService.getInstance(true, true);
    }
}
