package dao;

import java.sql.Connection;
import java.sql.SQLException;


public interface InterfaceDAO {

    Connection getConnection() throws SQLException;

}
