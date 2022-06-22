package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.KorisnikManager;
import model.Korisnik;

/**
 * Servlet implementation class ZaradaPoIdKorisnik
 */
@WebServlet("/ZaradaPoIdKorisnik")
public class ZaradaPoIdKorisnik extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZaradaPoIdKorisnik() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ZaradaPoIdKorisnikJSP.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idKorisnik = request.getParameter("idKorisnik");
		int idK = Integer.parseInt(idKorisnik);
		
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		
		KorisnikManager km = new KorisnikManager();
//		String k = km.sumtByIdKorisnik(idK);
		request.getSession().setAttribute("k", idKorisnik);
	}

}
