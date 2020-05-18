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
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        MembresCanalDao dao = new MembresCanalDao();
        try {

            dao.supprimerMembre(idCanal);
            //response.sendRedirect("supprimerMembresCanal?idCanal=1");
        } catch (SQLException exc) {

            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;

        }
        request.getRequestDispatcher(vue).forward(request, response);

    }

}
