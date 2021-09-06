import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;


public class ReqestWrapper extends AbstractRequestWrapper {

    private Map<String, String > params = new HashMap<String, String>();
    private HttpSession session = new SessionWrapper();

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public HttpSession getSession() {
        return session;
    }

    public String getParameter(String s) {
        return params.get(s);
    }


}
