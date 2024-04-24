package biblio.metier;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Mail {
    private String objet;
    private String message;
    private String dateEnvoi;

    public Mail(String objet, String message, String dateEnvoi) {
        this.objet = objet;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "objet='" + objet + '\'' +
                ", message='" + message + '\'' +
                ", dateEnvoi='" + dateEnvoi + '\'' +
                '}';
    }

    /* METHODES */

    /* ENONCE V7
	   1.Modifiez la classe Mail afin qu'elle comporte une méthode envoi permettant de générer un fichier texte dont le nom est l'adresse
       mail du destinataire et dont le contenu correspond à l'objet et au contenu du mail.
     */
    public void envoi(String destinataire) {
        String fichier = destinataire + ".txt";

        /* Ecrire du contenu dans le fichier */
        try (FileWriter fileW = new FileWriter(fichier, true)) {
            PrintWriter ecrire = new PrintWriter(fileW);
            /* FileWriter --> Permet de fournir une interface pour permettre d'écrire dans un fichier texte */
            /* PrintWriter --> Permet d'écrire des données dans un fichier texte */

            /* Ecrire les détails du mail (objet, message, date de l'envoie) dans le fichier */
            ecrire.println("Objet : " + objet);
            ecrire.println("Message : " + message);
            ecrire.println("Date de l'envoi : " + dateEnvoi);

            System.out.println("Contenu du mail enregistré dans le fichier" + fichier + " avec succès.");

        } catch (IOException e) {
            System.out.println("Erreur : " + e);
        }
    }


}
