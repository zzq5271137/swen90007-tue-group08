package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import authentication.AppSession;
import datasource.IdentityMap;
import domain.Courier;
import domain.CourierLog;
import domain.Order;
import domain.User;

/**
 * Servlet implementation class CourierConfirmPickOrderController
 */
@WebServlet("/CourierConfirmPickOrderController")
public class CourierConfirmPickOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourierConfirmPickOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated() && AppSession.getUser() != null) {
            if (AppSession.hasRole(AppSession.COURIER_ROLE)) {
                String view = "/CourierInspectAllNewOrders.jsp";
                User user = AppSession.getUser();

                List<Order> orders = ((Courier) user).inspectAllNewOrders();
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

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated()) {
            if (AppSession.hasRole(AppSession.COURIER_ROLE)) {
                String view = "/CourierPickOrderSuccess.jsp";
                User user = AppSession.getUser();

                int order_id = Integer
                        .parseInt(request.getParameter("order_id"));
                try {
                    ((Courier) user).confirmPickOrder(order_id);

                    request.setAttribute("user_id", user.getUser_id());
                    RequestDispatcher requestDispatcher = servletContext
                            .getRequestDispatcher(view);
                    requestDispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                response.sendError(403);
            }
        } else {
            response.sendError(401);
        }
    }

}
