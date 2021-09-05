package command;

import dao.DAOFactory;
import dao.FlowerDAO;
import entity.Flower;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class AddToBunchCommand implements Command {
 //   private static final Logger LOG = LoggerFactory.getLogger(AddToBunchCommand.class);
    public String execute(HttpServletRequest request) {
        System.out.println("AddToBunchCommand execute()");
  //      LOG.info("AddToBunchCommand execute");
        long bunchId = Long.parseLong(request.getParameter("id"));
        DAOFactory factory = DAOFactory.getInstance();
        FlowerDAO flowerDAO = factory.getFlowerDAO();
        List<Flower> flowers = flowerDAO.getAll();
        request.setAttribute("id", bunchId);
        request.setAttribute("flowers", flowers);
        return "add_flowers.jsp";
    }

}
