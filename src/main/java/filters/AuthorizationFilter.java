package filters;


import javax.servlet.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AuthorizationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        String login = request.getParameter("name");
        String password = request.getParameter("password");


        if ( (login == null && password == null) || (login.length() > 3
                && password.length() > 5 && findExpression("[0-9]", password) ) ) {

            chain.doFilter(request, response);

        } else {
            request.getRequestDispatcher("ex_error.xhtml").forward(request, response);
        }


    }

    private boolean findExpression(String expression, String text) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    public void destroy() {

    }

}
