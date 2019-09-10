package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datasource.IdentityMap;
import domain.Order;

/**
 * Servlet implementation class CustomerChangeOrderService
 */
@WebServlet("/CustomerChangeOrderService")
public class CustomerChangeOrderService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerChangeOrderService() {
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
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        Order order = new Order();
        order = IdentityMap.getInstance(order).get(order_id);
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        request.setAttribute("user_id", user_id);
        request.setAttribute("order", order);
        request.getRequestDispatcher("CustomerChangeOrderDetail.jsp")
                .forward(request, response);
    }
}
