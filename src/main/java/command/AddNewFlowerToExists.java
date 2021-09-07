package command;

import dao.DAOFactory;
import dao.FlowerDAO;
import entity.Flower;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AddNewFlowerToExists implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(AddNewFlowerToExists.class);

    public String execute(HttpServletRequest request) {
        LOG.info("AddNewFlowerToExists execute");
        User user = (User) request.getSession().getAttribute("user");
        if(user.isAdmin()) {
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int iceLevel = Integer.parseInt(request.getParameter("iceLevel"));
            int lengthSteack = Integer.parseInt(request.getParameter("lengthSteack"));
            Flower flower = new Flower(name, price, lengthSteack, iceLevel);
            DAOFactory daoFactory = DAOFactory.getInstance();
            FlowerDAO flowerDAO = daoFactory.getFlowerDAO();
            flowerDAO.addFlowerExists(flower);
        }
        return "controller?action=main";
    }
}
