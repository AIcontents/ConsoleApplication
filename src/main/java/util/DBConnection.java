package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static String url;

    static {
        try (InputStream input = DBConnection.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");

        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки конфигурации БД");
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url);
    }
}
