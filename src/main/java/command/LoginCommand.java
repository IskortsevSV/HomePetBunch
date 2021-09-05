package command;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


public class LoginCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request) {
        LOG.info("LoginCommand execute");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getUser(name, password);
        String resultPage = (user == null) ? "login.jsp" : "controller?action=main";

        if (user == null) {
            request.setAttribute("notExists", "This user not exists");
        } else {
            request.getSession().setAttribute("user", user);
        }

        return resultPage;
    }
}
