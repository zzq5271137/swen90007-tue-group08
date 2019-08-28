package authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -8039083216034335532L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println("Hello from GET method.");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter pw = response.getWriter();
        pw.println("<h1>Hello from GET.</h1>");
        pw.println("<h3>username: " + username + "</h3><h3>password: " + password + "</h3>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println("Hello from POST method.");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String correctUser = getServletConfig().getInitParameter("usernameI");
        String correctPass = getServletConfig().getInitParameter("passwordI");

        PrintWriter pw = response.getWriter();
        if (username.equals(correctUser) && password.equals(correctPass)) {
            // pw.println("<h1>Hello from POST.</h1>");
            // pw.println("<h3>username: " + username + "</h3><h3>password: " + password + "</h3>");
            response.sendRedirect("success.jsp");
        } else {
            pw.println("<h3> Error </h3>");
        }
    }
}
