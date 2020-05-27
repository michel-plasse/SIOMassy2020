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

/**
 *
 * @author Cissé-LENOVO
 */
@WebServlet("/ajouterMembresCanal")
public class AjouterMembresCanal extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String NORMALE = "WEB-INF/ajouterMembresCanal.jsp";
    /**
     * Vue si erreur (exception)
     */
    private static final String ERREUR = "WEB-INF/exception.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(NORMALE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String vue = NORMALE;
        int idCanal=1;
        CanalDao dao = new CanalDao();
        try {
             idCanal = Integer.parseInt(request.getParameter("idCanal"));

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Veuillez saisir un nombre valide pour l'idCanal");
        }

        try {
            int idPersonne = Integer.parseInt(request.getParameter("idPersonne"));
            dao.ajouterMembre(idCanal, idPersonne);
            //response.sendRedirect("http://localhost:8090/SIOMassy/membresCanal?idCanal="+idCanal);

        } catch (SQLException exc) {
            request.setAttribute("exception", exc);
            request.getRequestDispatcher(ERREUR).forward(request, response);
        }
        request.getRequestDispatcher(vue).forward(request, response);
    }
}
