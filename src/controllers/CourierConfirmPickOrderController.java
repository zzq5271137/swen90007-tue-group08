package controllers;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class CourierConfirmPickOrderController
 */
@WebServlet("/CourierConfirmPickOrderController")
public class CourierConfirmPickOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourierConfirmPickOrderController() {
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
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        try {
            ((Courier) user).confirmPickOrder(order_id);

            HttpSession session = request.getSession();
            session.setAttribute("user_id", user_id);
            response.sendRedirect(
                    request.getContextPath() + "/CourierPickOrderSuccess.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
