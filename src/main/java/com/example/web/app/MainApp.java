package com.example.web.app;
import static spark.Spark.get;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.xml.DOMConfigurator;

public class MainApp {
	
	static{
        init();
    }

	public static void main(String[] args) {
		
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
