/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import dao.ProjetDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Projet;

/**
 *
 * @author akber
 */
@WebServlet(name = "ProjetsServlet", urlPatterns = {"/projet"})
public class ProjetsServlet extends HttpServlet {

 private static final String VUE_OK = "WEB-INF/projets.jsp";
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vue = VUE_OK;


        try {
            List<Projet> projets = ProjetDao.getAll();
            // Ajouter 2 post it
            request.setAttribute("projets", projets);
        } catch (SQLException exc) {
            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;
        }

        request.getRequestDispatcher(vue).forward(request, response);
    }

}