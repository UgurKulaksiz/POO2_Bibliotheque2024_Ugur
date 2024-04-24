package biblio.mvcold.view;

import biblio.metier.Lecteur;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static biblio.utilitaires.Utilitaire.*;

public class LecteurViewConsole extends AbstractView<Lecteur> {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(controller.getAll());

        List options = Arrays.asList("Ajouter lecteur", "Retirer lecteur", "Rechercher lecteur", "Modifier lecteur", "Ajouter les lecteurs", "Fin");
        do {
            int choix = choixListe(options);

            switch (choix) {
                case 1:
                    ajouterLecteur();
                    break;
                case 2:
                    retirerLecteur();
                    break;
                case 3:
                    rechercherLecteur();
                    break;
                case 4:
                    modifierLecteur();
                    break;
                case 5:
                    ajouterLecteurFichier();
                    break;
                case 6:
                    return;
            }
        } while (true);
    }

    public void ajouterLecteur() {
        Lecteur l;
        do {
            try {
                System.out.println("Nom : ");
                String nom = sc.nextLine();
                System.out.println("Prénom : ");
                String prenom = sc.nextLine();
                System.out.println("Date de naissance (DD MM YYYY): ");
                LocalDate dateNaissance = lecDate();
                System.out.println("Adresse : ");
                String adresse = sc.nextLine();
                System.out.println("E-mail : ");
                String mail = sc.nextLine();
                System.out.println("Téléphone : ");
                String tel = sc.nextLine();

                l = new Lecteur(nom, prenom, dateNaissance, adresse, mail, tel);
                break;
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
            }
        } while (true);

        controller.add(l);
    }

    private void retirerLecteur() {
        int choix = choixEltInt(list) - 1;

        Lecteur l = list.get(choix);
        boolean ok = controller.remove(l);

        if (ok) affMsg("Lecteur effacé");
        else affMsg("Lecteur non effacé");
    }

    public void rechercherLecteur() {
        System.out.println("\n" + list);
        try {
            System.out.println("Numéro du lecteur : ");
            int numLec = lireInt();

            Lecteur rechLecteur = new Lecteur(numLec, "", "", null, "", "", "");

            Lecteur l = controller.search(rechLecteur);
            if (l == null) affMsg("Lecteur inconnu");
            else {
                affMsg(l.toString());
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

    }

    public void modifierLecteur() {
        System.out.println("\n" + list);

        int choix = choixEltInt(list);
        Lecteur l = list.get(choix - 1);

        do {
            try {
                String nom = modifyIfNotBlank("Nom : ", l.getNom());
                String prenom = modifyIfNotBlank("Prénom : ", l.getPrenom());
                LocalDate dn = LocalDate.parse(modifyIfNotBlank("Date de naissance (DD MM YYYY) : ", String.valueOf(l.getDn())));
                String adresse = modifyIfNotBlank("Adresse : ", l.getAdresse());
                String mail = modifyIfNotBlank("E-mail : ", l.getMail());
                String tel = modifyIfNotBlank("Téléphone : ", l.getTel());

                l.setNom(nom);
                l.setPrenom(prenom);

                // gérer autres valeurs
                //OK
                l.setDn(dn);
                l.setAdresse(adresse);
                l.setMail(mail);
                l.setTel(tel);

                break;
            } catch (Exception e) {
                System.out.println("Erreur :" + e);
            }
        } while (true);

        controller.update(l); /* Mise à jour du lecteur choisi */
    }

    public void ajouterLecteurFichier() {
        try {
            // Ouvrir le fichier "nouveaux_lecteurs.txt" en lecture
            Scanner fichier = new Scanner(new File("C:\\Ugur Kulaksiz Condorcet Mons Cours\\BAC 2 2023-2024 Ugur Kulaksiz\\Q2\\POO 2\\nouveaux_lecteurs.txt"));

            while (fichier.hasNext()) {
                // Lire chaque ligne du fichier
                String line = fichier.nextLine();

                // Séparer les informations du lecteur
                String[] infoLecteur = line.split(", ");

                // Créer un nouvel objet Lecteur
                Lecteur nouveauLecteur = new Lecteur(infoLecteur[0], infoLecteur[1], LocalDate.parse(infoLecteur[2]), infoLecteur[3], infoLecteur[4], infoLecteur[5]);

                // Ajouter le nouveau lecteur à la liste des lecteurs
                controller.add(nouveauLecteur);
            }

            // Fermer le fichier (Scanner)
            fichier.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier 'nouveaux_lecteurs.txt' n'a pas été trouvé.");
        }
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void affList(List ll) {
        affListe(ll);
    }
}
