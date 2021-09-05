package command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LogoutCommand implements Command {
   private static final Logger LOG = LoggerFactory.getLogger(LogoutCommand.class);

    public String execute(HttpServletRequest request) {
         LOG.info("LogoutCommand execute");
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "controller?action=main";
    }

}
