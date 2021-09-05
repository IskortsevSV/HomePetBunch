package dao;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class UserDAO extends AbstractDAO {

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

    private static UserDAO instance;

    private Set<User> allUsers;

    private UserDAO() {
        allUsers = new HashSet<User>();
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public int addUser(User user) {
        LOG.info("Method addUser: {}", user.getName());
        Connection connection = null;
        PreparedStatement statement = null;
        int resultAdded = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO user(name, password, admin) VALUES (?,?,?)");

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isAdmin());

            resultAdded = statement.executeUpdate();

            ResultSet rs = statement.executeQuery("SELECT MAX(id) FROM user");
            rs.next();
            int userId = rs.getInt(1);
            user.setId(userId);


        } catch (SQLException e) {
            LOG.error("Method addUser: ERROR");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultAdded;
    }

    public Set<User> getAllUsers() {
        LOG.info("Method getAllUsers");
        Set<User> users = new HashSet<User>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM user");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                boolean admin = rs.getBoolean("admin");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setPassword(password);
                user.setAdmin(admin);
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error("Method getAllUsers: ERROR");
            e.printStackTrace();
        }
        return users;
    }

    public User getUser(String name, String password) {
        LOG.info("Method getUser {}", name);
        User user = null;

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT id, admin FROM user" +
                    " WHERE password = ? AND name = ?");

            statement.setString(1, password);
            statement.setString(2, name);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                boolean admin = rs.getBoolean(2);
                user = new User();
                user.setId(id);
                user.setName(name);
                user.setPassword(password);
                user.setAdmin(admin);
            }

        } catch (SQLException e) {
            LOG.error("Method getUser: ERROR");
            e.printStackTrace();
        }
        return user;
    }

}
