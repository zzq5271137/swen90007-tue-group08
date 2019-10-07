package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.IdentityMap;
import domain.Courier;
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
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = new Courier();
        user = IdentityMap.getInstance(user).get(user_id);
        String date = request.getParameter("date");
        ((Courier) user).deleteLog(date);

        HttpSession session = request.getSession();
        session.setAttribute("user_id", user_id);
        response.sendRedirect(
                request.getContextPath() + "/CourierDeleteLogSuccess.jsp");
    }

}
