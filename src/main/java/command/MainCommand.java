package command;

import dao.BunchDAO;
import dao.DAOFactory;
import dao.FlowerDAO;
import entity.Bunch;
import entity.Flower;
import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class MainCommand implements Command {
//    private static final Logger LOG = LoggerFactory.getLogger(MainCommand.class);

    public String execute(HttpServletRequest request) {
        System.out.println("MainCommand execute()");
//        LOG.info("MainCommand execute");
        DAOFactory factory = DAOFactory.getInstance();
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            BunchDAO bunchDAO = factory.getBunchDAO();
            List<Bunch> bunches = bunchDAO.getAllBunches(user);
            request.setAttribute("bunches", bunches);
        } else {
            FlowerDAO flowerDAO = factory.getFlowerDAO();
            List<Flower> flowers = flowerDAO.getAll();
            request.setAttribute("flowers", flowers);
        }

        return "main.jsp";
    }

}
