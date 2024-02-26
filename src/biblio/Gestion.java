package biblio;

import biblio.metier.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static biblio.metier.TypeOuvrage.LIVRE;

public class Gestion {
    Scanner sc = new Scanner(System.in);
    List<Auteur> listAuteur = new ArrayList<>();

    public static void main(String[] args) {
        Auteur a = new Auteur("Verne", "Jules", "France");
        Livre l = new Livre("Vingt mille lieues sous les mers", 10, LocalDate.of(1880, 1, 1), LIVRE, 1.50, "français", "aventure", "a125", 350, TypeLivre.ROMAN, "histoire de sous-marin");
        a.getListOuvrage().add(l);
        l.getListAuteur().add(a);

        Rayon r = new Rayon("r12", "aventure");
        Exemplaire e = new Exemplaire("m12", "état neuf", l);
        e.setRayon(r);
        r.getListExemplaire().add(e);

        Lecteur lec = new Lecteur("Dupont", "Jean", LocalDate.of(2000, 1, 4), "Mons", "jean.dupont@mail.com", "0458774411");
        Location loc = new Location(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 3, 1), lec, e);
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
                    ajoutOuvrage();
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

            Auteur a = new Auteur(nom, prenom, nationalite);
            List<Auteur> listAuteur = new ArrayList<>();
            listAuteur.add(a);

            System.out.println("Auteur " + cpt_auteur + " créé ");
            cpt_auteur++;

            System.out.println(listAuteur);

            System.out.println("Voulez-vous encoder un autre auteur (o/n) ? ");
            rep = sc.nextLine();

        } while (rep.equals("o"));
    }

    public void ajoutOuvrage() {
        System.out.println("Choix du type d'ouvrage : ");
        for (TypeOuvrage to : TypeOuvrage.values()) {
            System.out.println(to.ordinal() + ". " + to.name());
        }
        int choixTo = sc.nextInt();
        sc.nextLine();

        if (choixTo < 1 || choixTo > TypeOuvrage.values().length) {
            System.out.println("Choix invalide");
            return;
        }

        TypeOuvrage toChoisi = TypeOuvrage.values()[choixTo - 1];
        System.out.println("Ouvrage choisi : " + toChoisi.name());

        if (toChoisi.name().equals(LIVRE)) {
            System.out.println("ISBN : ");
            String isbn = sc.nextLine();
            System.out.println("Nombre de pages : ");
            int nbrePages = sc.nextInt();
            sc.nextLine();

            System.out.println("Type du livre : ");
            for (TypeLivre tl : TypeLivre.values()) {
                System.out.println(tl.ordinal() + ". " + tl.name());
            }
            TypeLivre tlChoisi = TypeLivre.values()[sc.nextInt() - 1];
            System.out.println("Résumé : ");
            String resume = sc.nextLine();

            Livre l = new Livre(isbn, nbrePages, tlChoisi, resume);
            listAuteur.forEach(auteur -> auteur.getListOuvrage().add(l));
        } else if (toChoisi.name().equals(TypeOuvrage.CD)) {
            System.out.println("Code : ");
            long code = sc.nextLong();
            sc.nextLine();
            System.out.println("Nombre de pages : ");
            byte nbrePages = sc.nextByte();
            sc.nextLine();
            System.out.println("Durée totale : ");
            String dureeTotale = sc.nextLine();

            CD c = new CD(code, nbrePages, dureeTotale);
            listAuteur.forEach(auteur -> auteur.getListOuvrage().add(c));
        } else if (toChoisi.name().equals(TypeOuvrage.DVD)) {
            System.out.println("Code : ");
            long code = sc.nextLong();
            sc.nextLine();
            System.out.println("Durée totale : ");
            LocalTime dureeTotale = LocalTime.parse(sc.nextLine());
            System.out.println("Nombre bonus : ");
            byte nbreBonus = sc.nextByte();

            DVD d = new DVD(code, dureeTotale, nbreBonus);
            listAuteur.forEach(auteur -> auteur.getListOuvrage().add(d));
        } else {
            System.out.println("Type d'ouvrage non trouvé ");
            return;
        }

        if (listAuteur.isEmpty()) {
            System.out.println("Aucun auteur disponible. Veuillez d'abord ajouter un auteur.");
            return;
        }

        System.out.println("Liste des auteurs disponibles : ");
        for (int i = 0; i < listAuteur.size(); i++) {
            Auteur auteur = listAuteur.get(i);
            System.out.println((i + 1) + ". " + auteur.getNom() + " " + auteur.getPrenom());
        }

        System.out.println("Choisissez l'auteur de l'ouvrage : ");
        int choixAuteur = sc.nextInt();
        sc.nextLine();

        if (choixAuteur < 1 || choixAuteur > listAuteur.size()) {
            System.out.println("Choix invalide");
            return;
        }

        Auteur auteurChoisi = listAuteur.get(choixAuteur - 1);
        System.out.println("L'auteur " + auteurChoisi.getNom() + " " + auteurChoisi.getPrenom() + " a été ajouté à l'ouvrage.");
    }
}