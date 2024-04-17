package biblio.mvc.view;

import biblio.metier.Auteur;
import biblio.metier.TypeLivre;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static biblio.utilitaires.Utilitaire.*;

public class AuteurViewConsole extends AbstractView<Auteur> {
    Scanner sc = new Scanner(System.in);

    //TODO ajouter une variable d'instance de type Vue<Ouvrage> ainsi que le setter correspondant

    @Override
    public void menu() {
        update(controller.getAll());

        System.out.println();

        List options = Arrays.asList("Ajouter auteur", "Retirer auteur", "Rechercher auteur", "Modifier auteur", "Fin");
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

        controller.add(a);
    }

    private void retirer() {
        int choix = choixEltInt(list) - 1;

        Auteur a = list.get(choix);
        boolean ok = controller.remove(a);

        if (ok) affMsg("Client effacé");
        else affMsg("Client non effacé");
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

            Auteur a = controller.search(rechAuteur);
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
        System.out.println("\n" + list);

        int choix = choixEltInt(list);
        Auteur a = list.get(choix - 1);

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

        controller.update(a); /* Mise à jour de l'auteur choisi */
    }

    @Override
    public void affMsg(String msg) {
        System.out.println(msg);
    }


    public void special(Auteur a) {

        List options = Arrays.asList("Lister ouvrages", "Lister livres", "Lister par genre", "fin");
        //TODO ajouter une option "ajouter des ouvrages" qui exploitera le getAll de la vue<Ouvrage>

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

        //affListe(new ArrayList(controller.listerOuvrages(a, genre)));
    }


    public void listerOuvrages(Auteur a) {
        // affList(new ArrayList(controllerAuteur.listerOuvrages(a)));
    }


    public void listerLivres(Auteur a) {
        TypeLivre[] typeLivre = TypeLivre.values();

        int choix = choixListe(List.of(typeLivre));
        TypeLivre tl = typeLivre[choix - 1];

        //affList(new ArrayList(controllerAuteur.listerLivre(a, tl)));
    }

    @Override
    public void affList(List la) {
        affListe(la);
    }

}
