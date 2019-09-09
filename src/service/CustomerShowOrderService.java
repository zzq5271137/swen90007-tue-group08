package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = new Customer();
        user = IdentityMap.getInstance(user).get(user_id);
        List<Order> orders = user.getOrders();
        
        PrintWriter out = response.getWriter();
        out.println("<h1>" + user.getUser_id() + "</h1>");
    }
}
