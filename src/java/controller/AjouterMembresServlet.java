/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.istack.internal.logging.Logger;
import dao.MembresCanalDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.MembresCanal;

/**
 *
 * @author Cissé-LENOVO
 */
@WebServlet(name = "GererMembresServlet", urlPatterns = {"/gererMembres"})
public class AjouterMembresServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }
    private static final String VUE_BON = "WEB-INF/gererMembres.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    private MembresCanal MembresCanal;
    private String idCanal;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String vue = VUE_BON;

        try {
            MembresCanalDao membres = new MembresCanalDao();
            /*int idCanal = Integer.parseInt(request.getParameter("id_canal"));
            int idPersonne = Integer.parseInt(request.getParameter("id_personne"));
            String nom = request.getParameter("nom");*/

            membres.ajouterMembreCanal(MembresCanal);
            out.println(" Membre ajouté");

        } catch (SQLException ex) {
            out.println(" Membre pas ajouté");

            java.util.logging.Logger.getLogger(AjouterMembresServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect(this.getServletContext().getContextPath() + "/gererMembres?idCanal=" + idCanal);
    }

}
