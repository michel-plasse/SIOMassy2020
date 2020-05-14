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
import modele.MembresCanal;

/**
 *
 * @author Cissé-LENOVO
 */
@WebServlet(name = "SupprimerMembresCanal", urlPatterns = {"/supprimerMembresCanal"})
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
    
    private MembresCanal MembresCanal;
    private int idCanal;

    private static final String VUE_OK = "WEB-INF/supprimerMembresCanal.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        MembresCanal membres = (MembresCanal) session.getAttribute("user");
        request.getRequestDispatcher(VUE_OK).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        String vue = VUE_OK;
        PrintWriter out = response.getWriter();
        int idCanal = Integer.parseInt(request.getParameter("id_canal"));
        MembresCanalDao membres = new MembresCanalDao();
        try {
            
            membres.supprimerMembreByIdCanal(idCanal);
            vue = VUE_OK;

        } catch (SQLException exc) {
            out.println(" Membre pas supprimé");

            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;

        }

        request.getRequestDispatcher(vue).forward(request, response);
    }

}
