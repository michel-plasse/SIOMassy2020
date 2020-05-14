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
import javax.servlet.http.HttpSession;
import modele.MembresCanal;
import modele.Personne;

/**
 *
 * @author Cissé-LENOVO
 */
@WebServlet(name = "AjouterMembresCanal", urlPatterns = {"/ajouterMembresCanal"})
public class AjouterMembresCanal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }
    private static final String VUE_OK = "WEB-INF/ajouterMembresCanal.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    MembresCanal MembresCanal = null;


      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    MembresCanal membres = (MembresCanal) session.getAttribute("MembresCanal");
    request.getRequestDispatcher(VUE_OK).forward(request, response);
    
    }
            
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String vue = VUE_OK;

        MembresCanalDao membres = new MembresCanalDao();
        String idCanal = request.getParameter("id_canal");
        String idPersonne = request.getParameter("id_personne");

        out.println(" Membre ajouté");

        try {
            membres.ajouterMembreCanal(MembresCanal);

            //request.setAttribute("membres", membres);
            request.setAttribute("idCanal", idCanal);
            request.setAttribute("idPersonne", idPersonne);
            vue = VUE_OK;
        } catch (SQLException ex) {
            Logger.getLogger(AjouterMembresCanal.class).log(Level.SEVERE, null, ex);
            vue = VUE_ERREUR;
        }

        request.getRequestDispatcher(vue).forward(request, response);

    }
}
