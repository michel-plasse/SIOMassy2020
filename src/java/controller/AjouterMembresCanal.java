/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MembresCanalDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Membre;
import modele.Personne;

/**
 *
 * @author Cissé-LENOVO
 */
@WebServlet(name = "AjouterMembresCanal", urlPatterns = {"/AjouterMembresCanal"})
public class AjouterMembresCanal extends HttpServlet {

    private static final String VUE_OK = "WEB-INF/ajouterMembresCanal.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";
    private int idCanal;
    private int idPersonne;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        String vue = VUE_OK;

        MembresCanalDao dao = new MembresCanalDao();
        try {
            int idCanal = Integer.parseInt(request.getParameter("id_canal"));

        } catch (Exception e1) {
            request.setAttribute("exception", e1);
            vue = VUE_ERREUR;
        }
        try {
            int idPersonne = Integer.parseInt(request.getParameter("id_personne"));
        } catch (Exception e2) {
            request.setAttribute("exception", e2);
            vue = VUE_ERREUR;

        }
        try {
            Membre membre = new Membre();
            membre.setIdCanal(idCanal);
            membre.setIdPersonne(idPersonne);
            dao.ajouterMembre(membre);
            response.sendRedirect("/ajouterMembresCanal");

        } catch (SQLException exc) {

            request.setAttribute("exception", exc);

            vue = VUE_ERREUR;
        }


    }

}
