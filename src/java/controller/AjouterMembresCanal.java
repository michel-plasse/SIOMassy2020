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

    private static final String VUE_OK = "WEB-INF/ajouterMembresCanal.jsp";
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

        int idCanal = 1;
        int idPersonne = 1;
        response.setContentType("text/html");
        String vue = VUE_OK;

        CanalDao dao = new CanalDao();
        
        while (true) {
            try {
                idCanal = Integer.parseInt(request.getParameter("idCanal"));
                break;
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Veuillez saisir un nombre valide pour l'idCanal");
            }
            try {
                idPersonne = Integer.parseInt(request.getParameter("idPersonne"));
                break;
            } catch (NumberFormatException e2) {
                request.setAttribute("message", "Veuillez saisir un nombre valide pour l'idPersonne");
            }
        }
        try {

            dao.ajouterMembre(idCanal, idPersonne);
            //response.sendRedirect("ajouterMembresCanal?idCanal=1");

        } catch (SQLException exc) {

            request.setAttribute("exception", exc);

            vue = VUE_ERREUR;
        }

        request.getRequestDispatcher(vue).forward(request, response);

    }

}
