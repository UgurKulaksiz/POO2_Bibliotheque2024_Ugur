package biblio.gestion;

import biblio.metier.*;
import biblio.utilitaires.*;
import biblio.utilitaires.comparators.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static biblio.utilitaires.Utilitaire.choixListe;


public class GestionOld {
    Scanner sc = new Scanner(System.in);
    //Ôter static pour les listes qui n'est plus nécessaire
    private List<Auteur> listAuteur = new ArrayList<>();
    private List<Exemplaire> listExemplaire = new ArrayList<>();
    private List<Lecteur> listLecteur = new ArrayList<>();
    private List<Ouvrage> listOuvrage = new ArrayList<>();
    private List<Rayon> listRayon = new ArrayList<>();
    public static final Map<Exemplaire, Lecteur> LOCATIONS = new HashMap(); /* Enoncé V2 */

    public static void main(String[] args) {
        GestionOld g = new GestionOld();
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

        /* Enoncé V2 */
        LOCATIONS.put(e, lec);


        lec = new Lecteur("Durant", "Aline", LocalDate.of(1980, 10, 10), "Binche", "aline.durant@mail.com", "045874444");
        listLecteur.add(lec);

        /* Enoncé V2 */
        LOCATIONS.put(e, lec);

        System.out.println("\n" + a);
        System.out.println("\n" + l);
        System.out.println("\n" + r);
        System.out.println("\n" + e);
        System.out.println("\n" + lec);
        System.out.println("\n" + LOCATIONS);
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
        listAuteur.add(a);

        System.out.println("Auteur créé ");

        System.out.println(listAuteur);

        // attribuer ouvrages par boucle
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

            if (choix == 0) break; /* Permet d'arrêter de parcourir (choisir) dans la liste Ouvrage */
            a.addOuvrage(listOuvrage2.get(choix - 1));

            System.out.println("Ouvrage ajouté");
            listOuvrage2.remove(choix - 1);

        } while (true);

    }

    public void ajoutOuvrage() {
        /* créer ouvrage */
        TypeOuvrage[] typeOuvrages = TypeOuvrage.values();
        List<TypeOuvrage> listTypeOuvrage = new ArrayList<>(Arrays.asList(typeOuvrages));

        int choix = choixListe(listTypeOuvrage);
        if (choix == 0) return;

        Ouvrage o = null;
        List<OuvrageFactory> listOuvrageFactory = Arrays.asList(new LivreFactory(), new CDFactory(), new DVDFactory());
        OuvrageFactory of = listOuvrageFactory.get(choix - 1);
        listOuvrage.add(o);
        System.out.println("Ouvrage créé");

        choix = choixListe(listOuvrage);
        o.addAuteur(listAuteur.get(choix - 1));

        // attribuer auteurs par boucle, les auteur sont triés par ordre de nom et prénom,
        // ne pas proposer un auteur déjà présent dans la liste des auteurs de cet ouvrage
        List<Auteur> listAuteur2 = new ArrayList<>(listAuteur);
        Iterator<Auteur> iteratorListAuteur = listAuteur2.iterator();

        while (iteratorListAuteur.hasNext()) {
            if (o.getListAuteur().contains(iteratorListAuteur.hasNext())) iteratorListAuteur.remove();
        }

        listAuteur2.sort(new AuteurComparator());
        do {
            choix = choixListe(listAuteur2);

            if (choix == 0) break; /* Permet d'arrêter de parcourir (choisir) dans la liste Auteur */
            o.addAuteur(listAuteur2.get(choix - 1));
            listAuteur2.remove(choix - 1);
            System.out.println("Auteur ajouté");
        } while (true);

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

        // attribuer par une boucle plusieurs exemplaires, les exemplaires sont triés par ordre de titre de l'ouvrage ,
        //  ne proposer que les exemplaires qui ne sont pas dans déjà présents dans ce rayon et qui ne sont dans aucun autre rayon
        List<Exemplaire> listExemplaire2 = new ArrayList<>(listExemplaire);
        Iterator<Exemplaire> iteratorListExemplaire = listExemplaire2.iterator();

        while (iteratorListExemplaire.hasNext()) {
            Rayon ray = iteratorListExemplaire.next().getRayon();

            if (r.equals(ray)) iteratorListExemplaire.remove();
        }

        listExemplaire2.sort(new ExemplaireTitreComparator());
        do {
            int choix = choixListe(listExemplaire2);
            if (choix == 0) break; /* Permet d'arrêter de parcourir (choisir) dans la liste Exemplaire */
            r.addExemplaire(listExemplaire.get(choix - 1));
            System.out.println("Exemplaire ajouté");
            listExemplaire2.remove(choix - 1);
        } while (true);
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
        // attribuer un rayon ==> c'est fait  , nouveauté : les rayons sont triès par ordre de code
        System.out.println("Attribuer un rayon à cet exemplaire");
        listRayon.sort(new RayonComparator());
        System.out.println("Liste des rayons est trié par ordre de code");
        choix = choixListe(listRayon);
        if (choix == 0) return;
        Rayon r = listRayon.get(choix - 1);
        e.setRayon(r);

        System.out.println("Rayon attribué à cet exemplaire");

    }

    public void ajoutLocation() {
        // lister exemplaires,lister lecteurs,créer la location avec le constructeur à deux paramètres(loueur,exemplaire)
        // ne lister que les exemplaires libres et les trier par matricule
        int choix;
        List<Exemplaire> listExemplaire2 = new ArrayList<>(listExemplaire);
        Iterator<Exemplaire> iteratorListExemplaire = listExemplaire2.iterator();

        while (iteratorListExemplaire.hasNext()) {
            if (iteratorListExemplaire.next().enLocation())
                iteratorListExemplaire.remove();
        }

        listExemplaire2.sort(new ExemplaireMatriculeComparator());
        choix = choixListe(listExemplaire2);
        if (choix == 0) return;
        Exemplaire e = listExemplaire2.get(choix - 1);

        choix = choixListe(listLecteur);
        if (choix == 0) return;
        Lecteur l = listLecteur.get(choix - 1);

        LOCATIONS.put(e, l);
    }

    private void gestRestitution() {
        //TODO lister exemplaires en location , choisir l'un d'entre eux, enregistrer sa restitution et éventuellement changer état
    }
}