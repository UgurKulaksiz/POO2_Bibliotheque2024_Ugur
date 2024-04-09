package biblio.metier;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Exemplaire {
    private String matricule;
    private String descriptionEtat;

    private Ouvrage ouvrage;
    private Rayon rayon;

    /* Enoncé V2
    1°)Remplacez la liste des locations par une hashmap dont la clé est l'exemplaire loué et la valeur le lecteur-loueur,
    cette hashmap sera de type public static final afin d'être accessible par toutes les autres classes de l'application.
    Lors de la location ajouter une entrée dans cette hashMap et lors de la restitution supprimez cette entrée.
     */
    public static final Map<Exemplaire, Lecteur> listLocation = new HashMap<>();

    public Exemplaire() {
    }

    public Exemplaire(String matricule, String descriptionEtat, Ouvrage ouvrage) {
        this.matricule = matricule;
        this.descriptionEtat = descriptionEtat;
        this.ouvrage = ouvrage;
        this.ouvrage.getListExemplaire().add(this);
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getDescriptionEtat() {
        return descriptionEtat;
    }

    public void setDescriptionEtat(String descriptionEtat) {
        this.descriptionEtat = descriptionEtat;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        if (this.ouvrage != null) this.ouvrage.getListExemplaire().remove(this);
        this.ouvrage = ouvrage;
        this.ouvrage.getListExemplaire().add(this);
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        if (this.rayon != null) this.rayon.getListExemplaire().remove(this);
        this.rayon = rayon;
        this.rayon.getListExemplaire().add(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exemplaire that = (Exemplaire) o;
        return Objects.equals(matricule, that.matricule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule);
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "matricule='" + matricule + '\'' +
                ", descriptionEtat='" + descriptionEtat + '\'' +
                ", \nouvrage=" + ouvrage +
                ", rayon=" + rayon +
                '}';
    }

    /* METHODES */
    public void modifierEtatExemplaire(String etat) {
        // modifier etat exemplaire
        setDescriptionEtat(etat);
    }

    public Lecteur lecteurActuelExemplaire() {
        // lecteur actuel exemplaire
        /* c)Modifiez les méthodes suivantes afin qu'elles exploitent la hashMap ci-dessus -> public Lecteur lecteurActuel() */
        return listLocation.get(this);

        /*
        if (enLocation())
            return listLocation.get(this); /* Retourne le lecteur (loueur) actuel de la location (de l'exemplaire)

        return null;
         */

    }

    public List<Lecteur> lecteurs() {
        List<Lecteur> listL = new ArrayList<>(listLocation.values());

        return listL;
        /*
        List<Lecteur> listL = new ArrayList<>();

        for (Location l : listLocation) {
            if (listL.contains(l)) continue; //par la suite utiliser set
            listL.add(l.getLoueur());
        }

        return null;
         */
    }

    public void envoiMailLecteurActuelExemplaire(Mail mail) {
        // envoi mail lecteur exemplaire
        if (lecteurActuelExemplaire() != null)
            System.out.println("Envoi de " + mail + " à " + lecteurActuelExemplaire().getMail());
        else
            System.out.println("Aucune location en cours");

    }

    public void envoiMailLecteursExemplaire(Mail mail) {
        // envoi mail lecteurs exemplaire
        List<Lecteur> listL = lecteurs();
        if (listL.isEmpty()) {
            System.out.println("Aucun lecteur enregistré");
        } else {
            for (Lecteur l : listL) {
                System.out.println("Envoi de " + mail + " à " + l.getMail());
            }
        }
    }

    /*
    public boolean enRetard() {
        // en retard exemplaire
        if (listLocation.isEmpty()) return false;

        Location l = listLocation.get(listLocation.size() - 1); /* Récupèrer la dernière location en cours */
    /*
        if (l.getDateRestitution() == null && l.getDateLocation().plusDays(ouvrage.njoursLocMax()).isAfter(LocalDate.now()))
            return true;

        return false;
    }

    public int joursRetard() {
        // jours retard exemplaire
        if (!enRetard()) return 0;

        Location l = listLocation.get(listLocation.size() - 1); /* La location en cours est la dernière de la liste */
    /*
        LocalDate dateLimite = l.getDateLocation().plusDays(ouvrage.njoursLocMax());

        int njoursRetard = (int) ChronoUnit.DAYS.between(dateLimite, LocalDate.now());

        return njoursRetard;
    }
    */


    public boolean enLocation() {
        // en location exemplaires
        /* c)Modifiez les méthodes suivantes afin qu'elles exploitent la hashMap ci-dessus -> public boolean enLocation() */
        return listLocation.containsKey(this);

        /*
        if (listLocation.isEmpty()) return false;

        Location l = listLocation.get(listLocation.size() - 1);
        if (l.getDateRestitution() == null)
            return true; // Si la date de restitution est null --> l'exemplaire est actuellement en location

        return false;

         */
    }

    /*  Lors de la location ajouter une entrée dans cette hashMap et lors de la restitution supprimez cette entrée. */
    public void ajouterLocation(Lecteur lecteur) {
        listLocation.put(this, lecteur);
    }

    public void supprimerLocation() {
        listLocation.remove(this);
    }

}
