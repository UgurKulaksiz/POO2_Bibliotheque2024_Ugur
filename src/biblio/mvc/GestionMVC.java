package biblio.mvc;

import biblio.metier.Auteur;
import biblio.mvc.controller.AuteurController;
import biblio.mvc.model.AuteurModel;
import biblio.mvc.model.DAOAuteur;
import biblio.mvc.view.AbstractViewAuteur;
import biblio.mvc.view.AuteurViewConsole;
import biblio.utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestionMVC {
    private DAOAuteur modelAuteur;
    private AbstractViewAuteur abViewAuteur;
    private AuteurController controllerAuteur;

    public void gestion() {
        modelAuteur = new AuteurModel();
        abViewAuteur = new AuteurViewConsole();
        controllerAuteur = new AuteurController(modelAuteur, abViewAuteur);//création et injection de dépendance

        modelAuteur.addObserver(abViewAuteur);

        try {
            populate();

        } catch (Exception e) {
            System.out.println("Erreur lors du populate : " + e);
            System.exit(1);
        }

        List<String> listOptions = Arrays.asList("Auteurs", "Fin");
        do {
            int choix = Utilitaire.choixListe(listOptions);
            switch (choix) {
                case 1:
                    abViewAuteur.menu();
                    break;
                case 2:
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
    }

    public static void main(String[] args) {
        GestionMVC gMVC = new GestionMVC();
        gMVC.gestion();
    }
}
