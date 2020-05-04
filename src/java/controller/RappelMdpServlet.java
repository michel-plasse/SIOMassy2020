package controller;

import javax.mail.*;
import dao.Database;
import dao.PersonneDao;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Personne;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.codec.digest.DigestUtils;

@WebServlet("/rappelMdp")
public class RappelMdpServlet extends HttpServlet {

  private static final String VUE_INDEX = "/index.jsp";

  private static final String VUE_ERREUR = "/WEB-INF/exception.jsp";
  private static final String VUE_VERIFY = "/WEB-INF/verify.jsp";
  private static final String VUE_CHANGEMENT_MDP = "/WEB-INF/rappelMdp.jsp";

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.getRequestDispatcher(VUE_FORM_INS).forward(request, response);
//    }
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    request.getRequestDispatcher(VUE_CHANGEMENT_MDP).forward(request, response);
  }

  /**
   *
   * @param request
     * @throws javax.servlet.ServletException
   */
  @Override

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String vue = VUE_CHANGEMENT_MDP;
    String mail = request.getParameter("mail");
    // nous faisons d'abord un test sur tous les champs du formulaire
    if (mail == null || mail.trim().isEmpty()) {
      request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
    } else if (!mail.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+")) {
      request.setAttribute("erreurLogin", "Ce mail est invalide, veuillez saisir un autre");
    } else {
      Random random = new Random();                               // Instance d'un objet random
      random.nextInt(9999999);                                  // Selection aléatoire d'un nombre entre 0 est 9999999
      String jeton = DigestUtils.md5Hex("" + random);
      String msg;
      try {
        PersonneDao dao = new PersonneDao();
        int nb = dao.setJeton(mail, jeton);
        if (nb == 1) {
          // Jeton positionne : envoyer le mail avec JavaMailUtil
          // + passer la main a jsp VUE_MESSAGE (juste un message)
        } else {
          request.setAttribute("erreurLogin", "Email introuvable ou inscription pas confirmée");
        }
      } catch (SQLException ex) {
          Logger.getLogger(RappelMdpServlet.class.getName()).log(Level.SEVERE, null, ex);
          vue = VUE_ERREUR;
        }

      }
      request.getRequestDispatcher(vue).forward(request, response);                   // La servlet nous envoi vers la vue appropriée en envoyant avec les objets request et response
    }

    
}
