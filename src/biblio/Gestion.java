package biblio;

import biblio.metier.TypeLivre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static biblio.metier.TypeOuvrage.LIVRE;

public class Gestion {
    Scanner sc = new Scanner(System.in);
    List<biblio.metier.Auteur> listAuteur = new ArrayList<>();

    public static void main(String[] args) {
        biblio.metier.Auteur a = new biblio.metier.Auteur("Verne", "Jules", "France");
        biblio.metier.Livre l = new biblio.metier.Livre("Vingt mille lieues sous les mers", 10, LocalDate.of(1880, 1, 1), LIVRE, 1.50, "français", "aventure", "a125", 350, TypeLivre.ROMAN, "histoire de sous-marin");
        a.getListOuvrage().add(l);
        l.getListAuteur().add(a);

        biblio.metier.Rayon r = new biblio.metier.Rayon("r12", "aventure");
        biblio.metier.Exemplaire e = new biblio.metier.Exemplaire("m12", "état neuf", l);
        e.setRayon(r);
        r.getListExemplaire().add(e);

        biblio.metier.Lecteur lec = new biblio.metier.Lecteur("Dupont", "Jean", LocalDate.of(2000, 1, 4), "Mons", "jean.dupont@mail.com", "0458774411");
        biblio.metier.Location loc = new biblio.metier.Location(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 3, 1), lec, e);
        lec.getListLocation().add(loc);
        e.getListLocation().add(loc);

        /*
        Lecteur lec2 = new Lecteur("Durant", "Louis", LocalDate.of(2005, 1, 5), "Tournai", "louis.durant@mail.com", "0495241010");
        Location loc2 = new Location(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 3, 1), lec2, e);
        lec2.getListLocation().add(loc2);
        */

        System.out.println("\n" + a);
        System.out.println("\n" + l);
        System.out.println("\n" + r);
        System.out.println("\n" + e);
        System.out.println("\n" + lec);
        //System.out.println(lec2);
        System.out.println("\n" + loc);
        //System.out.println("\n" + loc2);
        System.out.println();


        /* ***************************************************************************************************** */
        Gestion g = new Gestion();
        g.menu();
        System.out.println();
    }

    public void menu() {
        int choix;
        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Ajouter un auteur");
            System.out.println("2. Ajouter un ouvrage");
            System.out.println("3. Ajouter un lecteur");
            System.out.println("4. Ajouter un rayon");
            System.out.println("5. Ajouter un exemplaire");
            System.out.println("6. Louer un exemplaire");
            System.out.println("7. Rendre un exemplaire");
            System.out.println("8. Fin");

            System.out.print("Choix: ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    ajoutAuteur();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Fin du programme.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 8);
    }

    public void ajoutAuteur() {
        int cpt_auteur = 1;
        String rep;
        do {
            System.out.println("ENCODAGE AUTEUR " + cpt_auteur);
            System.out.println("Entrez le nom de l'auteur : ");
            String nom = sc.nextLine();

            System.out.println("Prénom : ");
            String prenom = sc.nextLine();

            System.out.println("Nationalité : ");
            String nationalite = sc.nextLine();

            biblio.metier.Auteur a = new biblio.metier.Auteur(nom, prenom, nationalite);
            List<biblio.metier.Auteur> listAuteur = new ArrayList<>();
            listAuteur.add(a);

            System.out.println("Auteur " + cpt_auteur + " créé ");
            cpt_auteur++;

            System.out.println(listAuteur);

            System.out.println("Voulez-vous encoder un autre auteur (o/n) ? ");
            rep = sc.nextLine();

        } while (rep.equals("o"));
        System.out.println("");
    }
}