/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersonneDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Personne;

@WebServlet(name = "GestionMdpServlet", urlPatterns = {"/demanderNouvMdp"})
public class GestionMdpServlet extends HttpServlet {
    
  private static final String VUE_INDEX = "/index.jsp";
  private static final String VUE_dmdMDP ="/WEB-INF/demanderNouvMdp.jsp";

  private static final String VUE_ERREUR = "WEB-INF/PageError.jsp";


   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.getRequestDispatcher(VUE_dmdMDP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                String vue = VUE_dmdMDP;
                Personne user = null;

     
     
    try {
      if (login == null || login.trim().isEmpty()
              //|| password == null || password.trim().isEmpty()
              ) {
        request.setAttribute("erreurLogin", "Les champs sont obligatoires");
      } else {
        user = PersonneDao.getByLoginPassword(login, password);
        if (user != null) {
          // Ajouter en session
          HttpSession maSession = request.getSession(true);
          maSession.setAttribute("user", user);
          vue = VUE_INDEX;
        } else {
          request.setAttribute("erreurLogin",
                  "Utilisateur inconnu ou mot de passe incorrect");
        }
      }
    } catch (SQLException exc) {
      Logger.getLogger(ConnexionServlet.class.getName()).log(Level.SEVERE, null, exc);
      request.setAttribute("exception", exc);
      vue = VUE_ERREUR;
    }
    // Passer la main à la JSP
   request.getRequestDispatcher(VUE_dmdMDP).forward(request, response);
  }
    }

