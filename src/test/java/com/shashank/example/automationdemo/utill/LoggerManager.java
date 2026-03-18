package com.shashank.example.automationdemo.utill;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggerManager
{
    private static Logger logger = null;

    public static Logger getLogger(Class<?> clazz){
        logger = LogManager.getLogger(clazz);
        return logger;
    }
}
