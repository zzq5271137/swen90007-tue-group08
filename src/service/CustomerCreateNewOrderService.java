package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerCreateNewOrderService
 */
@WebServlet("/CustomerCreateNewOrderService")
public class CustomerCreateNewOrderService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerCreateNewOrderService() {
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
        request.setAttribute("user_id", user_id);
        request.getRequestDispatcher("CustomerNewOrderForm.jsp")
                .forward(request, response);
    }

}
