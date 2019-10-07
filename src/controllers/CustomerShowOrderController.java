package controllers;

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
import domain.Order;
import domain.User;

/**
 * Servlet implementation class CustomerShowOrderController
 */
@WebServlet("/CustomerShowOrderController")
public class CustomerShowOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerShowOrderController() {
        super();
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
        List<Order> orders = user.getAllOrders();
        // request.setAttribute("user_id", user_id);
        // request.setAttribute("orders", orders);
        // request.getRequestDispatcher("CustomerOrderList.jsp")
        // .forward(request, response);

        // check session
        HttpSession session = request.getSession();
        session.setAttribute("user_id", user_id);
        session.setAttribute("orders", orders);

        response.sendRedirect(
                request.getContextPath() + "/CustomerOrderList.jsp");
    }
}
