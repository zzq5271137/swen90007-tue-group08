package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.IdentityMap;
import domain.Order;

/**
 * Servlet implementation class CustomerChangeOrderService
 */
@WebServlet("/CustomerChangeOrderService")
public class CustomerChangeOrderService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerChangeOrderService() {
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
            Order order = new Order();
            order = IdentityMap.getInstance(order).get(order_id);
            // request.setAttribute("order", order);
            // request.getRequestDispatcher("CustomerChangeOrderDetail.jsp")
            // .forward(request, response);

            // check session
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user_id);
            session.setAttribute("order", order);

            response.sendRedirect(request.getContextPath()
                    + "/CustomerChangeOrderDetail.jsp");
        }
    }
}
