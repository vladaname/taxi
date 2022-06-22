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

import helper.ListaZauzetihVoznji;
import helper.SumZarade;
import manager.CenovnikManager;
import manager.KorisnikManager;
import manager.VoznjaManager;
import model.Cenovnik;
import model.Korisnik;

/**
 * Servlet implementation class ListAllIdZauzetoVoznja
 */
@WebServlet("/ListAllIdZauzetoVoznja")
public class ListAllIdZauzetoVoznja extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static final String ULOGA_VOZAC = "vozac";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllIdZauzetoVoznja() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		VoznjaManager vm = new VoznjaManager();
		
		
		List<ListaZauzetihVoznji> zauzeto = vm.findAllZauzetoVoznja();
		request.getSession().setAttribute("ListaZauzetihVoznji", zauzeto);
//		Korisnik k = (Korisnik) (request.getSession().getAttribute("korisnik"));
		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
//		if(k.getUloga().getNazivUloga().equals(ULOGA_VOZAC)) {
//			KorisnikManager km = new KorisnikManager();
//			SumZarade sum = km.sumZaradeKorisnika(k.getIdKorisnik());
//			String zarada = k.getIme() + " Vasa ukupna zarada je: " + sum.getUkupan_racun();
//			request.getSession().setAttribute("zarada", zarada);
//		}
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ListAllIdZauzetoVoznja.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idVoznja = request.getParameter("idVoznja");
		int idV = Integer.parseInt(idVoznja);
		System.out.println(idV);
		request.getSession().setAttribute("idVoznja" , idVoznja);
		request.getSession().setAttribute("message", "Zavrsite voznju.");
		
		CenovnikManager cm = new CenovnikManager();
		Cenovnik aktuelniCenovnik = cm.aktuelniCenovnik();
		request.getSession().setAttribute("aktuelniCenovnik", aktuelniCenovnik);
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ZavrsiVoznjuJSP.jsp");
		rd.forward(request, response);
		
	}

}
