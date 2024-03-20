package biblio;

import biblio.metier.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static biblio.metier.TypeOuvrage.LIVRE;

public class Gestion {
    Scanner sc = new Scanner(System.in);
    private static List<Auteur> listAuteur = new ArrayList<>();
    private static List<Exemplaire> listExemplaire = new ArrayList<>();
    private static List<Lecteur> listLecteur = new ArrayList<>();
    private static List<Location> listLocation = new ArrayList<>();
    private static List<Ouvrage> listOuvrage = new ArrayList<>();
    private static List<Rayon> listRayon = new ArrayList<>();

    public static void main(String[] args) {
        Gestion g = new Gestion();
        g.populate();
        g.menu();
    }

    public void populate() {
        Auteur a = new Auteur("Verne", "Jules", "France");
        listAuteur.add(a);
        Livre l = new Livre("Vingt mille lieues sous les mers", 10, LocalDate.of(1880, 1, 1), 1.50, "français", "aventure", "a125", 350, TypeLivre.ROMAN, "histoire de sous-marin");
        listOuvrage.add(l);
        a.addOuvrage(l);

        a = new Auteur("Spielberg", "Steven", "USA");
        listAuteur.add(a);

        DVD d = new DVD("AI", 12, LocalDate.of(2000, 10, 1), 2.50, "anglais", "SF", 4578l, "120 min", (byte) 2);
        d.getAutresLangues().add("français");
        d.getAutresLangues().add("italien");
        d.getSousTitres().add("néerlandais");
        listOuvrage.add(d);
        a.addOuvrage(d);

        a = new Auteur("Kubrick", "Stanley", "GB");
        listAuteur.add(a);
        a.addOuvrage(d);

        CD c = new CD("The Compil 2023", 0, LocalDate.of(2023, 1, 1), 2, "English", "POP", 1245, (byte) 20, "100 min");
        listOuvrage.add(c);

        Rayon r = new Rayon("r12", "aventure");
        listRayon.add(r);
        Exemplaire e = new Exemplaire("m12", "état neuf", l);
        listExemplaire.add(e);
        e.setRayon(r);

        r = new Rayon("r45", "science fiction");
        listRayon.add(r);
        e = new Exemplaire("d12", "griffé", d);
        listExemplaire.add(e);
        e.setRayon(r);

        Lecteur lec = new Lecteur("Dupont", "Jean", LocalDate.of(2000, 1, 4), "Mons", "jean.dupont@mail.com", "0458774411");
        listLecteur.add(lec);

        Location loc = new Location(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 3, 1), lec, e);
        listLocation.add(loc);
        loc.setDateRestitution(LocalDate.of(2023, 2, 4));


        lec = new Lecteur("Durant", "Aline", LocalDate.of(1980, 10, 10), "Binche", "aline.durant@mail.com", "045874444");
        listLecteur.add(lec);

        loc = new Location(LocalDate.of(2023, 2, 5), LocalDate.of(2023, 3, 5), lec, e);
        listLocation.add(loc);

        System.out.println("\n" + a);
        System.out.println("\n" + l);
        System.out.println("\n" + r);
        System.out.println("\n" + e);
        System.out.println("\n" + lec);
        System.out.println("\n" + loc);
        System.out.println();


