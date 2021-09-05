package command;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;


public class RegisterCommand implements Command {
    private static final Logger LOG = LoggerFactory.getLogger(RegisterCommand.class);

    public String execute(HttpServletRequest request) {
         LOG.info("RegisterCommand execute");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
        System.out.println(admin);

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setAdmin(admin);

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        Set<User> users = userDAO.getAllUsers();
        int add = 0;

        //проверяем если данного юзера нет в списке (проверка по имени)
        if (!users.contains(user)) {
            add = userDAO.addUser(user);
            System.out.println(add + " size " + userDAO.getAllUsers().size());
        }

        if (add == 0) {
            request.setAttribute("notAdd", "This user exists");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }

        String result = (add == 0) ? "register.jsp" : "controller?action=main";
        return result;
    }

}
