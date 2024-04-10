package biblio.mvc.view;

import biblio.metier.Auteur;
import biblio.metier.TypeLivre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static biblio.utilitaires.Utilitaire.*;

public class AuteurViewConsole extends AbstractViewAuteur {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(auteurController.getAll());

        List options = Arrays.asList("Ajouter auteur", "Retirer auteur", "Rechercher auteur", "Modifier auteur", "fin");
        do {
            int choix = choixListe(options);

            switch (choix) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    private void retirer() {
        int choix = choixEltInt(listAuteur) - 1;

        Auteur a = listAuteur.get(choix);
        boolean ok = auteurController.remove(a);

        if (ok) affMsg("Client effacé");
        else affMsg("Client non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }


    public void rechercher() {
        try {
            System.out.println("Nom : ");
            String nom = sc.nextLine();
            System.out.println("Prénom : ");
            String prenom = sc.nextLine();
            System.out.println("Nationalité : ");
            String nat = sc.nextLine();

            Auteur rechAuteur = new Auteur(nom, prenom, nat);

            Auteur a = auteurController.search(rechAuteur);
            if (a == null) affMsg("Auteur inconnu");
            else {
                affMsg(a.toString());
                special(a);
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

    }


    public void modifier() {
        int choix = choixEltInt(listAuteur);
        Auteur a = listAuteur.get(choix - 1);

        do {
            try {
                String nom = modifyIfNotBlank("Nom : ", a.getNom());
                String prenom = modifyIfNotBlank("Prénom : ", a.getPrenom());
                String nat = modifyIfNotBlank("Nationalité : ", a.getNationalite());

                a.setNom(nom);
                a.setPrenom(prenom);
                a.setNationalite(nat);

                break;
            } catch (Exception e) {
                System.out.println("Erreur :" + e);
            }
        } while (true);

        auteurController.update(a); /* Mise à jour de l'auteur choisi */
    }


    public void ajouter() {
        Auteur a;
        do {
            try {
                System.out.println("Nom : ");
                String nom = sc.nextLine();
                System.out.println("Prénom : ");
                String prenom = sc.nextLine();
                System.out.println("Nationalité : ");
                String nat = sc.nextLine();

                a = new Auteur(nom, prenom, nat);
                break;
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        } while (true);

        auteurController.add(a);
    }

    public void special(Auteur a) {

        List options = Arrays.asList("Lister ouvrages", "Lister livres", "Lister par genre", "fin");
        do {
            int choix = choixListe(options);

            switch (choix) {
                case 1:
                    listerOuvrages(a);
                    break;
                case 2:
                    listerLivres(a);
                    break;
                case 3:
                    listerGenre(a);
                    break;
                case 4:
                    return;
            }
        } while (true);

    }


    public void listerGenre(Auteur a) {
        System.out.println("Genre :");
        String genre = sc.nextLine();

        affListe(new ArrayList(auteurController.listerOuvrages(a, genre)));
    }


    public void listerOuvrages(Auteur a) {
        affList(new ArrayList(auteurController.listerOuvrages(a)));
    }


    public void listerLivres(Auteur a) {
        TypeLivre[] typeLivre = TypeLivre.values();

        int choix = choixListe(List.of(typeLivre));
        TypeLivre tl = typeLivre[choix - 1];

        affList(new ArrayList(auteurController.listerLivre(a, tl)));
    }

    @Override
    public void affList(List la) {
        affListe(la);
    }
}
