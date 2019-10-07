package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.IdentityMap;
import domain.Customer;
import domain.Order;
import domain.User;

/**
 * Servlet implementation class CustomerDeleteOrderController
 */
@WebServlet("/CustomerDeleteOrderController")
public class CustomerDeleteOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDeleteOrderController() {
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
        String status = request.getParameter("status");
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        // check session
        HttpSession session = request.getSession();
        session.setAttribute("user_id", user_id);

        // request.setAttribute("user_id", user_id);
        if (status.equalsIgnoreCase(Order.SHIPPED_STATUS)) {
            // request.getRequestDispatcher("ShippedOrderCannotChange.jsp")
            // .forward(request, response);
            response.sendRedirect(request.getContextPath()
                    + "/ShippedOrderCannotChange.jsp");
        } else if (status.equalsIgnoreCase(Order.DELIVERED_STATUS)) {
            // request.getRequestDispatcher("DeliveredOrderCannotChange.jsp")
            // .forward(request, response);
            response.sendRedirect(request.getContextPath()
                    + "/DeliveredOrderCannotChange.jsp");
        } else {
            int order_id = Integer.parseInt(request.getParameter("order_id"));
            User user = new Customer();
            user = IdentityMap.getInstance(user).get(user_id);
            ((Customer)user).deleteOrder(order_id);
            // request.getRequestDispatcher("CustomerDeleteOrderSuccess.jsp")
            // .forward(request, response);
            response.sendRedirect(request.getContextPath()
                    + "/CustomerDeleteOrderSuccess.jsp");
        }
    }

}
