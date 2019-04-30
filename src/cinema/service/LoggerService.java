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

    public static Logger getInstance() throws IOException {
        Logger logger = Logger.getLogger("cinema.Logger");

        FileHandler handler = new FileHandler("logging/logMessages.txt", true);
        Formatter CSVFormat = new Formatter() {
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

        handler.setFormatter(CSVFormat);

        logger.addHandler(handler);
        logger.setUseParentHandlers(false);

        return logger;
    }
}
