package command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Asus on 06.02.2018.
 */
public interface Command {

    String execute(HttpServletRequest request);

}
