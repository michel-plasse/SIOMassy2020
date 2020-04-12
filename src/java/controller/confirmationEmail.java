/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="confirmationEmail" ,urlPatterns = "/confirmationEmail")
public class confirmationEmail extends HttpServlet {
    
        private static final String VUE_FORM_CON = "/WEB-INF/connexion.jsp";
        private static final String VUE_INDEX = "/index.jsp";
    
        
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String Token = request.getParameter("token");
        Timestamp timestampvalidation = new Timestamp(System.currentTimeMillis());
        
        String vue=VUE_FORM_CON;
        
        try {
            
            Connection db = Database.getConnection();
            PreparedStatement stmt = db.prepareStatement("SELECT * from personne where jeton= ? and est_Actif=false");
            stmt.setString(1, Token);
            //stmt.setTimestamp(2, timestampvalidation);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp t = rs.getTimestamp("date_insertion");
                if (timestampvalidation.getTime()-t.getTime()<86400){
                         PreparedStatement stmt1 = db.prepareStatement("UPDATE personne SET est_Actif=true where jeton= ? ");
                         stmt1.setString(1, Token);
                         int i = stmt1.executeUpdate();
                         if(i==1){
                             request.setAttribute("messageBienvenue", "Bienvenue");
                        }
                
                
                }else{
                    request.setAttribute("messageBienvenue", "vous n'etes pas le bienvenue");
                }
                
                
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(confirmationEmail.class.getName()).log(Level.SEVERE, null, ex);
            vue = VUE_INDEX;
            
        }
            //response.sendRedirect(vue);
            request.getRequestDispatcher(vue).forward(request, response);
            
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
