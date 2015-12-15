package com.autoservice;


import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Максим on 14.12.2015.
 */
public class DataWriter {
    private static final Logger logger = Logger.getLogger(DataWriter.class.getName());

    // id xml-файла, чтобы не было одинаковых имен, т.к. имена тачек могут повторяться
    private static int uniqueIdForXMLfile = 0;

    /**
     * метод, выводящий информацию о тачке в консоль
     * @param car
     */
    private static void writeInConsole(Car car) {
        System.out.println("Information about fixed car\n"
                + "ID:\t" + car.getId()
                + "\nName:\t" + car.getName()
                + "\nOwner:\t" + car.getOwner()
                + "\nMinimal handling time:\t" + car.getHandlingTime());
        logger.info("Data about "+car.getName()+" writed in console");
    }

    /**
     * метод, записывающий информацию о тачке в отдельный xml-файл в папке CarsInfo
     * @param car
     */
    private static void writeInXML(Car car)  {
        try {
            Element root = new Element(car.getId());
            Document doc = new Document(root);
            root.addContent(new Element("Name").addContent(car.getName()));
            root.addContent(new Element("Owner").addContent(car.getOwner()));
            root.addContent(new Element("HandlingTime").addContent(String.valueOf(car.getHandlingTime())));

            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            new File("./CarsInfo").mkdirs();
            outputter.output(doc, new FileOutputStream("CarsInfo/" + car.getName() + "_" + uniqueIdForXMLfile + ".xml"));
            uniqueIdForXMLfile++;
            logger.info("Data about "+car.getName()+"writed in XML-file");
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


    /**
     * метод, записывающий информацию о тачке в БД в разные таблицы в зависимости от Id(модели)
     * @param car
     */
    private static void writeInDateBase(Car car) {
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + car.getId() + " (" +
                    "  Name VARCHAR(20)," +
                    "  Owner VARCHAR(20)," +
                    "  Handling_time INT(5)" +
                    "  );";
            stmt.execute(createTableQuery);
            String query = "INSERT INTO "+car.getId()+"  VALUES ('"+car.getName()+"', '"+car.getOwner()+"', '"+(int)car.getHandlingTime()+"');";
            stmt.execute(query);
            logger.info("Data about "+car.getName()+"writed in database");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Properties prop = Main.loadPropertiesFile("./src/main/resources/database.properties");
        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String username = prop.getProperty("MYSQLJDBC.username");
        String password = prop.getProperty("MYSQLJDBC.password");
        Class.forName(driverClass);
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }

    /**
     * вызывает один из трех перечисленных методов, в щависимости от типа записи данных
     * @param car
     */
    public static void writeData(Car car){
        switch (car.getDataIndicator()){
            case CONSOLE:
                writeInConsole(car);
                break;
            case XML:
                writeInXML(car);
                break;
            case DATABASE:
                writeInDateBase(car);
                break;
        }
    }
}
