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
import domain.CourierLog;
import domain.User;

/**
 * Servlet implementation class CourierDeleteLogController
 */
@WebServlet("/CourierDeleteLogController")
public class CourierDeleteLogController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourierDeleteLogController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * If the user uses url to access this page,
     * 1. the role of user has no permission => authorization error.
     * 2. no user is authenticated => redirect to login page. 
     * Otherwise, the page will transfer to "courier log list" page.
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated() && AppSession.getUser() != null) {
            if (AppSession.hasRole(AppSession.COURIER_ROLE)) {
                User user = AppSession.getUser();
                List<CourierLog> myLogs = CourierServices
                        .getCourierLogsService(user);

                String view = "/CourierLogList.jsp";
                request.setAttribute("user_id", user.getUser_id());
                request.setAttribute("myLogs", myLogs);
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
     * If the user is authenticated and authorized, 
     * it will show the "delete log successful" message after the 
     * courier clicks the "delete log" button.
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated()) {
            if (AppSession.hasRole(AppSession.COURIER_ROLE)) {
                User user = AppSession.getUser();
                String date = request.getParameter("date");
                CourierServices.deleteLogService(user, date);

                String view = "/CourierDeleteLogSuccess.jsp";
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
