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
        CanalDao dao = new CanalDao();
        
        try {
            int idCanal = Integer.parseInt(request.getParameter("idCanal"));
            int idPersonne = Integer.parseInt(request.getParameter("idPersonne"));
            dao.ajouterMembre(idCanal, idPersonne);
            response.sendRedirect("membresCanal?idCanal="+idCanal);
            
        } catch (SQLException | NumberFormatException exc) {
            request.setAttribute("exception", exc);
            request.getRequestDispatcher(ERREUR).forward(request, response);
        }
    }
}
