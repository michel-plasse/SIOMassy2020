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
@WebServlet("/index")
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

    private Membre MembresCanal;
    private int idCanal;
    private int idPersonne;

    private static final String VUE_OK = "WEB-INF/index.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        String vue = VUE_OK;

        try {
            int idCanal = Integer.parseInt(request.getParameter("id_canal"));
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Veuillez saisir un entier pour l'idCanal");

        }
        try {
            int idPersonne = Integer.parseInt(request.getParameter("id_canal"));
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Veuillez saisir un entier pour l'idCanal");

        }

        CanalDao dao = new CanalDao();
        try {

            dao.supprimerMembre(idCanal, idPersonne);
            //response.sendRedirect("supprimerMembresCanal?idCanal=1");
        } catch (SQLException exc) {

            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;

        }
        request.getRequestDispatcher(vue).forward(request, response);

    }

}
