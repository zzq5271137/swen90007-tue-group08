package authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
        int user_id = -1;
        UserMapper um = new UserMapper();
        
        // if no username is typed, finding user from cookie
        if(username == "") {
        	Cookie[] cookies = request.getCookies();
        	System.out.println("cookies length: "+cookies.length);
        	for(Cookie cook:cookies) {
        		String cookName = cook.getName();
        		if(cookName.equals("name")) {
        			username = cook.getValue();
        		}
        		if(cookName.equals("password")) {
        			password = cook.getValue();
        		}
        	}
        }
        
        List<User> results = um.findWithUsernameAndPassword(username, password,
                User.CUSTOMER_TYPE);

        if (results.size() == 0) {
            response.sendRedirect("LoginFailed.html");
        } else {
            user_id = results.get(0).getUser_id();
            IdentityMap<User> iMap = IdentityMap.getInstance(results.get(0));
            if (iMap.get(user_id) == null) {
                IdentityMap.getInstance(results.get(0)).put(user_id,
                        results.get(0));
            }
            Cookie cookie = new Cookie("name", username);
            Cookie cookiePass = new Cookie("password", password);
            cookie.setMaxAge(60*8);
            cookiePass.setMaxAge(60*8);
            response.addCookie(cookie);
            response.addCookie(cookiePass);
            request.setAttribute("user_id", results.get(0).getUser_id());
            request.getRequestDispatcher("CustomerLoginSuccess.jsp")
                    .forward(request, response);
        }
    }
}
