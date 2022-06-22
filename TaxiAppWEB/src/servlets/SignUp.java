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
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/SignUpJSP.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String telefon = request.getParameter("telefon");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		String passConfirm = request.getParameter("passConfirm");

		String message = "";
		
		if(!pass.equals(passConfirm)) {
			message+="Pogresan password. Ponovite.";
		}
		
		KorisnikManager km = new KorisnikManager();
		if(km.findByEmail(email) != null) {
			message+="Email allredy exist";
		}
		if(km.findByTelefon(telefon) != null) {
			message+="Telfon allredy exist";
		}
		if(!message.equals("")) {
			request.setAttribute("message", "Pokusajte ponovo " + message);
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/SignUp.jsp");
			rd.forward(request, response);
		}
		else {
			km.createKorisnik(passConfirm, email, ime, prezime, telefon, username);
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
		
	}

}
