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
 * Servlet implementation class CourierFinishDeliverController
 */
@WebServlet("/CourierFinishDeliverController")
public class CourierFinishDeliverController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourierFinishDeliverController() {
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
                User user = AppSession.getUser();
                List<Order> orders = CourierServices
                        .getAllDeliveringOrdersService(user);

                String view = "/CourierDeliveringOrderList.jsp";
                request.setAttribute("user_id", user.getUser_id());
                request.setAttribute("orders", orders);
                RequestDispatcher requestDispatcher = servletContext
                        .getRequestDispatcher(view);
                requestDispatcher.forward(request, response);
            } else {
                // invalid authorization
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
            if (AppSession.hasRole(AppSession.COURIER_ROLE)) {
                User user = AppSession.getUser();
                int order_id = Integer
                        .parseInt(request.getParameter("order_id"));
                CourierServices.finishDeliverService(user, order_id);

                String view = "/CourierFinishDeliverSuccess.jsp";
                request.setAttribute("user_id", user.getUser_id());
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
