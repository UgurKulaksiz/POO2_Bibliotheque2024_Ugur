package biblio.mvcold.view;

import biblio.metier.Exemplaire;
import biblio.metier.Mail;
import biblio.metier.Ouvrage;
import biblio.metier.Rayon;
import biblio.mvcold.GestionMVCold;
import biblio.mvcold.controller.ControllerSpecialExemplaire;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static biblio.utilitaires.Utilitaire.*;
import static biblio.utilitaires.Utilitaire.affListe;

public class ExemplaireViewConsole extends AbstractView<Exemplaire> {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(controller.getAll());

        System.out.println();

        List options = Arrays.asList("Ajouter exemplaire", "Retirer exemplaire", "Rechercher exemplaire", "Modifier exemplaire", "Fin");
        do {
            int choix = choixListe(options);

            switch (choix) {
                case 1:
                    ajouterExemplaire();
                    break;
                case 2:
                    retirerExemplaire();
                    break;
                case 3:
                    rechercherExemplaire();
                    break;
                case 4:
                    modifierExemplaire();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    public void ajouterExemplaire() {
        Exemplaire ex;
        do {
            try {
                System.out.println("Matricule : ");
                String matricule = sc.nextLine();
                System.out.println("Description de l'état : ");
                String descriptionEtat = sc.nextLine();

                System.out.println("Ouvrage : ");
                List<Ouvrage> listO = GestionMVCold.abViewOuvrage.getAll();
                //TODO présenter les ouvrages par ordre de titre ==> classe anonyme

                int ch = choixListe(listO);
                ex = new Exemplaire(matricule, descriptionEtat, listO.get(ch - 1));

                System.out.println("Rayon");
                List<Rayon> listR = GestionMVCold.abViewRayon.getAll();
                //TODO présenter les rayons par ordre de code ==> classe anonyme

                ch = choixListe(listR);
                ex.setRayon(listR.get(ch - 1));

                break;
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        } while (true);

        controller.add(ex);
    }

    private void retirerExemplaire() {
        int choix = choixEltInt(list) - 1;

        Exemplaire e = list.get(choix);
        boolean ok = controller.remove(e);

        if (ok) affMsg("Exemplaire effacé");
        else affMsg("Exemplaire non effacé");
    }

    public void rechercherExemplaire() {
        try {
            System.out.println("Matricule : ");
            String matricule = sc.nextLine();

            Exemplaire rechExemplaire = new Exemplaire(matricule, "", null);

            Exemplaire ex = controller.search(rechExemplaire);
            if (ex == null) affMsg("Exemplaire inconnu");
            else {
                affMsg(ex.toString());
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

    }

    public void modifierExemplaire() {
        System.out.println("\n" + list);

        int choix = choixEltInt(list);
        Exemplaire ex = list.get(choix - 1);

        do {
            try {
                String descrptionEtat = modifyIfNotBlank("Description de l'état : ", ex.getDescriptionEtat());

                ex.setDescriptionEtat(descrptionEtat);
                break;
            } catch (Exception e) {
                System.out.println("Erreur :" + e);
            }
        } while (true);

        controller.update(ex); /* Mise à jour de l'exemplaire choisi */
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void affList(List le) {
        affListe(le);
    }

    public void special(Exemplaire a) {

        List options = Arrays.asList("Modifier état", "Lecteur actuel", "Envoi mail", "En location", "Louer", "Rendre", "Fin");
        do {
            int ch = choixListe(options);

            switch (ch) {
                case 1:
                    modifierEtat(a);
                    break;
                case 2:
                    lecteurActuel(a);
                    break;
                case 3:
                    envoiMail(a);
                    break;
                case 4:
                    enLocation(a);
                    break;
                case 5:
                    louer(a);
                    break;
                case 6:
                    rendre(a);
                    break;
                case 7:
                    return;
            }
        } while (true);

    }

    private void rendre(Exemplaire a) {
        GestionMVCold.LOCATIONS.remove(a);
    }

    private void louer(Exemplaire a) {
        //TODO chosir un lecteur et enregistrer la location dans LOCATIONS
    }


    public void enLocation(Exemplaire ex) {
        boolean loc = ((ControllerSpecialExemplaire) controller).enLocation(ex);

        if (loc) System.out.println("En location");
        else System.out.println("Pas en location");
    }


    public void envoiMail(Exemplaire ex) {
        Mail m = new Mail("demo", "message de test", "01-01-2024");

        ((ControllerSpecialExemplaire) controller).envoiMailLecteurActuel(ex, m);
    }


    public void lecteurActuel(Exemplaire ex) {
        ((ControllerSpecialExemplaire) controller).LecteurActuel(ex);
    }


    public void modifierEtat(Exemplaire ex) {
        System.out.println("Nouvel état :");
        String etat = sc.nextLine();

        ((ControllerSpecialExemplaire) controller).modifierEtat(ex, etat);
    }

}
