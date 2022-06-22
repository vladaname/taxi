package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ListaSlobodnihVoznji;
import manager.VoznjaManager;

/**
 * Servlet implementation class SlobodneVoznje
 */
@WebServlet("/SlobodneVoznje")
public class SlobodneVoznje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlobodneVoznje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VoznjaManager vm = new VoznjaManager();
		List<ListaSlobodnihVoznji> lista = vm.findAllSlobodnoVoznja();
		request.getSession().setAttribute("ListaSlobodnihVoznji", lista);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/CreateVoznjaJSP.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
