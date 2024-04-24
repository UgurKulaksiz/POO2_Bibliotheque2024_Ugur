package biblio.mvcold;

import biblio.metier.*;
import biblio.mvcold.controller.*;
import biblio.mvcold.model.*;
import biblio.mvcold.view.*;
import biblio.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GestionMVCold {
    public static DAO<Auteur> modelAuteur;
    public static AbstractView<Auteur> abViewAuteur;
    public static Controller<Auteur> controllerAuteur;

    public static DAO<Ouvrage> modelOuvrage;
    public static AbstractView<Ouvrage> abViewOuvrage;
    public static Controller<Ouvrage> controllerOuvrage;

    public static DAO<Exemplaire> modelExemplaire;
    public static AbstractView<Exemplaire> abViewExemplaire;
    public static Controller<Exemplaire> controllerExemplaire;

    public static DAO<Lecteur> modelLecteur;
    public static AbstractView<Lecteur> abViewLecteur;
    public static Controller<Lecteur> controllerLecteur;

    public static DAO<Rayon> modelRayon;
    public static AbstractView<Rayon> abViewRayon;
    public static Controller<Rayon> controllerRayon;

    public static HashMap<Exemplaire, Lecteur> LOCATIONS = new HashMap<>();


    public void gestion() {
        modelAuteur = new ModelAuteur();
        abViewAuteur = new AuteurViewConsole();
        controllerAuteur = new ControllerAuteur(modelAuteur, abViewAuteur);//création et injection de dépendance
        modelAuteur.addObserver(abViewAuteur);

        // créer les éléments relatifs aux autres classes
        // associer les vues entre elles pour exploiter leurs getAll()

        modelOuvrage = new ModelOuvrage();
        abViewOuvrage = new OuvrageViewConsole();
        controllerOuvrage = new ControllerOuvrage(modelOuvrage, abViewOuvrage);
        modelOuvrage.addObserver(abViewOuvrage);

        modelExemplaire = new ModelExemplaire();
        abViewExemplaire = new ExemplaireViewConsole();
        controllerExemplaire = new Controller<>(modelExemplaire, abViewExemplaire);
        modelExemplaire.addObserver(abViewExemplaire);

        modelLecteur = new ModelLecteur();
        abViewLecteur = new LecteurViewConsole();
        controllerLecteur = new Controller<>(modelLecteur, abViewLecteur);
        modelLecteur.addObserver(abViewLecteur);

        modelRayon = new ModelRayon();
        abViewRayon = new RayonViewConsole();
        controllerRayon = new Controller<>(modelRayon, abViewRayon);
        modelRayon.addObserver(abViewRayon);

        try {
            populate();

        } catch (Exception e) {
            System.out.println("Erreur lors du populate : " + e);
            System.exit(1);
        }

        List<String> listOptions = Arrays.asList("Auteurs", "Exemplaires", "Lecteurs", "Rayons", "Fin");
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
        Auteur a1 = new Auteur("Verne", "Jules", "France");
        modelAuteur.getAll().add(a1);

        Auteur a2 = new Auteur("Spielberg", "Steven", "USA");
        modelAuteur.getAll().add(a2);

        Auteur a3 = new Auteur("Kubrick", "Stanley", "GB");
        modelAuteur.getAll().add(a3);

        // ajouter autres éléments, les associer entre eux et créer des locations

        Livre l = new Livre("Vingt mille lieues sous les mers", 10, LocalDate.of(1880, 1, 1), 1.50, "français", "aventure", "a125", 350, TypeLivre.ROMAN, "histoire de sous-marin");
        modelOuvrage.add(l);
        a1.addOuvrage(l);

        DVD d = new DVD("AI", 12, LocalDate.of(2000, 10, 1), 2.50, "anglais", "SF", 4578l, LocalTime.of(2, 0, 0), (byte) 2);
        d.getAutresLangues().add("français");
        d.getAutresLangues().add("italien");
        d.getSousTitres().add("néerlandais");
        modelOuvrage.add(d);

        a2.addOuvrage(d);
        a3.addOuvrage(d);

        CD c = new CD("The Compil 2023", 0, LocalDate.of(2023, 1, 1), 2, "English", "POP", 1245, (byte) 20, LocalTime.of(1, 40, 0));
        modelOuvrage.add(c);

        Rayon r = new Rayon("r12", "aventure");
        modelRayon.add(r);

        Exemplaire e = new Exemplaire("m12", "état neuf", l);
        modelExemplaire.add(e);
        e.setRayon(r);


        r = new Rayon("r45", "science fiction");
        modelRayon.add(r);

        e = new Exemplaire("d12", "griffé", d);
        modelExemplaire.add(e);

        e.setRayon(r);


        Lecteur lec = new Lecteur("Dupont", "Jean", LocalDate.of(2000, 1, 4), "Mons", "jean.dupont@mail.com", "0458774411");
        modelLecteur.add(lec);

        LOCATIONS.put(e, lec);

        lec = new Lecteur("Durant", "Aline", LocalDate.of(1980, 10, 10), "Binche", "aline.durant@mail.com", "045874444");
        modelLecteur.add(lec);
    }

    public static void main(String[] args) {
        GestionMVCold gMVC = new GestionMVCold();
        gMVC.gestion();
    }
}
