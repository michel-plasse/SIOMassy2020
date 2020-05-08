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
import modele.Canal;

/**
 *
 */
@WebServlet(name = "ChatServlet", urlPatterns = {"/chat.jsp"})
public class ChatServlet extends HttpServlet {

    /**
     * Vue si succes
     */
    private static final String VUE_OK = "WEB-INF/chat.jsp";

    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Soyons optimistes
        String vue = VUE_OK;
        // d'abord en dur
        //int idCanal = Integer.parseInt(request.getParameter("idCanal"));
        // Recuperer les donnees (ici, les stagiaires)
        try {
            List<Canal> canaux = CanalDao.getAll();
            // Ajouter 2 post it
            request.setAttribute("canaux", canaux);
            //request.setAttribute("idCanal", idCanal);
            System.out.println("Afficher nombre de canaux");
        } catch (Exception exc) {
            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;
        }
        // Passer la main a la vue
        request.getRequestDispatcher(vue).forward(request, response);
    }
}
