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
import domain.Destination;
import domain.User;

/**
 * Servlet implementation class CustomerViewAddressesController
 */
@WebServlet("/CustomerViewAddressesController")
public class CustomerViewAddressesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerViewAddressesController() {
        super();
        // TODO Auto-generated constructor stub
    }

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
                List<Destination> addresses = CustomerServices
                        .getAllDestinationsServices(user);

                String view = "/CustomerAddressList.jsp";
                request.setAttribute("user_id", user_id);
                request.setAttribute("addresses", addresses);
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
