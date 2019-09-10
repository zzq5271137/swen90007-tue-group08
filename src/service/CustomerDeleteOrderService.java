package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datasource.IdentityMap;
import domain.Customer;
import domain.Order;
import domain.User;

/**
 * Servlet implementation class CustomerDeleteOrderService
 */
@WebServlet("/CustomerDeleteOrderService")
public class CustomerDeleteOrderService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDeleteOrderService() {
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
        request.setAttribute("user_id", user_id);
        if (status.equalsIgnoreCase(Order.SHIPPED_STATUS)) {
            request.getRequestDispatcher("ShippedOrderCannotChange.jsp")
                    .forward(request, response);
        } else if (status.equalsIgnoreCase(Order.DELIVERED_STATUS)) {
            request.getRequestDispatcher("DeliveredOrderCannotChange.jsp")
                    .forward(request, response);
        } else {
            int order_id = Integer.parseInt(request.getParameter("order_id"));
            User user = new Customer();
            user = IdentityMap.getInstance(user).get(user_id);
            user.deleteOrder(order_id);
            request.getRequestDispatcher("CustomerDeleteOrderSuccess.jsp")
                    .forward(request, response);
        }
    }

}
