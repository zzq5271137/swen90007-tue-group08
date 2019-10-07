package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.IdentityMap;
import domain.Courier;
import domain.Order;
import domain.User;

/**
 * Servlet implementation class CourierShowDeliveringOrderController
 */
@WebServlet("/CourierShowDeliveringOrderController")
public class CourierShowDeliveringOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourierShowDeliveringOrderController() {
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
        User user = new Courier();
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
                request.getContextPath() + "/CourierDeliveringOrderList.jsp");
    }
}
