package biblio.mvcold.view;

import biblio.metier.Exemplaire;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static biblio.utilitaires.Utilitaire.*;
import static biblio.utilitaires.Utilitaire.affListe;

public class ExemplaireViewConsole extends AbstractView<Exemplaire>{
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(controller.getAll());

        System.out.println();

        List options = Arrays.asList("Ajouter exemplaire", "Retirer exemplaire", "Rechercher exemplaire", "Modifier exemplaire", "Fin");
        do {
            int choix = choixListe(options);

            switch (choix) {
                case 1:
                    ajouterExemplaire();
                    break;
                case 2:
                    retirerExemplaire();
                    break;
                case 3:
                    rechercherExemplaire();
                    break;
                case 4:
                    modifierExemplaire();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    public void ajouterExemplaire() {
        Exemplaire ex;
        do {
            try {
                System.out.println("Matricule : ");
                String matricule = sc.nextLine();
                System.out.println("Description de l'état : ");
                String descriptionEtat = sc.nextLine();

                ex = new Exemplaire(matricule, descriptionEtat);
                break;
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        } while (true);

        controller.add(ex);
    }

    private void retirerExemplaire() {
        int choix = choixEltInt(list) - 1;

        Exemplaire e = list.get(choix);
        boolean ok = controller.remove(e);

        if (ok) affMsg("Exemplaire effacé");
        else affMsg("Exemplaire non effacé");
    }

    public void rechercherExemplaire() {
        try {
            System.out.println("Matricule : ");
            String matricule = sc.nextLine();

            Exemplaire rechExemplaire = new Exemplaire(matricule);

            Exemplaire ex = controller.search(rechExemplaire);
            if (ex == null) affMsg("Exemplaire inconnu");
            else {
                affMsg(ex.toString());
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

    }

    public void modifierExemplaire() {
        System.out.println("\n" + list);

        int choix = choixEltInt(list);
        Exemplaire ex = list.get(choix - 1);

        do {
            try {
                String matricule = modifyIfNotBlank("Matricule : ", ex.getMatricule());
                String descrptionEtat = modifyIfNotBlank("Description de l'état : ", ex.getDescriptionEtat());

                ex.setMatricule(matricule);
                ex.setDescriptionEtat(descrptionEtat);

                break;
            } catch (Exception e) {
                System.out.println("Erreur :" + e);
            }
        } while (true);

        controller.update(ex); /* Mise à jour de l'exemplaire choisi */
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void affList(List le) {
        affListe(le);
    }
}
