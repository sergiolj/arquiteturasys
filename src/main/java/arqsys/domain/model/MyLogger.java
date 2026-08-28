package arqsys.domain.model;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

enum Format {
	SIMPLE, COMPLETE;
}

public class MyLogger {
	private Logger logger;
	private Formatter formatter;

	private String loggerClassName;
	private static final DateTimeFormatter DATA_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
			.withZone(ZoneId.systemDefault());
	
	
	public MyLogger(String loggerClassName) {
		super();
		this.loggerClassName = loggerClassName;
		this.logger = Logger.getLogger(loggerClassName);
		ConsoleHandler consoleHandler = new ConsoleHandler();
		this.formatter = customFormatter();
		
		consoleHandler.setFormatter(formatter);
		logger.addHandler(consoleHandler);
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);
	}
	
	
	public void addOutputFileHandler() {
		Handler fileHandler;
		try {
			fileHandler = new FileHandler(loggerClassName + ".log", true);
			fileHandler.setLevel(Level.WARNING);
			fileHandler.setFormatter(formatter);
			logger.addHandler(fileHandler);

		}catch (IOException e) {
			logger.warning("Erro ao criar log " + e.getMessage());
		}
		
	}
	private Formatter customFormatter() {
		return new Formatter() {
		@Override
		public String format(LogRecord logRecord) {
			String dataFormatadaString = DATA_TIME_FORMATTER.format(logRecord.getInstant());
			
			return String.format("[%s] [%s] %s: %s%n",
					dataFormatadaString,
					logRecord.getLoggerName(),
					logRecord.getLevel(),
					logRecord.getMessage());
			}
		};
	}
	
	/**
	 * PADRÃO DE PROJETO FACADE para evitar expor o looger interno da classe e manter as funcionalidades do logger
	 * @param message
	 */
	public void info(String message) {
		this.logger.info(message);
	}
	
	public void warning(String message) {
		this.logger.warning(message);
	}
	
	public void severe(String message) {
		this.logger.severe(message);
	}
	
}


