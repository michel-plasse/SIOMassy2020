/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MembresCanalDao;
import dao.PersonneDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Canal;
import modele.MembresCanal;
import modele.Personne;

/**
 *
 * @author Cissé-LENOVO
 */
@WebServlet(name = "ListerMembresCanal", urlPatterns = {"/ListerMembresCanal"})
public class ListerMembresCanal extends HttpServlet {

    /**
     * Vue si succes
     */
    private static final String VUE_OK = "WEB-INF/listerMembresCanal.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Soyons optimistes
        String vue = VUE_OK;

        // Recuperer les donnees des membres d'un canal
        try {
            List<MembresCanal> membres = MembresCanalDao.getAll();
            // Ajouter 2 post it            

            request.setAttribute("membres", membres);
        } catch (SQLException exc) {
            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;
        }
        // Passer la main a la vue
        request.getRequestDispatcher(vue).forward(request, response);

    }

}
