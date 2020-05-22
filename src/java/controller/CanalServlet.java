/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CanalDao;
import dao.MessageDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Canal;
import modele.Message;

/**
 *
 * @author akber
 */
@WebServlet(name = "MessageServlet", urlPatterns = {"/canal"})
public class CanalServlet extends HttpServlet {

 private static final String VUE_OK = "WEB-INF/canal.jsp";
    private static final String VUE_ERREUR = "WEB-INF/exception.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vue = VUE_OK;
        
        int idCanal = 1;
        try {
            List<Message> messages = MessageDao.getMessage(idCanal);
            // Ajouter 2 post it
            request.setAttribute("messages", messages);
            System.out.println("test passé" + messages.size());
        } catch (SQLException exc) {
            exc.printStackTrace();
            request.setAttribute("exception", exc);
            vue = VUE_ERREUR;
        }

        request.getRequestDispatcher(vue).forward(request, response);
    }

}
