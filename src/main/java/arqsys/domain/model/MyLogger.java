package arqsys.domain.model;

import java.util.logging.Formatter;
import java.util.logging.Handler;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

enum Format {
	SIMPLE, COMPLETE;
}

public class MyLogger {
	private Logger logger;
	private Formatter formatter;
	private Handler handler;
	private static final DateTimeFormatter DATA_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
			.withZone(ZoneId.systemDefault());
	
	
	public MyLogger(String loggerClassName) {
		super();
		this.logger = Logger.getLogger(loggerClassName);
		this.handler = new ConsoleHandler();
		this.formatter = new SimpleFormatter();
		
		handler.setFormatter(formatter);
		logger.addHandler(handler);
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);
	}
	
	public void setSimpleOutput() {
		this.handler.setFormatter(simpleFormatter());
		
	}
	
	private Formatter simpleFormatter() {
		return new Formatter() {
		@Override
		public String format(LogRecord record) {
			String dataFormatadaString = DATA_TIME_FORMATTER.format(record.getInstant());
			
			return String.format("[%s] %s: %s%n",
					dataFormatadaString,
					record.getLevel(),
					record.getMessage());
			}
		};
	}
	
	public Logger getLogger() {
		return this.logger;
	}
}
