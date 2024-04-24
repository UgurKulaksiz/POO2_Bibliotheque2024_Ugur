package biblio.mvcold.view;

import biblio.metier.*;
import biblio.mvcold.controller.ControllerSpecialOuvrage;
import biblio.utilitaires.*;

import java.util.*;

import static biblio.mvcold.GestionMVCold.abViewAuteur;
import static biblio.utilitaires.Utilitaire.*;

public class OuvrageViewConsole extends AbstractView<Ouvrage> {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(controller.getAll());
        List options = Arrays.asList("Ajouter ouvrage", "Retirer ouvrage", "Rechercher ouvrage", "Modifier ouvrage", "Fin");
        do {
            int ch = choixListe(options);

            switch (ch) {
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
        TypeOuvrage[] tto = TypeOuvrage.values();
        List<TypeOuvrage> lto = new ArrayList<>(Arrays.asList(tto));

        int choix = Utilitaire.choixListe(lto);

        Ouvrage o = null;
        List<OuvrageFactory> lof = new ArrayList<>(Arrays.asList(new LivreFactory(), new CDFactory(), new DVDFactory()));
        o = lof.get(choix - 1).create();
        // affecter un ou plusieurs auteurs
        List<Auteur> listA = abViewAuteur.getAll();

        // trier les auteurs présentés par ordre de nom et prénom  ==>  classe anonyme
        listA.sort(new Comparator<Auteur>() {
            @Override
            public int compare(Auteur a1, Auteur a2) {
                if (a1.getNom().equals(a2.getNom())) return a1.getPrenom().compareTo(a2.getPrenom());
                return a1.getNom().compareTo(a2.getNom());
            }
        });
        // ne pas présenter les auteurs déjà enregistrés pour cet ouvrage
        do {
            Iterator<Auteur> listIteratorAuteur = listA.iterator();

            while (listIteratorAuteur.hasNext()) {
                Auteur a = listIteratorAuteur.next();
                if (o.getListAuteur().contains(a)) listIteratorAuteur.remove();
            }

            int choix2 = choixListe(listA);

            if (choix2 == 0) break;
            o.addAuteur(listA.get(choix2 - 1));

        } while (true);

        //TODO utiliser Lambda
        controller.add(o);
    }

    private void retirer() {
        int nl = choixEltInt(list) - 1;
        Ouvrage a = list.get(nl);
        boolean ok = controller.remove(a);

        if (ok) affMsg("Ouvrage effacé");
        else affMsg("Ouvrage non effacé");
    }

    public void rechercher() {
        // rechercher ouvrage en demandant type d'ouvrage, puis l'info unique relative au type recherché
        TypeOuvrage[] typeOuvrage = TypeOuvrage.values();
        List<TypeOuvrage> listTypeOuvrage = Arrays.asList(typeOuvrage);

        int choix = Utilitaire.choixListe(listTypeOuvrage);

        Ouvrage o = null;
        switch (choix) {
            case 1:
                System.out.print("ISBN : ");
                String isbn = sc.nextLine();

                o = new Livre("", 0, null, 0, "", "", isbn, 0, TypeLivre.ROMAN, "");
                break;
            case 2:
                System.out.print("Code : ");
                int codecd = lireInt();

                o = new CD("", 0, null, 0, "", "", codecd, (byte) 0, null);
                break;
            case 3:
                System.out.print("Code : ");
                int codedvd = lireInt();

                o = new DVD("", 0, null, 0, "", "", codedvd, null, (byte) 0);
                break;
        }

        o = controller.search(o);

        if (o != null) {
            affMsg(o.toString());
        } else {
            affMsg("Ouvrage inconnu");
        }
    }


    public void modifier() {
        int choix = choixEltInt(list);
        Ouvrage o = list.get(choix - 1);
        do {
            try {
                double prixLoc = Double.parseDouble(modifyIfNotBlank("Prix location : ", "" + o.getPrixLocation()));

                o.setPrixLocation(prixLoc);
                break;
            } catch (Exception e) {
                System.out.println("Erreur : " + e);
            }
        } while (true);

        controller.update(o);
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }


    protected void special() {
        int choix = choixEltInt(list);
        Ouvrage o = list.get(choix - 1);

        List options = new ArrayList<>(Arrays.asList("Lister exemplaires", "Lister exemplaires en location", "Lister exemplaires libres", "Fin"));
        do {
            int ch = choixListe(options);

            switch (ch) {
                case 1:
                    exemplaires(o);
                    break;
                case 2:
                    enLocation(o, true);
                    break;
                case 3:
                    enLocation(o, false);
                    break;

                case 4:
                    return;
            }
        } while (true);

    }

    public void enLocation(Ouvrage o, boolean enLocation) {
        List<Exemplaire> l = ((ControllerSpecialOuvrage) controller).listerExemplaireOuvrageLocation(o, enLocation);
        affList(l);
    }

    public void exemplaires(Ouvrage o) {
        List<Exemplaire> l = ((ControllerSpecialOuvrage) controller).listerExemplaireOuvrage(o);
        affList(l);
    }

    @Override
    public void affList(List la) {
        affListe(la);
    }
}
