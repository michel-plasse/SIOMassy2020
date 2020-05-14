/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjetDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Personne;
import modele.Projet;

/**
 *
 * @author maxim
 */
@WebServlet(name = "ProjetServlet", urlPatterns = {"/ProjetServlet"})
public class CreerProjetServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProjetServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProjetServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Personne user = (Personne) request.getSession(true).getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
        request.getRequestDispatcher("/WEB-INF/creerProjet.jsp").forward(request, response);
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Personne user = (Personne) request.getSession(true).getAttribute("user");
        user = new Personne();
               
            boolean champsrenseignes = true;
            System.out.println("post creerProjet");
            String titre = request.getParameter("titre");
            String sDateLimite = request.getParameter("dateLimite");
            Date dateLimite = null;
            int idSession = 0;
            int id_createur = user.getId();
            
            if (titre == null || titre.isEmpty()) {
                champsrenseignes = false;
                request.setAttribute("sujet", "Veuillez entrer le nom de projet..");
                System.out.println("Rentre dans if condition");
            }
            
            if (sDateLimite == null || sDateLimite.isEmpty()) {
                champsrenseignes = false;
                request.setAttribute("dateLimite", "Veuillez choisir une date limite..");
                System.out.println("Rentre dans if condition");
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-DD");
                try {
                    dateLimite = df.parse(sDateLimite);
                } catch (ParseException ex) {
                    Logger.getLogger(CreerProjetServlet.class.getName()).log(Level.SEVERE, null, ex);
                    champsrenseignes = false;
                    request.setAttribute("dateLimite", "Veuillez saisir une date valide (aaaa-mm-jj)");
                }
            }
            if (champsrenseignes) {
                Date dateCreation = new Date();
                    Projet projetAjoutee = new Projet(0, idSession, id_createur, titre, dateCreation, dateLimite);
                    ProjetDao dao = new ProjetDao();
                    request.getRequestDispatcher("/WEB-INF/creerProjet.jsp").forward(request, response);
                }
            request.getRequestDispatcher("/WEB-INF/creerProjet.jsp").forward(request, response);
        }
    }

