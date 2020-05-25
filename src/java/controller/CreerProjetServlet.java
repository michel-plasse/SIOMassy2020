/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjetDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "CreerProjetServlet", urlPatterns = {"/creerProjet"})
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

 
    private final String VUE_FORM = "/WEB-INF/creerProjet.jsp";
    private final String VUE_KO = "/WEB-INF/connexion.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
		int idSession = 1;
        try {
            List<Projet>projet = ProjetDao.getByIdSessionFormation(idSession);
      
                request.setAttribute("projet", projet);
                request.setAttribute("idSession", idSession);
        } catch (SQLException ex) {
            Logger.getLogger(CreerProjetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher(VUE_FORM).forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Personne user = (Personne) request.getSession(true).getAttribute("user");
        user = new Personne();
        
        if (user == null) {
            request.getRequestDispatcher(VUE_KO).forward(request, response);
        } else {
            boolean champsrenseignes = true;
            String sidSession = request.getParameter("id_session_formation");
            String titre = request.getParameter("titre");
            String datefin = request.getParameter("date_Fin");
            Date dateLimite = null;
            int id_session_formation = 0;
            int id_createur = user.getId();          
            
            if (sidSession == null || sidSession.isEmpty()) {
                champsrenseignes = false;
                request.setAttribute("idSession", "Veuillez selectionner une session de formation.");
            }
            else {
                try {
                    id_session_formation = Integer.parseInt(sidSession);
                    System.out.println("idSession = " + id_session_formation);
                } catch (NumberFormatException exc) {
                    champsrenseignes = false;
                    request.setAttribute("idSession", "La session de formation doit être un entier positif.");
                }

            }
            if (titre == null || titre.isEmpty()) {
                champsrenseignes = false;
                request.setAttribute("titre", "Veuillez entrer le nom de projet..");
                System.out.println("Rentre dans if condition");
            }
           // verification de la date de fin
            if (datefin == null || datefin.isEmpty()) {
                champsrenseignes = false;
                request.setAttribute("date_Fin", "Veuillez choisir une date limite.");
                System.out.println("Rentre dans if condition");
            }
            else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-DD");
                try {
                    dateLimite = df.parse(datefin);
                } catch (ParseException ex) {
                    Logger.getLogger(CreerProjetServlet.class.getName()).log(Level.SEVERE, null, ex);
                    champsrenseignes = false;
                    request.setAttribute("date_Fin", "Veuillez saisir une date valide (aaaa-mm-jj)");
                }
            }
            
            if (champsrenseignes) {
                try {
                    Date dateCreation = new Date();         // date de début initialisé au moment de la création
                    Projet p = new Projet(0, id_session_formation, id_createur, titre, dateLimite, dateCreation);                    // ajout du projet a l'aide du constructeur dans modele

                    ProjetDao dao = new ProjetDao();
                    dao.insert(p);
                    
                    request.setAttribute("message", "Projet numero" + p.getId() + " a été correctement créé " );
                    request.getRequestDispatcher(VUE_FORM).forward(request, response);
                } catch (SQLException e) {
                    request.getRequestDispatcher("WEB-INF/exception.jsp").forward(request, response);
                }

            } else {
                request.getRequestDispatcher(VUE_FORM).forward(request, response);
            }
        }
    }
}

