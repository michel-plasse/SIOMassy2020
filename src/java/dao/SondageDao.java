/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Statement;
import java.sql.*;
import java. text.*;
import java.time.*;
import java.util.Date;




import modele.Sondage;

/**
 *
 * @author Ronan
 */
 
public class SondageDao {

    public static void insert(Sondage sondage) throws SQLException {

        Connection db = Database.getConnection();
        String sql = "INSERT INTO agriotes2020.sondage (  question, note_maximale,id_createur, date_butoir)"
                + " VALUES ( ?, ?, ?, ?)";
        PreparedStatement stmt = db.prepareStatement(sql);

        stmt.setString(2, sondage.getQuestion());
        stmt.setInt(3, sondage.getNote_maximale());
        stmt.setInt(4, sondage.getId_createur());
        stmt.setTimestamp(5,Timestamp.valueOf(sondage.getDate_butoir()));
        stmt.executeUpdate();
        stmt.close();
        db.close();
        
        
        

  }
 
    public static void Ins(Sondage sondage) throws SQLException {

        Connection db = Database.getConnection();
        String sql = "INSERT INTO agriotes2020.sondage (id_sondage,  question, note_maximale,id_createur, date_butoir)"
                + " VALUES ( ?, ?, ?, ?, ?)";
        PreparedStatement stmt = db.prepareStatement(sql);
        stmt.setInt(1, sondage.getId_sondage());
        stmt.setString(2, sondage.getQuestion());
        stmt.setInt(3, sondage.getNote_maximale());
        stmt.setInt(4, sondage.getId_createur());
        stmt.setTimestamp(5,Timestamp.valueOf(sondage.getDate_butoir()));
        stmt.executeUpdate();
        stmt.close();
        db.close();
        
        
        

  }
    
    
    public static void insert2() throws SQLException {
                    
                Connection db = Database.getConnection();
                String sql = "INSERT INTO agriotes2020.reponse_sondage (  id_personne, id_sondage,note) VALUES ( 1,4,1)";
                Statement stmt = db.createStatement();
                stmt.executeQuery(sql);
                
            }
    public static void insert3() throws SQLException {
                Connection db = Database.getConnection();
                String sql = "INSERT INTO agriotes2020.sondage ( id_sondage, question, note_maximale,id_createur, date_butoir) VALUES (?, ?, ?, ?, ?) ";
                PreparedStatement stmt = db.prepareStatement(sql); //"Insert into personne (nom,prenom,email,mdp,jeton,date_butoir_jeton) VALUES(?,?,?,?,?,?)"
                stmt.setInt(1, 1);
                stmt.setString(2, "hello");
                stmt.setInt(3, 4);
                stmt.setInt(4, 2);
                stmt.setTimestamp(5, Timestamp.valueOf("2020-05-09"));
                stmt.executeUpdate();

              }
       public static final String INSERTION
          = "INSERT INTO agriotes2020.reponse_sondage (  id_personne, id_sondage,note) VALUES ( ?,?,?)";  
    
       public static void insertrsond(int a, int b, int c) throws SQLException {
                Connection db = Database.getConnection();
                //String sql = "INSERT INTO agriotes2020.reponse_sondage (  id_personne, id_sondage,note) VALUES ( ?,?,?)";
                PreparedStatement stmt = db.prepareStatement(INSERTION); //"Insert into personne (nom,prenom,email,mdp,jeton,date_butoir_jeton) VALUES(?,?,?,?,?,?)"
                stmt.setInt(1, a);
                stmt.setInt(2, b);
                stmt.setInt(3, c);
                stmt.executeUpdate();

            }
           
        public static final String INSERTIONS
          = "INSERT INTO agriotes2020.sondage ( question, note_maximale,id_createur, date_butoir) VALUES ( ?,?,?,?)";  
    
       
        
        public static void insertsond(Sondage sondage) throws SQLException {
                Connection db = Database.getConnection();
                
                String da = sondage.getDate_butoir();
                //String da = "1990-07-09";
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                 try {

                    java.util.Date date = df.parse(da);
                    // if you really need java.sql.Date
                   java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                   PreparedStatement stmt = db.prepareStatement(INSERTIONS); //"Insert into personne (nom,prenom,email,mdp,jeton,date_butoir_jeton) VALUES(?,?,?,?,?,?)"
                    stmt.setString(1, sondage.getQuestion());
                    stmt.setInt(2, sondage.getNote_maximale());
                    stmt.setInt(3, sondage.getId_createur());
                    stmt.setDate(4,sqlDate);
                    stmt.executeUpdate();
                
                } catch (ParseException e) {
                    e.printStackTrace();
                }
               
               
                  
                
                
                

            }
  
        
          
   
         
            public int ComptSond() throws SQLException {
            int sortie = 0;
            Connection db = Database.getConnection();
            Statement stmt = db.createStatement();
            String sql = "select count(*) as NbSond FROM agriotes2020.sondage";
            ResultSet rs = stmt.executeQuery(sql); //"SELECT * FROM personne WHERE email=? and date_inscription IS NOT NULL;"
             while (rs.next()) {
             sortie = rs.getInt("NbSond");
            }
            return sortie;
         
              
             }
            public int ComptSond2() throws SQLException {
            int sortie = 0;
            Connection db = Database.getConnection();
            String sql = "select id_createur FROM agriotes2020.sondage where note_maximale != 1000";

            PreparedStatement stmt = db.prepareStatement(sql);
            stmt.setInt(1, 1000);

            ResultSet resultat = stmt.executeQuery();
             while (resultat.next()) {
             sortie = resultat.getInt("id_createur");
            }
            //sortie = resultat.getInt("id_createur");
            

            stmt.close();
            db.close();

            return sortie;
        }




}

