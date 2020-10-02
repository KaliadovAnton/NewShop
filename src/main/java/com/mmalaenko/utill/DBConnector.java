package com.mmalaenko.utill;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    private static Properties props;

    private static final String DATABASES_PROPERTIES = "../../resources/database.properties";

    private static final String JDBC_URL = "jdbc.url";

    private static final String JDBC_NAME = "jdbc.name";

    private static final String JDBC_PASSWORD = "jdbc.password";

   static  {

        props = new Properties();
        InputStream inp = ClassLoader.class.getResourceAsStream(DATABASES_PROPERTIES);
        if (inp != null) {
            try {
                props.load(inp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  static   Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            String url ="jdbc:h2:mem:onlineShop;DB_CLOSE_DELAY=-1"; //props.getProperty(JDBC_URL);
            String name = "";//props.getProperty(JDBC_NAME);
            String password ="";// props.getProperty(JDBC_PASSWORD);
            return DriverManager.getConnection(url, name, password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

