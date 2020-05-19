/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CanalDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
    private static final String VUE_OK = "WEB-INF/membresCanal.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Soyons optimistes
        String vue = VUE_OK;

        int idCanal = 1;
        // Recuperer les donnees des membres d'un canal
        try {
            List<Membre> membres = CanalDao.getMembres(idCanal);
            // Ajouter 2 post it            

            request.setAttribute("membres", membres);
            request.setAttribute("idCanal", idCanal);
            //URL Rewritting
            //response.sendRedirect("membresCanal?idCanal="+idCanal);

        } catch (SQLException exc) {
            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;
        }
        // Passer la main a la vue
        request.getRequestDispatcher(vue).forward(request, response);

    }
    

}
