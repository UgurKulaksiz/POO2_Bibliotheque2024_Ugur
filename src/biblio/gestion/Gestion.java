package biblio.gestion;

import biblio.metier.*;
import biblio.utilitaires.CDFactoryBeta;
import biblio.utilitaires.DVDFactoryBeta;
import biblio.utilitaires.LivreFactory;
import biblio.utilitaires.LivreFactoryBeta;
import biblio.utilitaires.comparators.OuvrageComparator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static biblio.utilitaires.Utilitaire.choixListe;


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

        DVD d = new DVD("AI", 12, LocalDate.of(2000, 10, 1), 2.50, "anglais", "SF", 4578l, LocalTime.of(2, 0, 0), (byte) 2);
        d.getAutresLangues().add("français");
        d.getAutresLangues().add("italien");
        d.getSousTitres().add("néerlandais");
        listOuvrage.add(d);
        a.addOuvrage(d);

        a = new Auteur("Kubrick", "Stanley", "GB");
        listAuteur.add(a);
        a.addOuvrage(d);

        CD c = new CD("The Compil 2023", 0, LocalDate.of(2023, 1, 1), 2, "English", "POP", 1245, (byte) 20, LocalTime.of(1, 40, 0));
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
        System.out.println();
    }

    public void menu() {
        List options = new ArrayList<>(Arrays.asList("Gestion Auteur", "Gestion Ouvrage", "Gestion Lecteur", "Gestion Rayon", "Gestion Exemplaire", "Gestion Location", "Restitution", "Fin"));

        int choix;
        do {
            System.out.println("MENU PRINCIPAL");
            choix = choixListe(options);

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
                    ajoutLocation();
                    break;
                case 7:
                    gestRestitution();
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (true || choix != 8);
    }

    public void ajoutAuteur() {
        // créer auteur
        System.out.println("ENCODAGE AUTEUR ");
        System.out.println("Entrez le nom de l'auteur : ");
        String nom = sc.nextLine();

        System.out.println("Prénom : ");
        String prenom = sc.nextLine();

        System.out.println("Nationalité : ");
        String nationalite = sc.nextLine();

        Auteur a = new Auteur(nom, prenom, nationalite);
        List<Auteur> listAuteur = new ArrayList<>();
        listAuteur.add(a);

        System.out.println("Auteur créé ");

        System.out.println(listAuteur);

        //TODO attribuer ouvrages par boucle
        // les ouvrages sont triés par ordre de titre
        // ne pas proposer un ouvrage déjà présent dans la liste des ouvrages de cet auteur
        List<Ouvrage> listOuvrage2 = new ArrayList<>(listOuvrage);
        Iterator<Ouvrage> iteratorListOuvrage = listOuvrage2.iterator();

        while (iteratorListOuvrage.hasNext()) {
            if (a.getListOuvrage().contains(iteratorListOuvrage.next())) iteratorListOuvrage.remove();
        }

        listOuvrage2.sort(new OuvrageComparator());

        do {
            int choix = choixListe(listOuvrage2);

            if (choix == 0) break;
            a.addOuvrage(listOuvrage2.get(choix - 1));

            System.out.println("Ouvrage ajouté");
            listOuvrage2.remove(choix - 1);

        } while (true);

    }

    public void ajoutOuvrage() {
        /*
        // créer ouvrages
        Ouvrage o = null;

        System.out.println("Titre : ");
        String titre = sc.nextLine();
        System.out.println("Âge minimum : ");
        int ageMin = sc.nextInt();
        sc.skip("\n");
        System.out.println("Date de parution : ");
        LocalDate dateParution = Utilitaire.lecDate();
        System.out.println("Prix de location : ");
        double prixLoc = sc.nextDouble();
        sc.skip("\n");
        System.out.println("Langue : ");
        String langue = sc.nextLine();
        System.out.println("Genre : ");
        String genre = sc.nextLine();

        TypeOuvrage[] typeO = TypeOuvrage.values();
        List<TypeOuvrage> listTypeOuvrage = new ArrayList<>(Arrays.asList(typeO));
        int choix = Utilitaire.choixListe(listTypeOuvrage);

        switch (choix) {
            case 1:
                System.out.println("isbn : ");
                String isbn = sc.next();
                System.out.println("Pages ");
                int nbrePages = sc.nextInt();
                sc.skip("\n");

                TypeLivre[] typeL = TypeLivre.values();
                List<TypeLivre> ltl = new ArrayList<>(Arrays.asList(typeL));
                choix = Utilitaire.choixListe(ltl);

                TypeLivre typeLivre = typeL[choix - 1];
                System.out.println("Résumé du livre :");
                String resume = sc.nextLine();

                o = new Livre(titre, ageMin, dateParution, prixLoc, langue, genre, isbn, nbrePages, typeLivre, resume);
                break;
            case 2:
                System.out.println("Code : ");
                long code = sc.nextLong();
                System.out.println("Nombre de pages :");
                byte nbrePlages = sc.nextByte();
                LocalTime dureeTotale = Utilitaire.lecTime();

                o = new CD(titre, ageMin, dateParution, prixLoc, langue, genre, code, nbrePlages, dureeTotale);
                break;
            case 3:
                System.out.println("Code : ");
                code = sc.nextLong();
                dureeTotale = Utilitaire.lecTime();
                byte nbreBonus = sc.nextByte();

                o = new DVD(titre, ageMin, dateParution, prixLoc, langue, genre, code, dureeTotale, nbreBonus);

                System.out.println("Autres langues");
                //List<String> langues = new ArrayList<>(Arrays.asList("anglais", "français", "italien", "allemand", "fin"));
                Set<String> langues = new HashSet<>();
                do {
                    String choixLangues = sc.nextLine();
                    if (choixLangues.equals("fin")) break;

                    langues.add(choixLangues);

                } while (true);

                ((DVD) o).setAutresLangues(langues);//TODO vérifier unicité ou utiliser set et pas de doublon avec langue d'origine

                System.out.println("Sous-titres");
                Set<String> sousTitres = new HashSet<>();
                do {
                    String choixSousTitres = sc.nextLine();
                    if (choixSousTitres.equals("fin")) break;

                    sousTitres.add(choixSousTitres);
                } while (true);

                ((DVD) o).setSousTitres(sousTitres);//TODO vérifier unicité ou utiliser set

                break;
        }

         */
        TypeOuvrage[] typeOuvrages = TypeOuvrage.values();
        List<TypeOuvrage> listTypeOuvrage = new ArrayList<>(Arrays.asList(typeOuvrages));

        int choix = choixListe(listTypeOuvrage);
        if (choix == 0) return;

        Ouvrage o = null;
        switch (choix) {
            case 1:
                o = new LivreFactoryBeta().create();
                break;
            case 2:
                o = new CDFactoryBeta().create();
                break;
            case 3:
                o = new DVDFactoryBeta().create();
                break;
        }
        listOuvrage.add(o);
        System.out.println("Ouvrage créé");

        choix = choixListe(listOuvrage);
        o.addAuteur(listAuteur.get(choix - 1));

        //TODO attribuer auteurs par boucle, les auteur sont triés par ordre de nom et prénom,
        // ne pas proposer un auteur déjà présent dans la liste des auteurs de cet ouvrage
        int choixAuteur;
        String autreAuteur;
        do {
            System.out.println("Voulez-vous attribuer un autre auteur à cet ouvrage (o/n) ? ");
            autreAuteur = sc.nextLine();

            if (autreAuteur.equals("o")) {
                int i = 0;
                for (Auteur a : listAuteur) {
                    System.out.println(i + 1 + ". " + a.getNom() + " " + a.getPrenom());
                }
                System.out.println("Choix d'un autre auteur : ");
                choixAuteur = sc.nextInt();

                Auteur aChoisi = listAuteur.get(choixAuteur - 1);

                /* Vérification si l'auteur n'est pas déjà attribué à cet ouvrage */
                if (!o.getListAuteur().contains(aChoisi)) {
                    o.addAuteur(aChoisi);
                    System.out.println("Auteur attribué à l'ouvrage avec succès.");
                } else {
                    System.out.println("Cet auteur est déjà été attribué à cet ouvrage.");
                }

            }
        } while (autreAuteur.equals("o"));

    }

    public void ajoutLecteur() {
        System.out.println("Nom du lecteur : ");
        String nom = sc.nextLine();
        System.out.println("Prénom : ");
        String prenom = sc.nextLine();
        System.out.println("Date de naissance (DD MM YYYY) : ");
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
        // gérer rayons
        System.out.println("Code du rayon : ");
        String codeRayon = sc.nextLine();
        System.out.println("Genre : ");
        String genre = sc.nextLine();

        Rayon r = new Rayon(codeRayon, genre);
        listRayon.add(r);
        System.out.println("Rayon créé");

        //TODO attribuer par une boucle plusieurs exemplaires, les exemplaires sont triés par ordre de titre de l'ouvrage ,
        //  ne proposer que les exemplaires qui ne sont pas dans déjà présents dans ce rayon et qui ne sont dans aucun autre rayon
        String autreExemplaire;
        do {
            System.out.println("Voulez-vous attribuer un autre exemplaire à ce rayon (o/n) ? ");
            autreExemplaire = sc.nextLine();

            int choix = choixListe(listExemplaire);
            /* r.addExemplaire(listExemplaire.get(choix - 1)); */
            Exemplaire eChoisi = listExemplaire.get(choix - 1);

            /* Vérification si l'exemplaire n'est pas déjà dans ce rayon et n'est dans aucun autre rayon */
            if (!r.getListExemplaire().contains(eChoisi) && eChoisi.getRayon() == null) {
                r.addExemplaire(eChoisi);
                eChoisi.setRayon(r);

                System.out.println("Exemplaire attrbiué avec succès au rayon " + r.getCodeRayon());
            } else {
                System.out.println("Cet exemplaire est déjà été attribué à un rayon ou est déjà présent dans ce rayon " + r.getCodeRayon());
            }

        } while (autreExemplaire.equals("o"));
    }

    public void ajoutExemplaire() {
        // afficher les ouvrages et choisir par sa position dans la liste
        // demander autres infos de l'exemplaire et le créer
        System.out.println("Encodage de l'exemplaire");
        System.out.println("Matricule : ");
        String matricule = sc.nextLine();
        System.out.println("Description état : ");
        String descriptionEtat = sc.nextLine();
        System.out.println("Choix ouvrage : ");
        int choix = choixListe(listOuvrage);

        Exemplaire e = new Exemplaire(matricule, descriptionEtat, listOuvrage.get(choix - 1));
        listExemplaire.add(e);

        System.out.println("Exemplaire de l'ouvrage a été créé avec succés");

        // attribuer rayon
        //TODO attribuer un rayon ==> c'est fait  , nouveauté : les rayons sont triès par ordre de code
        System.out.println("Attribuer un rayon à cet exemplaire");
        listRayon.sort(Comparator.comparing(Rayon::getCodeRayon));
        System.out.println("Liste des rayons est trié par ordre de code");
        int choixRayon = choixListe(listRayon);
        Rayon r = listRayon.get(choixRayon - 1);
        e.setRayon(r);

        System.out.println("Rayon attribué à cet exemplaire");

    }

    public void ajoutLocation() {
        // lister exemplaires,lister lecteurs,créer la location avec le constructeur à deux paramètres(loueur,exemplaire)
        //TODO ne lister que les exemplaires libres et les trier par matricule
        int choix = choixListe(listExemplaire);

        if (listExemplaire.get(choix).enLocation()) {
            System.out.println("Exemplaire est en location ");
            return;
        } else {
            System.out.println("Exemplaire disponible pour la location (trié par matricule)");
            listExemplaire.sort(Comparator.comparing(Exemplaire::getMatricule));
            for (int i = 0; i < listExemplaire.size(); i++) {
                System.out.println(i + 1 + ". " + listExemplaire.get(i).getMatricule());
            }
        }

        Exemplaire ex = listExemplaire.get(choix - 1);

        choix = choixListe(listLecteur);
        Lecteur lec = listLecteur.get(choix - 1);

        listLocation.add(new Location(lec, ex));
    }

    private void gestRestitution() {
        //TODO lister exemplaires en location , choisir l'un d'entre eux, enregistrer sa restitution et éventuellement changer état
        List<Exemplaire> listExemplairesEnLocation = new ArrayList<>();

        for (Location l : listLocation) {
            listExemplairesEnLocation.add(l.getExemplaire());
        }

        /* Vérification s'il y a des exemplaires en location */
        if (listExemplairesEnLocation.isEmpty()) {
            System.out.println("Aucun exemplaire n'est actuellement en location");
            return;
        }

        /* Afficher les exemplaires en location et demander à l'utilisateur de choisir */
        System.out.println("Exemplaires actuellement en location ");
        for (int i = 0; i < listExemplairesEnLocation.size(); i++) {
            System.out.println(i + 1 + ". " + listExemplairesEnLocation.get(i).getMatricule());
        }

        int choix = choixListe(listExemplairesEnLocation);
        Exemplaire e = listExemplairesEnLocation.get(choix - 1);

        /* Enregistrer la restitution */

        /* Changer l'état */
    }
}