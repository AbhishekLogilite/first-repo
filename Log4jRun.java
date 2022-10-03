package com.test;

import org.apache.log4j.Logger;

public class Log4jRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final Logger logger = Logger.getLogger(Log4jRun.class);
		logger.debug("This is debug : " + "parameter/exception object");
		logger.info("This is info : " + "parameter/exception object");
		logger.warn("This is warn : " + "parameter/exception object");
		logger.error("This is error : " + "parameter/exception object");
		logger.fatal("This is fatal : " + "parameter/exception object");
	}

}
