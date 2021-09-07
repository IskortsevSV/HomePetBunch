package command;

import dao.BunchDAO;
import dao.DAOFactory;
import entity.Bunch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class RemoveBunchCommand implements Command{

    private static final Logger LOG = LoggerFactory.getLogger(RemoveBunchCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("RemoveBunchCommand execute");
        int bunchId = Integer.valueOf(request.getParameter("bunch_id"));
        DAOFactory daoFactory = DAOFactory.getInstance();
        BunchDAO bunchDAO = daoFactory.getBunchDAO();
        Bunch bunch = new Bunch();
        bunch.setId(bunchId);
        bunchDAO.removeBunch(bunch);
        return "controller?action=main";
    }
}
