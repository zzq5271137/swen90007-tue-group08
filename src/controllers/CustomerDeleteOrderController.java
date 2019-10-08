package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
import security.AppSession;

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

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated() && AppSession.getUser() != null) {
            if (AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
                String view = "/CustomerOrderList.jsp";
                User user = AppSession.getUser();
                List<Order> orders = user.getAllOrders();

                request.setAttribute("user_id", user.getUser_id());
                request.setAttribute("orders", orders);

                RequestDispatcher requestDispatcher = servletContext
                        .getRequestDispatcher(view);
                requestDispatcher.forward(request, response);
            } else {
                response.sendError(403);
            }
        } else {
            response.sendRedirect("Login.jsp");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        if (AppSession.isAuthenticated()) {
            if (AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
                String status = request.getParameter("status");
                User user = AppSession.getUser();
                int user_id = user.getUser_id();

                String view = "/CustomerOrderList.jsp";

                if (status.equalsIgnoreCase(Order.SHIPPED_STATUS)) {
                    view = "/ShippedOrderCannotChange.jsp";
                } else if (status.equalsIgnoreCase(Order.DELIVERED_STATUS)) {
                    view = "/DeliveredOrderCannotChange.jsp";
                } else {
                    int order_id = Integer
                            .parseInt(request.getParameter("order_id"));

                    ((Customer) user).deleteOrder(order_id);
                    view = "/CustomerOrderChangeSuccess.jsp";
                }
                request.setAttribute("user_id", user_id);

                RequestDispatcher requestDispatcher = servletContext
                        .getRequestDispatcher(view);
                requestDispatcher.forward(request, response);
            } else {
                response.sendError(403);
            }
        } else {
            response.sendError(401);
        }

    }

}