        /* ***************************************************************************************************** */
        Gestion g = new Gestion();
        g.menu();
        System.out.println();
    }

    public void menu() {
        List options = new ArrayList<>(Arrays.asList("Ajouter un auteur", "Ajouter un ouvrage", "Ajouter un lecteur", "Ajouter un rayon", "Ajouter un exemplaire", "Louer un exemplaire", "Rendre un exemplaire", "Fin"));

        int choix;
        do {
            System.out.println("MENU PRINCIPAL");
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

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
                    ajoutLecteur();
                    break;
                case 4:
                    ajoutRayon();
                    break;
                case 5:
                    ajoutExemplaire();
                    break;
                case 6:
                    /* Louer un exemplaire */
                    break;
                case 7:
                    ajoutLocation();
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
        //TODO créer auteur
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
        //TODO créer ouvrages
        System.out.println("Choix du type d'ouvrage : ");
        for (TypeOuvrage to : TypeOuvrage.values()) {
            System.out.println(to.ordinal() + 1 + ". " + to.name());
        }
        int choixTo = sc.nextInt();
        sc.nextLine();

        if (choixTo < 0 || choixTo > TypeOuvrage.values().length) {
            System.out.println("Choix invalide");
            return;
        }

        TypeOuvrage toChoisi = TypeOuvrage.values()[choixTo - 1];
        System.out.println("Ouvrage choisi : " + toChoisi.name());

        if (toChoisi == TypeOuvrage.LIVRE) {
            System.out.println("ISBN : ");
            String isbn = sc.nextLine();
            System.out.println("Nombre de pages : ");
            int nbrePages = sc.nextInt();
            sc.nextLine();

            System.out.println("Type du livre : ");
            for (TypeLivre tl : TypeLivre.values()) {
                System.out.println(tl.ordinal() + 1 + ". " + tl.name());
            }
            int choixTl = sc.nextInt();
            sc.nextLine();
            if (choixTl < 0 || choixTl >= TypeLivre.values().length) {
                System.out.println("Choix invalide");
                return;
            }
            TypeLivre tlChoisi = TypeLivre.values()[choixTl - 1];

            System.out.println("Résumé : ");
            String resume = sc.nextLine();

            Livre l = new Livre("", 0, null, 0, "", "", isbn, nbrePages, tlChoisi, resume);
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

            CD c = new CD("", 0, null, 0, "", "", code, nbrePages, dureeTotale);
            listAuteur.forEach(auteur -> auteur.getListOuvrage().add(c));
        } else if (toChoisi.name().equals(TypeOuvrage.DVD)) {
            System.out.println("Code : ");
            long code = sc.nextLong();
            sc.nextLine();
            System.out.println("Durée totale : ");
            String dureeTotale = sc.nextLine();
            System.out.println("Nombre bonus : ");
            byte nbreBonus = sc.nextByte();

            DVD d = new DVD("", 0, null, 0, "", "", code, dureeTotale, nbreBonus);
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

    public void ajoutLecteur() {
        System.out.println("Nom du lecteur : ");
        String nom = sc.nextLine();
        System.out.println("Prénom : ");
        String prenom = sc.nextLine();
        System.out.println("Date de naissance (DD/MM/YYYY) : ");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dn = LocalDate.of(a, m, j);
        System.out.println("Adresse : ");
        String adresse = sc.nextLine();
        System.out.println("E-mail : ");
        String mail = sc.nextLine();
        System.out.println("Numéro de téléphone : ");
        String tel = sc.nextLine();

        Lecteur lec = new Lecteur(nom, prenom, dn, adresse, mail, tel);
        listLecteur.add(lec);
        System.out.println("Lecteur créé");
    }

    public void ajoutRayon() {
        //TODO gérer rayons
        System.out.println("Code du rayon : ");
        String codeRayon = sc.nextLine();
        System.out.println("Genre : ");
        String genre = sc.nextLine();

        Rayon r = new Rayon(codeRayon, genre);
        listRayon.add(r);
        System.out.println("Rayon créé");
        System.out.println("eeee");
    }

    public void ajoutExemplaire() {
        //TODO afficher les ouvrages et choisir par sa position dans la liste
        //TODO demander autres infos de l'exemplaire et le créer
        System.out.println("Liste des ouvrages ");
        for (int i = 0; i < listAuteur.size(); i++) {
            Auteur a = listAuteur.get(i);

            for (Ouvrage o : a.getListOuvrage()) {
                System.out.println(i + 1 + ". " + o.getTitre());
            }
        }

        System.out.println("Choix d'un ouvrage : ");
        int choixOuvrage = sc.nextInt();
        sc.nextLine();

        if (choixOuvrage < 1 || choixOuvrage > listAuteur.size()) {
            System.out.println("Choix invalide");
            return;
        }

        Ouvrage ouvrageChoisi = listOuvrage.get(choixOuvrage - 1);
        System.out.println("Ouvrage choisi : " + ouvrageChoisi.getTitre());

        int cpt;
        cpt = 0;
        for (Auteur a : listAuteur) {
            for (Ouvrage o : a.getListOuvrage()) {
                cpt++;
                if (cpt == choixOuvrage) {
                    ouvrageChoisi = o;
                }
            }

            if (ouvrageChoisi != null) break;
        }

        System.out.println("Liste des rayons ");
        for (int i = 0; i < listRayon.size(); i++) {
            Rayon r = listRayon.get(i);

            System.out.println(i + 1 + ". " + r.getCodeRayon() + " - " + r.getGenre());
        }

        System.out.println("Choix d'un rayon ");
        int choixRayon = sc.nextInt();
        sc.nextLine();

        if (choixRayon < 1 || choixRayon > listRayon.size()) {
            System.out.println("Choix invalide");
            return;
        }

        Rayon rayonChoisi = listRayon.get(choixRayon - 1);
        System.out.println("Rayon choisi : " + rayonChoisi.getCodeRayon() + " - " + rayonChoisi.getGenre());

        System.out.println("Encodage de l'exemplaire");
        System.out.println("Matricule : ");
        String matricule = sc.nextLine();
        System.out.println("Description : ");
        String description = sc.nextLine();

        Exemplaire e = new Exemplaire(matricule, description, ouvrageChoisi);
        e.setRayon(rayonChoisi);

        /* Ajouter à la liste des exemplaires de l'ouvrage */
        ouvrageChoisi.getListExemplaire().add(e);

        System.out.println("Exemplaire de l'ouvrage a été ajouté avec succés");
    }

    public void ajoutLocation() {
        //TODO lister exemplaires,lister lecteurs,créer la location avec le constructeur à deux paramètres(loueur,exemplaire)
        List<Exemplaire> listExemplaireDispo = new ArrayList<>();

        for (Exemplaire e : listExemplaire){
            if (!e.enLocation()) listExemplaireDispo.add(e);
        }

        List<Lecteur> listLecteurDispo = new ArrayList<>();

        if (listLecteur != null){
            for (Lecteur l : listLecteur){
                listLecteurDispo = listLecteur;
            }
        }

        if (!listExemplaireDispo.isEmpty() && !listLecteurDispo.isEmpty()){
            Lecteur lec = listLecteurDispo.remove(0);
            Exemplaire e = listExemplaireDispo.remove(0);

            Location newLocation = new Location(lec, e);
            listLocation.add(newLocation);

            System.out.println("Location ajoutée avec succés");
            System.out.println(newLocation);
        }

    }
}