package biblio.mvc;

import biblio.metier.Auteur;
import biblio.metier.Exemplaire;
import biblio.metier.Lecteur;
import biblio.metier.Rayon;
import biblio.mvcold.GestionMVCold;
import biblio.mvcold.controller.Controller;
import biblio.mvcold.model.*;
import biblio.mvcold.view.*;
import biblio.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GestionMVC {
    private DAO<Auteur> modelAuteur;
    private AbstractView<Auteur> abViewAuteur;
    private Controller<Auteur> controllerAuteur;

    private DAO<Exemplaire> modelExemplaire;
    private AbstractView<Exemplaire> abViewExemplaire;
    private Controller<Exemplaire> controllerExemplaire;

    public static final HashMap<Exemplaire,Lecteur> LOCATION = new HashMap<>();

    private DAO<Lecteur> modelLecteur;
    private AbstractView<Lecteur> abViewLecteur;
    private Controller<Lecteur> controllerLecteur;

    private DAO<Rayon> modelRayon;
    private AbstractView<Rayon> abViewRayon;
    private Controller<Rayon> controllerRayon;

    public void gestion() {
        modelAuteur = new ModelAuteur();
        abViewAuteur = new AuteurViewConsole();
        controllerAuteur = new Controller<>(modelAuteur, abViewAuteur);//création et injection de dépendance

        //TODO créer les éléments relatifs aux autres classes
        //TODO associer les vues entre elles pour exploiter leurs getAll()

        modelExemplaire = new ModelExemplaire();
        abViewExemplaire = new ExemplaireViewConsole();
        controllerExemplaire = new Controller<>(modelExemplaire, abViewExemplaire);

        modelLecteur = new ModelLecteur();
        abViewLecteur = new LecteurViewConsole();
        controllerLecteur = new Controller<>(modelLecteur, abViewLecteur);

        modelRayon = new ModelRayon();
        abViewRayon = new RayonViewConsole();
        controllerRayon = new Controller<>(modelRayon, abViewRayon);

        modelAuteur.addObserver(abViewAuteur);
        modelExemplaire.addObserver(abViewExemplaire);
        modelLecteur.addObserver(abViewLecteur);
        modelRayon.addObserver(abViewRayon);

        try {
            populate();

        } catch (Exception e) {
            System.out.println("Erreur lors du populate : " + e);
            System.exit(1);
        }

        List<String> listOptions = Arrays.asList("Auteurs", "Exemplaires", "Lecteur", "Rayon", "Fin");
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
                    abViewRayon.menu();
                    break;
                case 5:
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

        //TODO ajouter autres éléments, les associer entre eux et créer des locations

        Exemplaire e = new Exemplaire("a70", "peu utilisée");
        modelExemplaire.getAll().add(e);

        e = new Exemplaire("b50", "état neuf");
        modelExemplaire.getAll().add(e);

        Lecteur l = new Lecteur("Dupont", "Louis", LocalDate.of(2000, 5, 12), "La Louvière", "louis.dupont@gmail.com", "0485152535");
        modelLecteur.getAll().add(l);

        Rayon r = new Rayon("r12", "aventure");
        modelRayon.add(r);

        r = new Rayon("a70", "documentaire");
        modelRayon.add(r);
    }

    public static void main(String[] args) {
        GestionMVC gMVC = new GestionMVC();
        gMVC.gestion();
    }
}
