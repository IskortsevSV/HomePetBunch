package dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public abstract class AbstractDAO implements InterfaceDAO {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDAO.class);
    private final Properties property = new Properties();

    public Connection getConnection() throws SQLException {

        InputStream is = AbstractDAO.class.getClassLoader().getResourceAsStream(
                "db.properties");
        try {
            property.load(is);
        } catch (IOException e) {
            LOG.error("Properties file not found");
            e.printStackTrace();
        }
        String url = property.getProperty("jdbc.url");
        String user = property.getProperty("jdbc.username");
        String password = property.getProperty("jdbc.password");

        LOG.info("Connection to DB ");

        return DriverManager.getConnection(url, user, password);
    }

}
