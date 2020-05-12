package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.PersonneDao;
import modele.Personne;
import modele.Sondage;
import dao.SondageDao;

@WebServlet("/sondage")
public class SondageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
  /** Vue si succes */
  private static final String VUE_OK = "WEB-INF/sondage.jsp";
  
  /** Vue si erreur (exception) */
  private static final String VUE_ERREUR = "WEB-INF/exception.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            // System.out.println("-------------------------------------------------------------------------VUE_OK ");
    // Soyons optimistes
               String vue = VUE_OK;
                try {
                        PersonneDao dao = new PersonneDao();
                        List<Personne> personnes = dao.getByIdPersonne( );
               
                        request.setAttribute("listePersonne", personnes);
                        
                        
                    } catch (SQLException exc) {
                            exc.printStackTrace();
                            request.setAttribute("message", "Pb de bases de données");
                            vue = VUE_ERREUR;
                    }
               
                
		
		request.getRequestDispatcher(vue).forward(request, response);
	}
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           
            System.out.println("+++++++++++++++++++++++++++++++++++---------VUE_OK ");
            String vue = VUE_OK;
            
           
                    String Question = request.getParameter("quest");
                    String Date = request.getParameter("date");
                    String Note = request.getParameter("note");
                    String box = request.getParameter("2");
                   
                    
                    HttpSession maSession = request.getSession();
                    Personne user = (Personne) maSession.getAttribute("user");
                    //Date = "2020-05-08";
                    //Question = "hello";
                    int note = -1;
                    int createur = -1;
                    int cible = -1;
                    int NbrSond = 1;
                    int NoteCible = 0;
                    int NbrPers = 0;
                    int x = 1;
                    String ValCombo = "x";
                    String Convert = "x";
                    String Convert1 = "x";
                    int test = 2;
                    LocalDateTime SDate = null;
                   
                    try {
                        PersonneDao dao = new PersonneDao();
                        List<Personne> personnes = dao.getByIdPersonne( );
               
                        request.setAttribute("listePersonne", personnes);
                        
                        
                    } catch (SQLException exc) {
                            exc.printStackTrace();
                            request.setAttribute("message", "Pb de bases de données");
                            vue = VUE_ERREUR;
                    }
                    
                    
                      
                    try {
                        note = Integer.parseInt(Note);
                        //note = 5;
                    } catch (Exception e) {
                        System.out.println("pb Note");
                        vue = VUE_ERREUR;

                    }
                    /* 
                    try {
                        cible = Integer.parseInt(box);
                        //createur = 6;
                    } catch (Exception e) {
                        System.out.println("pb cible");
                        vue = VUE_ERREUR;

                    }
                    */ 
                   
                      
                     // mettre au troisième argument user.getId()
                     //Sondage sondage = new Sondage(Question, note, 2,  Date);
                    /* try {
                        //SondageDao dao = new SondageDao();
                        //PersonneDao daop = new PersonneDao();
                        //NbrSond = dao.ComptSond()+1;
                        //NPers = daop.ComptPers();
                       // dao.insertsond(sondage);
                        //dao.insertrsond(cible,NbrSond,NoteCible);
                        //dao.insert3();
                       // SondageDao.insert(sondage);
                        //SondageDao dao2 = new SondageDao();
                        //dao2.insert2();
                    } catch (Exception ex) {
                        Logger.getLogger(SondageServlet.class.getName()).log(Level.SEVERE, null, ex);
                        vue = VUE_ERREUR;

                    }*/
          
 /*
                    try {
                        note = Integer.parseInt(Note);
                    } catch (Exception e) {
                        System.out.println("pb note");
                        vue = VUE_ERREUR;

                    }

                   try {
                        cible= Integer.parseInt(box);
                       //cible = Integer.valueOf(box).intValue();
                    } catch (Exception e) {
                        System.out.println("pb parse int2");
                        vue = VUE_ERREUR;

                    }
        */
                
        
        
        
        Sondage sondage = new Sondage(Question, note, 2,  Date);

        try {
           
            SondageDao dao = new SondageDao();
            PersonneDao daop = new PersonneDao();
            NbrSond = dao.ComptSond()+1;
            NbrPers = daop.ComptPers();
            dao.insertsond(sondage);
            
            /*
             dao.insertrsond(1,NbrSond,NoteCible);
              dao.insertrsond(3,NbrSond,NoteCible);
               dao.insertrsond(5,NbrSond,NoteCible);
                dao.insertrsond(7,NbrSond,NoteCible);
           */
            for(int i=1; i <= NbrPers; i++)
            {
               if(request.getParameter(String.valueOf(i))!=null)
                {
                    dao.insertrsond(i,NbrSond,NoteCible);
                }
                
            }
            
            
            
            
            /* 
            if(request.getParameter("1")!=null)
                {
                    dao.insertrsond(1,NbrSond,NoteCible);
                }
            if(request.getParameter("2")!=null)
                {
                    dao.insertrsond(2,NbrSond,NoteCible);
                }
            if(request.getParameter("3")!=null)
                {
                    dao.insertrsond(3,NbrSond,NoteCible);
                }
            if(request.getParameter("4")!=null)
                {
                    dao.insertrsond(4,NbrSond,NoteCible);
                }
             if(request.getParameter("5")!=null)
                {
                    dao.insertrsond(5,NbrSond,NoteCible);
                }
             /*
            
             if(request.getParameter("6").length()!=0)
                {
                    dao.insertrsond(6,NbrSond,NoteCible);
                }
            
             if(request.getParameter("7").length()!=0)
                {
                    dao.insertrsond(7,NbrSond,NoteCible);
                }
             if(request.getParameter("8").length()!=0)
                {
                    dao.insertrsond(8,NbrSond,NoteCible);
                }
             if(request.getParameter("9").length()!=0)
                {
                    dao.insertrsond(9,NbrSond,NoteCible);
                }
            
            if(request.getParameter("10").length()!=0)
                {
                    dao.insertrsond(10,NbrSond,NoteCible);
                }
            if(request.getParameter("11").length()!=0)
                {
                    dao.insertrsond(11,NbrSond,NoteCible);
                }
            if(request.getParameter("12").length()!=0)
                {
                    dao.insertrsond(12,NbrSond,NoteCible);
                }
            if(request.getParameter("13").length()!=0)
                {
                    dao.insertrsond(13,NbrSond,NoteCible);
                }
           
            */
        
        
        
        
        } catch (SQLException ex) {
            Logger.getLogger(SondageServlet.class.getName()).log(Level.SEVERE, null, ex);
            vue = VUE_ERREUR;

        }
    
                    request.setAttribute("message", "Sondage ki zébi");
                    request.getRequestDispatcher(vue).forward(request, response);
        
        
     }
                
        
}              

