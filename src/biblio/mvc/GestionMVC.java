package biblio.mvc;

import biblio.metier.Auteur;
import biblio.metier.Exemplaire;
import biblio.mvc.controller.AuteurController;
import biblio.mvc.controller.ExemplaireController;
import biblio.mvc.model.AuteurModel;
import biblio.mvc.model.DAOAuteur;
import biblio.mvc.model.DAOExemplaire;
import biblio.mvc.model.ExemplaireModel;
import biblio.mvc.view.AbstractViewAuteur;
import biblio.mvc.view.AbstractViewExemplaire;
import biblio.mvc.view.AuteurViewConsole;
import biblio.mvc.view.ExemplaireViewConsole;
import biblio.utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestionMVC {
    private DAOAuteur modelAuteur;
    private AbstractViewAuteur abViewAuteur;
    private AuteurController controllerAuteur;

    private DAOExemplaire modelExemplaire;
    private AbstractViewExemplaire abViewExemplaire;
    private ExemplaireController controllerExemplaire;

    public void gestion() {
        modelAuteur = new AuteurModel();
        abViewAuteur = new AuteurViewConsole();
        controllerAuteur = new AuteurController(modelAuteur, abViewAuteur);//création et injection de dépendance

        modelExemplaire = new ExemplaireModel();
        abViewExemplaire = new ExemplaireViewConsole();
        controllerExemplaire = new ExemplaireController(modelExemplaire, abViewExemplaire);

        modelAuteur.addObserver(abViewAuteur);
        modelExemplaire.addObserver(abViewExemplaire);

        try {
            populate();

        } catch (Exception e) {
            System.out.println("Erreur lors du populate : " + e);
            System.exit(1);
        }

        List<String> listOptions = Arrays.asList("Auteurs", "Exemplaires", "Fin");
        do {
            int choix = Utilitaire.choixListe(listOptions);
            switch (choix) {
                case 1:
                    abViewAuteur.menu();
                    break;
                case 2:
                    abViewExemplaire.menu();
                case 3:
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
    }

    public static void main(String[] args) {
        GestionMVC gMVC = new GestionMVC();
        gMVC.gestion();
    }
}
