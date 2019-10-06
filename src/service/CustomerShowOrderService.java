package service;

import java.io.IOException;
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
import domain.Customer;
import domain.Order;
import domain.User;

/**
 * Servlet implementation class CustomerShowOrderService
 */
@WebServlet("/CustomerShowOrderService")
public class CustomerShowOrderService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerShowOrderService() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 ServletContext servletContext = getServletContext();
         if (AppSession.isAuthenticated()) {
             if (AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
            	 String view = "/CustomerOrderList.jsp";
            	 User user = AppSession.getUser();
            	 List<Order> orders = user.getAllOrders();
            	 
            	 request.setAttribute("user_id", user.getUser_id());
                 request.setAttribute("orders", orders);
            	 
                 RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
                 requestDispatcher.forward(request, response);
             } else {
                 response.sendError(403);
             }
         } else {
        	 response.sendRedirect("LoginFailed.html");
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
                User user = AppSession.getUser();
            	int user_id = user.getUser_id();
                // user = IdentityMap.getInstance(user).get(user_id);
                List<Order> orders = user.getAllOrders();
                
                request.setAttribute("user_id", user_id);
                request.setAttribute("orders", orders);
                String view = "/CustomerOrderList.jsp";
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
                requestDispatcher.forward(request, response);
            }else {
                response.sendError(403);
            }
        }else {
            response.sendError(401);
        }
    }
}
