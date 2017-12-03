package com.sitedia.mytodolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Application launcher
 * 
 * @author sitedia
 *
 */
@SpringBootApplication
public class Application {

	private static ConfigurableApplicationContext applicationContext;

	/**
	 * Private constructor
	 */
	protected Application() {
		super();
	}

	/**
	 * Launcher
	 * 
	 * @param args
	 *            input parameters
	 */
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(Application.class, args);
	}

	/**
	 * Stops the application
	 */
	public static void close() {
		applicationContext.close();
	}

}
