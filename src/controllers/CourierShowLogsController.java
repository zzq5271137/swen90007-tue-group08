package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.IdentityMap;
import domain.Courier;
import domain.CourierLog;
import domain.User;

/**
 * Servlet implementation class CourierShowLogsController
 */
@WebServlet("/CourierShowLogsController")
public class CourierShowLogsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourierShowLogsController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = new Courier();
        user = IdentityMap.getInstance(user).get(user_id);
        List<CourierLog> myLogs = ((Courier) user).getMyLogs();

        HttpSession session = request.getSession();
        session.setAttribute("user_id", user_id);
        session.setAttribute("myLogs", myLogs);

        response.sendRedirect(
                request.getContextPath() + "/CourierLogList.jsp");
    }

}
