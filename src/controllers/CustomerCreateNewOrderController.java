package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import security.AppSession;
import domain.User;

/**
 * Servlet implementation class CustomerCreateNewOrderService
 */
@WebServlet("/CustomerCreateNewOrderController")
public class CustomerCreateNewOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerCreateNewOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * If the user uses url to access this page,
     * 1. no user is authenticated => redirect to login page. 
     * Otherwise, the page will invoke its doPost method.
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
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated() && AppSession.getUser() != null) {
            if (AppSession.hasRole(AppSession.CUSTOMER_ROLE)) {
                User user = AppSession.getUser();
                int user_id = user.getUser_id();

                String view = "/CustomerNewOrderForm.jsp";
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
