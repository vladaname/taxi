package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.SumZarade;
import manager.KorisnikManager;
import model.Korisnik;

/**
 * Servlet implementation class UkupnaZaradaById
 */
@WebServlet("/UkupnaZaradaById")
public class UkupnaZaradaById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UkupnaZaradaById() {
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

		Korisnik k = (Korisnik) request.getSession().getAttribute("korisnik");
		if (k != null) {
			KorisnikManager km = new KorisnikManager();
			SumZarade ukupnaZarada = km.sumZaradeKorisnika(k.getIdKorisnik());
			request.getSession().setAttribute("ukupnaZarada", ukupnaZarada.getUkupan_racun());
		} else {
			request.getSession().setAttribute("ukupnaZarada", 0);
		}
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/UkupnaZaradaByIdJSP.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
