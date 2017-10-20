package com.example.web.app;
import static spark.Spark.get;
import static spark.Spark.port;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class MainApp {

	static final Logger logger = Logger.getLogger( MainApp.class );
	
	static{
        init();
    }

	public static void main(String[] args) {

		logger.info("java.version:" + System.getProperty("java.version"));
		logger.info("java.vm.info:" + System.getProperty("java.vm.info"));
		logger.info("os.arch:" + System.getProperty("os.arch"));
		logger.info("os.name:" + System.getProperty("os.name"));
		logger.info("os.version:" + System.getProperty("os.version"));
		logger.info("sun.arch.data.model:" + System.getProperty("sun.arch.data.model"));
		logger.info("java.library.path:" + System.getProperty("java.library.path"));
		
		port(getHerokuAssignedPort());

		get("/", (req, res) -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			String message = String.format("Spark Main Page on Heroku [%s]", sdf.format(new Date()));
			return message;
		});

		get("/hello", (req, res) -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			String message = String.format("Hello World [%s]", sdf.format(new Date()));
			return message;
		});

	}
	
	/**
     * method to init log4j configurations
     */
    private static void init() {
        DOMConfigurator.configure("log4j.xml");
	}
	
	static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

}
