package security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import domain.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String view = "/Login.jsp";

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext
                .getRequestDispatcher(view);
        requestDispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String view = "/CustomerLogin.jsp";
        // check whether the username is valid or not
        InterceptingValidator vadilator = new InterceptingValidator();

        if (!vadilator.checkLoginParam(username, password)) {
            view = "/LoginInvalid.jsp";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext
                    .getRequestDispatcher(view);
            requestDispatcher.forward(request, response);
        } else {
            UsernamePasswordToken token = new UsernamePasswordToken(username,
                    password);
            System.out.println("the password in the token is: "
                    + token.getCredentials().toString());
            token.setRememberMe(true);
            Subject currentUser = SecurityUtils.getSubject();

            try {
                // Authenticate the subject by passing the username and password token into the
                // login method
                currentUser.login(token);
                User user = User.getUser(username);
                AppSession.init(user);
                if (user.getUser_type().equals(AppSession.CUSTOMER_ROLE)) {
                    view = "/CustomerLoginSuccess.jsp";
                } else {
                    view = "/CourierLoginSuccess.jsp";
                }
            } catch (UnknownAccountException
                    | IncorrectCredentialsException e) {
                view = "/LoginFailed.html";
            } finally {
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext
                        .getRequestDispatcher(view);
                requestDispatcher.forward(request, response);
            }
        }
    }
}
