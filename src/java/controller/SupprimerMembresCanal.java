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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AjouterMembresServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjouterMembresServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int idCanal = Integer.parseInt(request.getParameter("id_canal"));

        try {
            MembresCanalDao membres = new MembresCanalDao();

            membres.supprimerMembreByIdCanal(idCanal);
            out.println(" Membre supprimé");

        } catch (SQLException ex) {
            out.println(" Membre pas supprimé");

            java.util.logging.Logger.getLogger(AjouterMembresCanal.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect(this.getServletContext().getContextPath() + "/supprimerMembresCanal?idCanal=" + idCanal);
    }

}
