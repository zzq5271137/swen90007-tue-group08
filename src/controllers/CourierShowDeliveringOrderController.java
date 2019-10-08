package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
 * Servlet implementation class CourierShowDeliveringOrderController
 */
@WebServlet("/CourierShowDeliveringOrderController")
public class CourierShowDeliveringOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourierShowDeliveringOrderController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	ServletContext servletContext = getServletContext();
    	if(AppSession.isAuthenticated()&& AppSession.getUser()!=null) {
    		if(AppSession.hasRole(AppSession.COURIER_ROLE)) {
    			String view = "/CourierDeliveringOrderList.jsp";
                User user = AppSession.getUser();
                
                List<Order> orders = user.getAllOrders();
                request.setAttribute("user_id", user.getUser_id());
                request.setAttribute("orders", orders);
                RequestDispatcher requestDispatcher = servletContext
                        .getRequestDispatcher(view);
                requestDispatcher.forward(request, response);
    		}else {
                response.sendError(403);
            }
    	}else {
            response.sendError(401);
        }
    }
}
