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
import security.InterceptingValidator;
import service.CustomerServices;
import domain.Order;
import domain.User;

/**
 * Servlet implementation class CustomerConfirmNewOrderController
 */
@WebServlet("/CustomerConfirmNewOrderController")
public class CustomerConfirmNewOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerConfirmNewOrderController() {
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
                // get the parameters from request
                String item_size_string = request.getParameter("item_size");
                String item_weight_string = request
                        .getParameter("item_weight");
                String address = request.getParameter("address");

                // check whether the inputs are valid or not
                InterceptingValidator vadilator = new InterceptingValidator();
                String view = null;

                if (!vadilator.checkOrderParam(item_size_string,
                        item_weight_string, address)) {
                    view = "/InputInvalid.jsp";
                } else {
                    float item_size = Float.parseFloat(item_size_string);
                    float item_weight = Float.parseFloat(item_weight_string);

                    User user = AppSession.getUser();
                    int user_id = user.getUser_id();

                    CustomerServices.createNewOrderService(user, item_size,
                            item_weight, address);

                    request.setAttribute("user_id", user_id);
                    view = "/CustomerNewOrderSuccess.jsp";
                }
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
