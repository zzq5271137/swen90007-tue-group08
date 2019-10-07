import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datasource.UserMapper;
import domain.User;

@WebServlet("/finduserwithusernametest")
public class FindUserWithUserUsernameTest extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        UserMapper um = new UserMapper();
        String username = "zhengqingz";
        String password = "123456";
        String user_type = User.CUSTOMER_TYPE;
        List<User> users = um.findWithUsernameAndPassword(username, password,
                user_type);
        for (User u : users) {
            out.println("<h1>id: " + u.getUser_id() + "</h1>");
        }
    }
}
