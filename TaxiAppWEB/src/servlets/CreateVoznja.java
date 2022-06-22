package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.VoziloManager;
import manager.VoznjaManager;
import model.Vozilo;
import model.Voznja;

/**
 * Servlet implementation class CreateVoznja
 */
@WebServlet("/CreateVoznja")
public class CreateVoznja extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateVoznja() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VoziloManager vz = new VoziloManager();
		List<Vozilo> slobodniTaxi = vz.findAllSlobodnaVozila();
		request.getSession().setAttribute("slobodniTaxi", slobodniTaxi);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/CreateVoznjaJSP.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id_vozilo = request.getParameter("id_vozilo");
		int idVozilo = Integer.parseInt(id_vozilo);
		
		VoziloManager vz = new VoziloManager();
		boolean zauzetoVozilo = vz.deleteVozilo(idVozilo);
		
		List<Vozilo> slobodniTaxi = vz.findAllSlobodnaVozila();
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/CreateVoznjaJSP.jsp");
		rd.forward(request, response);
		
		
		
		
		
	}

}
