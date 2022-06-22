package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.ocsp.Req;
import org.python.antlr.base.boolop;

import com.ibm.icu.text.SimpleDateFormat;

import manager.KorisnikManager;
import manager.VoznjaManager;
import model.Korisnik;
import model.Voznja;

/**
 * Servlet implementation class Reservatin
 */
@WebServlet("/Reservatin")
public class Reservatin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ULOGA_VOZAC = "vozac";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reservatin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ReservationJSP.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String idKorisnik = request.getParameter("idKorisnik");
//		int idK = Integer.parseInt(idKorisnik);
		String telefonMob = request.getParameter("tel");
		String email = request.getParameter("email");
		String adresaPolazak = request.getParameter("adresaPolazak");
		String adresaCilj = request.getParameter("adresaDolazak");
	//	Date vremePolazak = java.util.Calendar.getInstance().getTime(); 
//		Korisnik k = (Korisnik) (request.getSession().getAttribute("korisnik"));// Ovako se vadi korisnik iz sesije
		Date vremePolazak = new Date();
		
		VoznjaManager vm = new VoznjaManager();
			boolean voznja = vm.createVoznja(adresaPolazak, vremePolazak, adresaCilj, telefonMob);
			
			System.out.println(voznja);
			if(voznja) {
				request.getSession().setAttribute("message", "Voznja je kreirana.");
				//saljem dispeceru da proveri najblizeg vozaca i dodeli voznju
//				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/AssignDispecerJSP.jsp");
//				rd.forward(request, response);
				response.sendRedirect(request.getContextPath() + "/AssignDispecer"); //	radi redirekciju na servlet

				
			}
			else {
				request.getSession().setAttribute("message", "Voznja nije kreirana.");
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ReservationJSP.jsp");
				rd.forward(request, response);
//				doGet(request, response);
			}
			

		
	}

}
