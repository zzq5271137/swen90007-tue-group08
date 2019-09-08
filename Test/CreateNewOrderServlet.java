import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datasource.UserMapper;
import domain.User;

@WebServlet("/CreateNewOrderServlet")
public class CreateNewOrderServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
		// read form fields
		String destination = request.getParameter("destination");
		String item_weight = request.getParameter("item_weight");
		String item_size = request.getParameter("item_size");

		System.out.println(destination + "," + item_weight + "," + item_size);

		// do some processing here...

		// get response writer
		PrintWriter writer = response.getWriter();
    }
}