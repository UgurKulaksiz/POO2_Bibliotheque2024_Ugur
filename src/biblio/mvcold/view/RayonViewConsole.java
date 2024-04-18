package biblio.mvcold.view;

import biblio.metier.Exemplaire;
import biblio.metier.Rayon;
import biblio.mvcold.controller.ControllerSpecialRayon;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static biblio.utilitaires.Utilitaire.*;

public class RayonViewConsole extends AbstractView<Rayon> {
    Scanner sc = new Scanner(System.in);


    @Override
    public void menu() {
        update(controller.getAll());

        List options = Arrays.asList("Ajouter rayon", "Retirer rayon", "Rechercher rayon", "Modifier rayon", "Fin");
        do {
            int ch = choixListe(options);

            switch (ch) {
                case 1:
                    ajouterRayon();
                    break;
                case 2:
                    retirerRayon();
                    break;
                case 3:
                    rechercherRayon();
                    break;
                case 4:
                    modifierRayon();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    public void ajouterRayon() {
        Rayon r;
        do {
            try {
                System.out.println("Code : ");
                String code = sc.nextLine();
                System.out.println("Genre : ");
                String genre = sc.nextLine();

                r = new Rayon(code, genre);
                break;
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        } while (true);

        r = controller.add(r);
        affMsg("Création du rayon : " + r);
    }

    private void retirerRayon() {
        int nl = choixEltInt(list) - 1;
        Rayon r = list.get(nl);
        boolean ok = controller.remove(r);

        if (ok) affMsg("Rayon effacé");
        else affMsg("Rayon non effacé");
    }

    public void rechercherRayon() {
        try {
            System.out.println("Code du rayon : ");
            String code = sc.nextLine();

            Rayon rechRayon = new Rayon(code, "");
            Rayon r = controller.search(rechRayon);

            if (r == null) affMsg("Rayon inconnu");
            else {
                affMsg(r.toString());
                special(r);
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

    }

    public void modifierRayon() {
        int choix = choixEltInt(list);
        Rayon r = list.get(choix - 1);
        do {
            try {
                String genre = modifyIfNotBlank("Genre", r.getGenre());
                r.setGenre(genre);
                break;
            } catch (Exception e) {
                System.out.println("Erreur :" + e);
            }
        } while (true);

        controller.update(r);
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }


    private void special(Rayon r) {
        List options = Arrays.asList("Lister exemplaires du rayon", "Fin");
        do {
            int ch = choixListe(options);

            switch (ch) {

                case 1:
                    listerExemplaires(r);
                    break;
                case 2:
                    return;
            }
        } while (true);

    }

    public void listerExemplaires(Rayon r) {
        List<Exemplaire> listEx = ((ControllerSpecialRayon)controller).listerExemplaires(r);
        affListe(listEx);
    }

    @Override
    public void affList(List list) {
        affListe(list);
    }


}
