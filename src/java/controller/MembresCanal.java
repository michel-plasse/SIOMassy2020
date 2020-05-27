/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CanalDao;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Membre;

/**
 *
 * @author Cissé-LENOVO
 */
@WebServlet("/membresCanal")
public class MembresCanal extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Vue si succes
     */
    private static final String NORMALE = "WEB-INF/membresCanal.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String ERREUR = "WEB-INF/exception.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vue = NORMALE;
        // Recuperer les donnees des membres d'un canal
        try {
            int idCanal = Integer.parseInt(request.getParameter("idCanal"));
            List<Membre> membres = CanalDao.AfficherMembresDUnCanal(idCanal);
            // Ajouter 2 post it            
            request.setAttribute("membres", membres);
            request.setAttribute("idCanal", idCanal);
            //response.sendRedirect("membresCanal?idCanal=1");
        } catch (SQLException exc) {
            request.setAttribute("exception", exc);
            request.getRequestDispatcher(ERREUR).forward(request, response);
        }
        // Passer la main a la vue
        request.getRequestDispatcher(vue).forward(request, response);
    }

}
