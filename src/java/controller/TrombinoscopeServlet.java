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

@WebServlet("/trombinoscope")
public class TrombinoscopeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Vue si succes
     */
    private static final String VUE_OK = "WEB-INF/trombinoscope.jsp";

    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Soyons optimistes
        String vue = VUE_OK;


        

        // Recuperer les donnees (ici, les stagiaires)
        try {
            int idSession = Integer.parseInt(request.getParameter("idSession"));
            List<Personne> stagiaires = PersonneDao.getByIdSessionFormation(idSession);
            // Ajouter 2 post it
            request.setAttribute("stagiaires", stagiaires);
            request.setAttribute("idSession", idSession);
        } catch (SQLException exc) {
            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;
        }
        // Passer la main a la vue
        request.getRequestDispatcher(vue).forward(request, response);
    }

}
