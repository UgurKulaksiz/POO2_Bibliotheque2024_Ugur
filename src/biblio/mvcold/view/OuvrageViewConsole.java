package biblio.mvcold.view;

import biblio.metier.Exemplaire;
import biblio.metier.Ouvrage;
import biblio.metier.TypeOuvrage;
import biblio.mvcold.controller.ControllerSpecialOuvrage;
import biblio.utilitaires.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

        Ouvrage a = null;
        List<OuvrageFactory> lof = new ArrayList<>(Arrays.asList(new LivreFactory(), new CDFactory(), new DVDFactory()));
        a = lof.get(choix - 1).create();

        //TODO affecter un ou plusieurs auteurs
        //TODO trier les auteurs présentés par ordre de nom et prénom  ==>  classe anonyme
        //TODO ne pas présenter les auteurs déjà enregistrés pour cet ouvrage
        controller.add(a);
    }

    private void retirer() {
        int nl = choixEltInt(list) - 1;
        Ouvrage a = list.get(nl);
        boolean ok = controller.remove(a);

        if (ok) affMsg("Ouvrage effacé");
        else affMsg("Ouvrage non effacé");
    }

    public void rechercher() {
        //TODO rechercher ouvrage en demandant type d'ouvrage, puis l'info unique relative à au type recherché
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
