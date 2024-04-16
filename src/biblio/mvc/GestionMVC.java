package biblio.mvc;

import biblio.metier.Auteur;
import biblio.metier.Exemplaire;
import biblio.metier.Lecteur;
import biblio.mvc.controller.AuteurController;
import biblio.mvc.controller.ExemplaireController;
import biblio.mvc.controller.LecteurController;
import biblio.mvc.model.*;
import biblio.mvc.view.*;
import biblio.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class GestionMVC {
    private DAOAuteur modelAuteur;
    private AbstractViewAuteur abViewAuteur;
    private AuteurController controllerAuteur;

    private DAOExemplaire modelExemplaire;
    private AbstractViewExemplaire abViewExemplaire;
    private ExemplaireController controllerExemplaire;

    private DAOLecteur modelLecteur;
    private AbstractViewLecteur abViewLecteur;
    private LecteurController controllerLecteur;

    public void gestion() {
        modelAuteur = new AuteurModel();
        abViewAuteur = new AuteurViewConsole();
        controllerAuteur = new AuteurController(modelAuteur, abViewAuteur);//création et injection de dépendance

        modelExemplaire = new ExemplaireModel();
        abViewExemplaire = new ExemplaireViewConsole();
        controllerExemplaire = new ExemplaireController(modelExemplaire, abViewExemplaire);

        modelLecteur = new LecteurModel();
        abViewLecteur = new LecteurViewConsole();
        controllerLecteur = new LecteurController(modelLecteur, abViewLecteur);

        modelAuteur.addObserver(abViewAuteur);
        modelExemplaire.addObserver(abViewExemplaire);

        try {
            populate();

        } catch (Exception e) {
            System.out.println("Erreur lors du populate : " + e);
            System.exit(1);
        }

        List<String> listOptions = Arrays.asList("Auteurs", "Exemplaires", "Lecteur", "Fin");
        do {
            int choix = Utilitaire.choixListe(listOptions);
            switch (choix) {
                case 1:
                    abViewAuteur.menu();
                    break;
                case 2:
                    abViewExemplaire.menu();
                    break;
                case 3:
                    abViewLecteur.menu();
                    break;
                case 4:
                    System.exit(0);
            }
        } while (true);
    }

    public void populate() {
        Auteur a = new Auteur("Verne", "Jules", "France");
        modelAuteur.getAll().add(a);

        a = new Auteur("Spielberg", "Steven", "USA");
        modelAuteur.getAll().add(a);

        a = new Auteur("Kubrick", "Stanley", "GB");
        modelAuteur.getAll().add(a);

        Exemplaire e = new Exemplaire("a70", "peu utilisée");
        modelExemplaire.getAll().add(e);

        e = new Exemplaire("b50", "état neuf");
        modelExemplaire.getAll().add(e);

        Lecteur l = new Lecteur("Dupont", "Louis", LocalDate.of(2000, 5, 12), "La Louvière", "louis.dupont@gmail.com", "0485152535");
        modelLecteur.getAll().add(l);
    }

    public static void main(String[] args) {
        GestionMVC gMVC = new GestionMVC();
        gMVC.gestion();
    }
}
