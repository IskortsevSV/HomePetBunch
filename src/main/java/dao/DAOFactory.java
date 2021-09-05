package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DAOFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DAOFactory.class);

    private static DAOFactory factory;

    private DAOFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }

    public FlowerDAO getFlowerDAO() {
      LOG.info("Driver load FlowerDAO");
        return FlowerDAO.getInstance();
    }

    public BunchDAO getBunchDAO() {
        LOG.info("Driver load BunchDAO");
        return BunchDAO.getInstance();
    }

    public UserDAO getUserDAO() {
         LOG.info("Driver load UserDAO");
        return UserDAO.getInstance();
    }

}
