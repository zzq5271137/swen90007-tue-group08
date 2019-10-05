package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.IdentityMap;
import domain.Customer;
import domain.User;

/**
 * Servlet implementation class CustomerConfirmNewOrderService
 */
@WebServlet("/CustomerConfirmNewOrderService")
public class CustomerConfirmNewOrderService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerConfirmNewOrderService() {
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
        float item_size = Float.parseFloat(request.getParameter("item_size"));
        float item_weight = Float
                .parseFloat(request.getParameter("item_weight"));
        String address = request.getParameter("address");
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = new Customer();
        user = IdentityMap.getInstance(user).get(user_id);
        ((Customer)user).CreateNewOrder(item_size, item_weight, address);
        // request.setAttribute("user_id", user_id);
        // request.getRequestDispatcher("CustomerNewOrderSuccess.jsp")
        // .forward(request, response);

        // check session
        HttpSession session = request.getSession();
        session.setAttribute("user_id", user_id);

        response.sendRedirect(
                request.getContextPath() + "/CustomerNewOrderSuccess.jsp");
    }
}
