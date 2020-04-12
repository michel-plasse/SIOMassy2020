package controller;

import SMTP.JavaMailUtil;
import dao.PersonneDao;
import java.io.IOException;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;


@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
    
    private static final String VUE_INDEX = "/index.jsp";
    private static final String VUE_FORM_CON = "/WEB-INF/connexion.jsp";
    private static final String VUE_FORM_INS = "/WEB-INF/inscription.jsp";
    private static final String VUE_ERREUR = "/WEB-INF/exception.jsp";
    private static final String VUE_VERIFY= "/WEB-INF/verify.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VUE_FORM_INS).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        String nom = request.getParameter("nom");
        String prenom= request.getParameter("prenom");
        String mail = request.getParameter("mail");
        String mdp = request.getParameter("mdp");
        String mdp2 = request.getParameter("mdp2");
        String vue = VUE_FORM_INS;
                       
        try {
            if (nom == null || nom.trim().isEmpty()|| prenom == null || prenom.trim().isEmpty()|| mail == null || mail.trim().isEmpty()|| mdp == null || mdp.trim().isEmpty() || mdp2 == null || mdp2.trim().isEmpty()) {
            request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
            // la vue reste à VUE_FORM_INS
            }
            else if(PersonneDao.mailExist(mail)){ // on fait appel à la méthode mailExist() presente dans PersonneDao
            request.setAttribute("erreurLogin", "Ce mail existe déjà");
            // la vue reste à VUE_FORM_INS
            }
            else if (!mail.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) {
            request.setAttribute("erreurLogin", "Ce mail est invalide, veuillez saisir un autre");
            // la vue reste à VUE_FORM_INS
            }
            else if (!mdp.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
            request.setAttribute("erreurLogin", "<ul>\n" +
                                                    "<p><u>Le mot de passe :</u></p>\n" +
                                                    "<li> - doit contenir au moins 8 caractères</li>\n" +
                                                    "<li> - doit au moins un chiffre</li>\n" +
                                                    "<li> - doit contenir au moins un caractère spécial dans la liste : @#$%^&+=</li>\n" +
                                                    "<li> - ne doit pas contenir des espaces</li>\n" +
                                                "</ul>");
            // la vue reste à VUE_FORM_INS    
            }
            else if (!mdp.equals(mdp2)){
            request.setAttribute("erreurLogin", "Les mots de passe ne sont pas identiques ! merci de vérifier votre saisie");
            // la vue reste à VUE_FORM_INS    
            }
            else{
                try {
                        Random random=new Random();
                        random.nextInt(9999999);
                        String jeton = DigestUtils.md5Hex(""+random) ;
                       
                        Personne p = new Personne(prenom, nom, mail, mdp, jeton);
                        PersonneDao.insert(p);
                        JavaMailUtil.sendMail(mail,nom,prenom,jeton);
                        
                        //request.setAttribute("messageBienvenue", "Bonjour "+ prenom + ", vous êtes maintenant inscrit, vous pouvez vous identifier ci-dessous");                
                        vue = VUE_VERIFY;
                    } catch (Exception ex) {
                        Logger.getLogger(InscriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
                        vue = VUE_ERREUR;
                    }
                    
                    
                }
        
            
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
            vue = VUE_ERREUR;
        }
        
        request.getRequestDispatcher(vue).forward(request, response);
            

       
    }

    

}
