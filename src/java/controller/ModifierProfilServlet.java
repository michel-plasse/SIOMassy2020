package controller;

import dao.PersonneDao;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modele.Personne;
import tools.Upload;

@WebServlet("/modifierProfil")
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB    
        maxRequestSize = 20971520L // 20 MB
)
public class ModifierProfilServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  /**
   * Vue si succes ou en cas d'anomalie sur les valeurs saisies
   */
  private static final String VUE_FORM = "/WEB-INF/modifierProfil.jsp";
  /**
   * Vue si succes si MAJ OK
   */
  private static final String VUE_ACCUEIL = "/WEB-INF/accueil.jsp";
  public static final String CHAMP_FICHIER = "fichier";

  /*public static final String CHEMIN
          = "C:/Users/sandr/OneDrive/Documents/NetBeansProjects/SIOMassy2020/web/img/";
   */
  public static final int TAILLE_TAMPON = 409600; // 400 ko /* Vue si erreur

  private static final String VUE_ERREUR = "/WEB-INF/exception.jsp";
  String vue = VUE_ACCUEIL;
  String CHEMIN;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   * response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    Personne personne = (Personne) session.getAttribute("user");
    request.getRequestDispatcher(VUE_FORM).forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (request.getParameter("bouton_valid").equals("Abandonner")) {
      //Abandon de la mise àjour
      request.setAttribute("abandon", "Modification abandonnée");
      request.getRequestDispatcher(vue).forward(request, response);
    }
    vue = VUE_FORM;                      // modifierProfil.jsp
    if (request.getParameter("bouton_valid").equals("Enregistrer")) {
      //Mise à jour de la base
      majProfil(request, response);
    }
    request.getRequestDispatcher(vue).forward(request, response);
  }

  private void majProfil(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession(true);
    Personne personne = (Personne) session.getAttribute("user");
    String nom = request.getParameter("nom");
    String prenom = request.getParameter("prenom");
    String email = request.getParameter("email");
    String mdp1 = request.getParameter("mdp1");
    String mdp2 = request.getParameter("mdp2");
    int idPersonne = personne.getId();
    CHEMIN = request.getServletContext().getRealPath("/img/");
    String oldPhoto = personne.getUrlPhoto();

    System.out.println(CHEMIN);
    /*
     * Les données reçues sont multipart, on doit donc utiliser la méthode
     * getPart() pour traiter le champ d'envoi de fichiers.
     */
    Part part = request.getPart(CHAMP_FICHIER);
    /*
     * Il faut déterminer s'il s'agit d'un champ classique 
     * ou d'un champ de type fichier : on délègue cette opération 
     * à la méthode utilitaire getNomFichier().
     */
    String nomFichier = getNomFichier(part);
    /*
     * Si la méthode a renvoyé quelque chose, il s'agit donc d'un champ
     * de type fichier (input type="file").
     */
    if (nomFichier != null && !nomFichier.isEmpty()) {
      String nomChamp = part.getName();
      /*
         * Antibug pour Internet Explorer, qui transmet pour une raison
         * mystique le chemin du fichier local à la machine du client...
         * 
         * Ex : C:/dossier/sous-dossier/fichier.ext
         * 
         * On doit donc faire en sorte de ne sélectionner que le nom et
         * l'extension du fichier, et de se débarrasser du superflu.
       */
      nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
              .substring(nomFichier.lastIndexOf('\\') + 1);
      /* Écriture du fichier sur le disque */

      String ext = "";
      if (nomFichier != null) {
        int i = nomFichier.lastIndexOf('.');
        if (i > 0 && i < nomFichier.length() - 1) {
          ext = "." + nomFichier.substring(i + 1).toLowerCase();
        }
      }
      String nomFichier2 = idPersonne + nom + prenom + ext;
      personne.setUrlPhoto(nomFichier2);
      if (oldPhoto != "" && oldPhoto != null) {
        supprimerFichier(CHEMIN, oldPhoto);
      }
      if (!Upload.upload(part, CHEMIN, nomFichier, nomFichier2)) {
        System.out.println("probleme sur l'upload du fichier");
        vue = VUE_ERREUR;
        request.getRequestDispatcher(vue).forward(request, response);
      }
    }
    personne.setNom(nom);
    personne.setPrenom(prenom);
    personne.setEmail(email);
    personne.setMdp(mdp1);
    // nous faisons d'abord un test sur tous les champs du formulaire
    if (nom == null || nom.trim().isEmpty() || prenom == null || prenom.trim().isEmpty()
            || email == null || email.trim().isEmpty() || mdp1 == null || mdp1.trim().isEmpty() || mdp2 == null || mdp2.trim().isEmpty()) {
      request.setAttribute("erreurLogin", "Veuillez renseigner tous les champs");
    } else if (!personne.emailIsValid(email)) {
      request.setAttribute("emailEstInvalide", true);
    } else if (!personne.mdpIsValid(mdp1)) {
      request.setAttribute("mdpEstInvalide", true);
    } else if (!mdp1.equals(mdp2)) {
      request.setAttribute("mdpEstDifferent", true);
    } else {
      try {
        PersonneDao.majByIdPersonne(personne);
        vue = VUE_ACCUEIL;
        request.setAttribute("majOK", "Mise à jour effectuée");
      } catch (SQLException ex) {
        Logger.getLogger(ModifierProfilServlet.class.getName()).log(Level.SEVERE, null, ex);
        vue = VUE_ERREUR;
      }
    }
  }

  /*
 * Méthode utilitaire qui a pour unique but d'analyser l'en-tête
 * "content-disposition", et de vérifier si le paramètre "filename" y est
 * présent. Si oui, alors le champ traité est de type File et la méthode
 * retourne son nom, sinon il s'agit d'un champ de formulaire classique et
 * la méthode retourne null.
   */
  private static String getNomFichier(Part part) {
    /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
    for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
      /* Recherche de l'éventuelle présence du paramètre "filename". */
      if (contentDisposition.trim().startsWith("filename")) {
        /*
             * Si "filename" est présent, alors renvoi de sa valeur,
             * c'est-à-dire du nom de fichier sans guillemets.
         */
        return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
      }
    }
    /* Et pour terminer, si rien n'a été trouvé... */
    return null;
  }

  private static void supprimerFichier(String chemin, String nomFichier) {
    try {

      File file = new File(chemin + "/" + nomFichier);
      if (file.delete()) {
        System.out.println(file.getName() + " est supprimé.");
      } else {
        System.out.println("Opération de suppression echouée");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
