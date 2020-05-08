package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonneDao;
import modele.Personne;

@WebServlet("/sondage")
public class SondageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
  /** Vue si succes */
  private static final String VUE_OK = "WEB-INF/sondage.jsp";
  
  /** Vue si erreur (exception) */
  private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    // Soyons optimistes
               String vue = VUE_OK;
                try {
                        PersonneDao dao = new PersonneDao();
                        List<Personne> personnes = dao.getByIdPersonne();
               
                        request.setAttribute("listePersonne", personnes);
                        

                        

                        } catch (SQLException exc) {
                            exc.printStackTrace();
                            request.setAttribute("message", "Pb de bases de données");
                            vue = VUE_ERREUR;
                        }
               
		
		request.getRequestDispatcher(vue).forward(request, response);
	}
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            
                    String Question = request.getParameter("quest");
                    String Date = request.getParameter("date");
                    String Note = request.getParameter("note");
                    
           
                }
                
        
                
}
