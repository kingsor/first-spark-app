package com.example.web.app;
import static spark.Spark.get;

import org.apache.log4j.xml.DOMConfigurator;

public class MainApp {
	
	static{
        init();
    }

	public static void main(String[] args) {
		get("/hello", (req, res) -> "Hello World");
	}
	
	/**
     * method to init log4j configurations
     */
    private static void init() {
        DOMConfigurator.configure("log4j.xml");
    }

}
