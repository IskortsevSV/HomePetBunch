package command;

import dao.BunchDAO;
import dao.DAOFactory;
import entity.Bunch;
import entity.Flower;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class BunchFlowersCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(BunchFlowersCommand.class);

    public String execute(HttpServletRequest request) {
         LOG.info("BunchFlowersCommand execute");
        int bunchId = Integer.parseInt(request.getParameter("id"));
        System.out.println(bunchId);
        User currentUser = (User) request.getSession().getAttribute("user");
        DAOFactory factory = DAOFactory.getInstance();
        BunchDAO bunchDAO = factory.getBunchDAO();
        Bunch currentBunch = bunchDAO.bunchById(bunchId, currentUser);
        System.out.println(currentBunch.getId());
        List<Flower> flowers = bunchDAO.getFlowersToBunch(currentBunch);
        request.setAttribute("flowers", flowers);
        request.setAttribute("id", bunchId);
        return "bunch_flowers.jsp";
    }

}
