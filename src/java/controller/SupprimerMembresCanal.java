/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CanalDao;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet("/supprimerMembresCanal")
public class SupprimerMembresCanal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;
    private static final String VUE_OK = "WEB-INF/supprimerMembresCanal.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vue = VUE_OK;
        //response.sendRedirect("ajouterMembresCanal?idCanal=1");

        request.getRequestDispatcher(vue).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        String vue = VUE_OK;
        //on affecte un entier à l'idCanal et à l'idPersonne
        int idCanal = 1;
        int idPersonne = 1;
        try {
            idCanal = Integer.parseInt(request.getParameter("idCanal"));
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Veuillez saisir un entier pour l'idCanal");
        }
        try {
            idPersonne = Integer.parseInt(request.getParameter("idPersonne"));
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Veuillez saisir un entier pour l'idCanal");
        }
        CanalDao dao = new CanalDao();
        try {
            dao.supprimerMembre(idCanal, idPersonne);
            //response.sendRedirect("membresCanal?idCanal="+idCanal+"&?idPersonne="+idPersonne);
        } catch (SQLException exc) {

            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;
        }
        request.getRequestDispatcher(vue).forward(request, response);

    }

}
