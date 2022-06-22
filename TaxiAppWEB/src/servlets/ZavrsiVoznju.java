package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.ListaZauzetihVoznji;
import manager.CenovnikManager;
import manager.VoznjaManager;
import model.Cenovnik;
import model.Korisnik;

/**
 * Servlet implementation class ZavrsiVoznju
 */
@WebServlet("/ZavrsiVoznju")
public class ZavrsiVoznju extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ZavrsiVoznju() {
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
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnk");
		CenovnikManager cm = new CenovnikManager();
		Cenovnik cenovnik = cm.aktuelniCenovnik();
		double cenaKm = cenovnik.getCena();
		System.out.println(cenaKm);
//		request.getSession().setAttribute("cenaKm", cenaKm);
		double cenaStart = cenovnik.getCenaStart();
//		request.getSession().setAttribute("cenaStart", cenaStart);
		
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ZavrsiVoznjuJSP.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idVoznja = request.getParameter("idVoznja");
		String km = request.getParameter("km");
		int idVoznjaInt = 0;
		int kmInt = 0;
		// String message = "Hello ";

		try {
			idVoznjaInt = Integer.parseInt(idVoznja);
			kmInt = Integer.parseInt(km);
		} catch (Exception e) {
			e.printStackTrace();
			// message +="Nije validan unos kilometara ili Id voznje.";
			request.getSession().setAttribute("message", "Nije validan unos kilometara ili Id voznje.");
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ZavrsiVoznjuJSP.jsp");
			rd.forward(request, response);
		}
		if (idVoznjaInt == 0 || kmInt == 0) {
			// message +="Nije validan unos kilometara ili Id voznje.";
			request.getSession().setAttribute("message", "Nije validan unos kilometara ili Id voznje.");
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ZavrsiVoznjuJSP.jsp");
			rd.forward(request, response);
		} else {

		}

		VoznjaManager vm = new VoznjaManager();
		boolean zavrsiVoznju = vm.updateVoznja(idVoznjaInt, kmInt);
		if (zavrsiVoznju) {
			System.out.println("Usao u if");
			List<ListaZauzetihVoznji> zauzeto = vm.findAllZauzetoVoznja();
			request.getSession().setAttribute("ListaZauzetihVoznji", zauzeto);
			request.getSession().setAttribute("message", "Uspesno!");
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ListAllIdZauzetoVoznja.jsp");
			rd.forward(request, response);
		} else {
			System.out.println("Usao u else");
			// message+="Dogodila se greska";
			request.getSession().setAttribute("message", "Dogodila se greska!");
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ZavrsiVoznjuJSP.jsp");
			rd.forward(request, response);

		}

	}

}
