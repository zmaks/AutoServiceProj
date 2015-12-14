package com.autoservice;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Максим on 14.12.2015.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Properties prop = loadPropertiesFile("./src/main/resources/car_service.properties");
        int psc = Integer.valueOf(prop.getProperty("parkingSpaceCount"));
        int mc = Integer.valueOf(prop.getProperty("mastersCount"));
        int ec = Integer.valueOf(prop.getProperty("evacuatorsCount"));
        CarService carService = new CarService(psc,ec,mc);

        carService.startWorking();
        logger.info("Car service start working");
    }

    public static Properties loadPropertiesFile(String path) {

        Properties prop = new Properties();
        try (InputStream in = new FileInputStream(path)) {

            prop.load(in);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return prop;
    }
}
