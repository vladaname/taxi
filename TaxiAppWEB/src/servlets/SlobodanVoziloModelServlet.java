package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import helper.SlobodanVoziloModelLista;
import manager.VoznjaManager;

/**
 * Servlet implementation class SlobodanVoziloModelServlet
 */
@WebServlet("/SlobodanVoziloModelServlet")
public class SlobodanVoziloModelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlobodanVoziloModelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VoznjaManager vm = new VoznjaManager();
		List<SlobodanVoziloModelLista> list = vm.findAllSlobodnoVozilo();
		request.getSession().setAttribute("slobodnoVozilo", list);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/SlobodanVoziloModelListaJSP.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idKorisnik = request.getParameter("idKorisnik");
		int idK = Integer.parseInt(idKorisnik);
		System.out.println(idK);
		request.getSession().setAttribute("idK", idK);
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ReservationJSP.jsp");
		rd.forward(request, response);
		
	}

}
