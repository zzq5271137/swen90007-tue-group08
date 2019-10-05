package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.IdentityMap;
import domain.Customer;
import domain.Destination;
import domain.User;

/**
 * Servlet implementation class CustomerViewAddresses
 */
@WebServlet("/CustomerViewAddresses")
public class CustomerViewAddresses extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerViewAddresses() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = new Customer();
        user = IdentityMap.getInstance(user).get(user_id);
        List<Destination> addresses = ((Customer) user).getDestinations();
        request.setAttribute("user_id", user_id);
        request.setAttribute("addresses", addresses);
        // request.getRequestDispatcher("CustomerAddressList.jsp")
        // .forward(request, response);

        // check session
        HttpSession session = request.getSession();
        session.setAttribute("user_id", user_id);
        session.setAttribute("addresses", addresses);

        response.sendRedirect(
                request.getContextPath() + "/CustomerAddressList.jsp");
    }

}
