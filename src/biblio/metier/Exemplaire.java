package biblio.metier;

import java.util.*;
import static biblio.gestion.GestionOld.LOCATIONS;

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
        /* Enoncé V2 : c)Modifiez les méthodes suivantes afin qu'elles exploitent la hashMap ci-dessus -> public Lecteur lecteurActuel() */
        if (enLocation())
            return LOCATIONS.get(this); /* Retourne le lecteur (loueur) actuel de la location (de l'exemplaire) */

        return null;

    }

    public void envoiMailLecteurActuelExemplaire(Mail mail) {
        // envoi mail lecteur exemplaire
        if (lecteurActuelExemplaire() != null)
            System.out.println("Envoi de " + mail + " à " + lecteurActuelExemplaire().getMail());
        else
            System.out.println("Aucune location en cours");

    }


    public boolean enLocation() {
        // en location exemplaires
        /* Enoncé V2 : c)Modifiez les méthodes suivantes afin qu'elles exploitent la hashMap ci-dessus -> public boolean enLocation() */
        /*  Lors de la location ajouter une entrée dans cette hashMap et lors de la restitution supprimez cette entrée. */

        return LOCATIONS.get(this) != null;
    }

}
