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

import security.AppSession;
import service.CustomerServices;
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
     * If the user uses url to access this page,
     * 1. the role of user has no permission => authorization error.
     * 2. no user is authenticated => redirect to login page. 
     * Otherwise, the page will transfer to customer's home page.
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated() && AppSession.getUser() != null) {
            if (AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
                User user = AppSession.getUser();
                List<Order> orders = CustomerServices.getAllOrderService(user);

                String view = "/CustomerOrderList.jsp";
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
                String view = null;

                if (status.equalsIgnoreCase(Order.SHIPPED_STATUS)) {
                    view = "/ShippedOrderCannotChange.jsp";
                } else if (status.equalsIgnoreCase(Order.DELIVERED_STATUS)) {
                    view = "/DeliveredOrderCannotChange.jsp";
                } else {
                    int order_id = Integer
                            .parseInt(request.getParameter("order_id"));

                    CustomerServices.deleteOrderService(user, order_id);

                    view = "/CustomerDeleteOrderSuccess.jsp";
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
