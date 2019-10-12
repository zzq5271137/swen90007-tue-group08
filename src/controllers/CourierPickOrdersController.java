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
import service.CourierServices;
import domain.Order;
import domain.User;

/**
 * Servlet implementation class CourierPickOrdersController
 */
@WebServlet("/CourierPickOrdersController")
public class CourierPickOrdersController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourierPickOrdersController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * If the user uses url to access this page,
     * 1. no user is authenticated => redirect to login page. 
     * Otherwise, this will invoke its doPost method.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (AppSession.isAuthenticated() && AppSession.getUser() != null) {
            doPost(req, resp);
        } else {
            resp.sendRedirect("Login.jsp");
        }
    }

    /**
     * If the user is authenticated and authorized, 
     * it will display all the "Confirmed" order after the 
     * courier clicks the "pick new orders" button.
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated() && AppSession.getUser() != null) {
            if (AppSession.hasRole(AppSession.COURIER_ROLE)) {
                User user = AppSession.getUser();
                List<Order> orders = CourierServices
                        .inspectAllNewOrdersService(user);

                String view = "/CourierInspectAllNewOrders.jsp";
                request.setAttribute("user_id", user.getUser_id());
                request.setAttribute("orders", orders);
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
