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

import security.AppSession;
import datasource.IdentityMap;
import domain.Customer;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated() && AppSession.getUser()!=null) {
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
            	float item_size = Float.parseFloat(request.getParameter("item_size"));
                float item_weight = Float
                        .parseFloat(request.getParameter("item_weight"));
                String address = request.getParameter("address");
                
                User user = AppSession.getUser();
                int user_id = user.getUser_id();

                ((Customer)user).CreateNewOrder(item_size, item_weight, address);

                request.setAttribute("user_id", user_id);
                
                String view = "/CustomerNewOrderSuccess.jsp";
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
