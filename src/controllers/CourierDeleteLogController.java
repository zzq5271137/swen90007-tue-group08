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

import authentication.AppSession;
import datasource.IdentityMap;
import domain.Courier;
import domain.CourierLog;
import domain.Order;
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

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (AppSession.isAuthenticated() && AppSession.getUser() != null) {
            if (AppSession.hasRole(AppSession.COURIER_ROLE)) {
                String view = "/CourierLogList.jsp";
                User user = AppSession.getUser();

                List<CourierLog> myLogs = ((Courier) user).getMyLogs();
                request.setAttribute("user_id", user.getUser_id());
                request.setAttribute("myLogs", myLogs); // ????
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
                String view = "/CourierDeleteLogSuccess.jsp";
                User user = AppSession.getUser();

                String date = request.getParameter("date");
                ((Courier) user).deleteLog(date);

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
