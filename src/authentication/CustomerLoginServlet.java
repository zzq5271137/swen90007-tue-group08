package authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datasource.IdentityMap;
import datasource.UserMapper;
import domain.User;

/**
 * Servlet implementation class CustomerLoginServlet
 */
@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLoginServlet() {
        super();
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
        UserMapper um = new UserMapper();
        List<User> results = um.findWithUsernameAndPassword(username, password,
                User.CUSTOMER_TYPE);
        if (results.size() == 0) {
            response.sendRedirect("LoginFailed.html");
        } else {
            int user_id = results.get(0).getUser_id();
            IdentityMap<User> iMap = IdentityMap.getInstance(results.get(0));
            if (iMap.get(user_id) == null) {
                IdentityMap.getInstance(results.get(0)).put(user_id,
                        results.get(0));
            }
            request.setAttribute("user_id", results.get(0).getUser_id());
            request.getRequestDispatcher("CustomerLoginSuccess.jsp")
                    .forward(request, response);
        }
    }
}
