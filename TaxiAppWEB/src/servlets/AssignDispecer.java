package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.python.antlr.base.boolop;

import helper.ListaSlobodnihVoznji;
import helper.listaSlobodnihVozaca;
import manager.KorisnikManager;
import manager.VoznjaManager;
import model.Korisnik;
import model.Voznja;

/**
 * Servlet implementation class AssignDispecer
 */
@WebServlet("/AssignDispecer")
public class AssignDispecer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AssignDispecer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("assign dispecer DoGet metoda");
		VoznjaManager vm = new VoznjaManager();
		List<Voznja> listaSlobodneVoznja = vm.findAllSlobodnaVoznja();
//		List<ListaSlobodnihVoznji> listaSlobodneVoznja = vm.findAllSlobodnoVoznja();
		
		request.getSession().setAttribute("listaSlobodneVoznja", listaSlobodneVoznja);
//		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/AssignDispecerJSP.jsp");
//		rd.forward(request, response);

		List<listaSlobodnihVozaca> listaSlobodnihVozaca = vm.findAllSlobodanVozac();
		request.getSession().setAttribute("listaSlobodnihVozaca", listaSlobodnihVozaca);
		RequestDispatcher rd1 = request.getServletContext().getRequestDispatcher("/AssignDispecerJSP.jsp");
		rd1.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("assign dispecer DoGet metoda");
		String id_korisnik = request.getParameter("id_korisnik");
		int idKorisnik = Integer.parseInt(id_korisnik);
		String id_voznja = request.getParameter("id_voznja");
		int idVoznja = Integer.parseInt(id_voznja);
		
		System.out.println("Id voznje je: " + idVoznja);
		
		System.out.println("Id korisnika je: " + idKorisnik);
		
		VoznjaManager vm = new VoznjaManager();
		boolean zauzeto = VoznjaManager.assignVoznja(idVoznja, idKorisnik);
		if (zauzeto) {
			request.getSession().setAttribute("poruka", "Uspesna rezervacija");
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ZavrsiVoznjuJSP.jsp");
			rd.forward(request, response);
		} else {
			request.getSession().setAttribute("poruka", "Greska! Pokusajte ponovo");
		}

		
		 List<Voznja> listaSlobodneVoznja = vm.findAllSlobodnaVoznja();
		 request.getSession().setAttribute("listaSlobodneVoznja",
		 listaSlobodneVoznja);
		 

		List<listaSlobodnihVozaca> listaSlobodnihVozaca = vm.findAllSlobodanVozac();
		request.getSession().setAttribute("listaSlobodnihVozaca", listaSlobodnihVozaca);
		RequestDispatcher rd1 = request.getServletContext().getRequestDispatcher("/AssignDispecerJSP.jsp");
		rd1.forward(request, response);

	}

}
