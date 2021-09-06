import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

/**
 * Created by Asus on 18.06.2018.
 */
public abstract class AbstractSessionWrapper implements HttpSession {

    public long getCreationTime() {
        return 0;
    }

    public String getId() {
        return null;
    }

    public long getLastAccessedTime() {
        return 0;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public void setMaxInactiveInterval(int i) {

    }

    public int getMaxInactiveInterval() {
        return 0;
    }

    public HttpSessionContext getSessionContext() {
        return null;
    }

    public Object getValue(String s) {
        return null;
    }

    public Enumeration<String> getAttributeNames() {
        return null;
    }

    public String[] getValueNames() {
        return new String[0];
    }

    public void putValue(String s, Object o) {

    }

    public void removeValue(String s) {

    }

    public void invalidate() {

    }

    public boolean isNew() {
        return false;
    }

}
