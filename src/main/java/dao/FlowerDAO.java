package dao;

import entity.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FlowerDAO extends AbstractDAO {
    private static final Logger LOG = LoggerFactory.getLogger(FlowerDAO.class);

    private static FlowerDAO instance;

    private FlowerDAO() {

    }

    public static FlowerDAO getInstance() {
        if (instance == null) {
            instance = new FlowerDAO();
        }
        return instance;
    }

    public void addFlower(Flower flower) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultAdded = 0;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO flowers (mark) VALUES (?)");

            statement.setString(1, flower.getName());

            resultAdded = statement.executeUpdate();
            System.out.println("Result values: " + resultAdded);


        } catch (SQLException exception) {
            exception.printStackTrace();
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


    }


    public void addFlowerExists(Flower flower) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultAdded = 0;
        try {
            connection = getConnection();

            int markId = getMarkId(flower.getName(), connection);

            if (markId == -1) {
                statement =
                        connection.prepareStatement(
                                "INSERT INTO flowers.flowers" +
                                        "(mark) " +
                                        "VALUES (?)");
                statement.setString(1, flower.getName());
                //находим новое полученное значение
                statement = connection.prepareStatement("SELECT MAX(id) FROM flowers");
                //получаем его результат
                ResultSet rs = statement.executeQuery();
                rs.next();
                //присваеваем марке новое id добавленной марки
                markId = rs.getInt(1);
            }

            statement =
                    connection.prepareStatement(
                            "INSERT INTO flowers_exists " +
                                    "(mark_id, price, length_steak, ice_level) " +
                                    "VALUES (?,?,?,?)");
            statement.setInt(1, markId);
            statement.setDouble(2, flower.getPrice());
            statement.setInt(3, flower.getLengthSteack());
            statement.setInt(4, flower.getIceLevel());
            resultAdded = statement.executeUpdate();
            LOG.info("Result values: {}", resultAdded);

        } catch (SQLException exception) {
            exception.printStackTrace();
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


    }

    private int getMarkId(String markName, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM flowers WHERE mark = ? ");
            preparedStatement.setString(1, markName);

            //получем результат по совпадению
            ResultSet rs = preparedStatement.executeQuery();

            //возврашаем полученное значение если оно есть
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // иначе получаем отрецательный результат
        return -1;
    }

    public List<Flower> getAll() {
        LOG.info("Method getAll");
        Connection connection = null;
        Statement statement = null;
        List<Flower> flowers = new ArrayList<Flower>();

        try {
            connection = getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT f.id, fl.mark, f.price, " +
                    " f.ice_level, f.length_steak " +
                    "FROM flowers_exists f INNER JOIN flowers fl ON f.mark_id = fl.id WHERE f.bunch_id iS NULL ");

            while (rs.next()) {
                int id = rs.getInt(1);
                String mark = rs.getString(2);
                double price = rs.getDouble(3);
                int lengthSteack = rs.getInt(5);
                int iceLevel = rs.getInt(4);
                Flower flower = new Flower();
                flower.setId(id);
                flower.setName(mark);
                flower.setPrice(price);
                flower.setIceLevel(iceLevel);
                flower.setLengthSteack(lengthSteack);
                flowers.add(flower);
            }

        } catch (SQLException e) {
            LOG.error("Method getAll: ERROR");
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

        return flowers;
    }

    public void removeFlower(Flower flower) {
        LOG.info("Method removeFlower: {}", flower.getName());
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM flowers_exists WHERE id = ?");

            statement.setInt(1, flower.getId());

            int result = statement.executeUpdate();
            LOG.info("RemoveFlower result: {}", result);
            System.out.println(result);
        } catch (SQLException e) {
            LOG.error("Method removeFlower: ERROR");
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
    }

}
