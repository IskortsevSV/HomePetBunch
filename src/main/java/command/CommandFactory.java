package command;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class CommandFactory {
    private static final Logger LOG = LoggerFactory.getLogger(CommandFactory.class);

    private static CommandFactory factory = new CommandFactory();


    private Map<String, Command> comands = new HashMap<String, Command>();

    private CommandFactory() {

    }

    public static CommandFactory commandFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    {
        comands.put("register", new RegisterCommand());
        comands.put("logout", new LogoutCommand());
        comands.put("main", new MainCommand());
        comands.put("create_new_bunch", new CreateBunchCommand());
        comands.put("login", new LoginCommand());
        comands.put("add_to_bunch", new AddToBunchCommand());
        comands.put("add_flower", new AddFlowerToBunchCommand());
        comands.put("bunch_flowers", new BunchFlowersCommand());
        comands.put("remove_flower", new RemoveFlowerCommand());
        comands.put("add_new_flower_to_exists", new AddNewFlowerToExists());
    }

    public Command getCommand(HttpServletRequest request) {
        LOG.info("Command getCommand");
        String action = request.getParameter("action");
        Command command = comands.get(action);
        return command;
    }

}
