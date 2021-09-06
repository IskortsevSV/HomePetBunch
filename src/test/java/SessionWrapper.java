import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class SessionWrapper extends AbstractSessionWrapper {

    private Map<String, Object> values = new HashMap<String, Object>();

    public Object getAttribute(String s) {
        return values.get(s);
    }

    public void setAttribute(String s, Object o) {
        values.put(s, o);
    }

    public void removeAttribute(String s) {
        values.remove(s);
    }


}
