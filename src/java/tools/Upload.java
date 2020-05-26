package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;

/**
 *
 * @author Sandrine_Maillard
 */
public class Upload {

  public static boolean upload(Part part, String repertoireDestination, String nomFichier) throws IOException {
    int read = 0;
    final byte[] BYTES = new byte[1024];
    final String PATH = repertoireDestination;
    OutputStream out = null;
    InputStream filecontent = null;
    try {
      // Crée le dossier de stockage s'il n'existe pas.

      File uploadFolder = new File(PATH);
      if (!uploadFolder.exists()) {
        uploadFolder.mkdirs();
      }
      System.out.println("nom du fichier : ");
      System.out.println(nomFichier);
      System.out.println(PATH);
      out = new FileOutputStream(new File(PATH + nomFichier));
      filecontent = part.getInputStream();
      System.out.println(out);
      while ((read = filecontent.read(BYTES)) != -1) {
        out.write(BYTES, 0, read);
      }

    } catch (FileNotFoundException fileNotFound) {
       return false;

    } catch (IOException io) {
       return false;

    } finally {
      if (out != null) {
        out.close();
      }
      if (filecontent != null) {
        filecontent.close();
      }
    }
    return true;
  }

  public static String getFilenameToUpload(Part part) {
    for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
      if (contentDisposition.trim().startsWith("filename")) {
        return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
      }
    }
    return null;
  }
}
