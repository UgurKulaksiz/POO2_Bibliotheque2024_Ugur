package biblio.metier;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Location {
    private LocalDate dateLocation;
    private LocalDate dateRestitution;
    private Lecteur loueur;
    private Exemplaire exemplaire;

    public Location(LocalDate dateLocation, LocalDate dateRestitution, Lecteur loueur, Exemplaire exemplaire) {
        this.dateLocation = dateLocation;
        this.dateRestitution = dateRestitution;
        this.loueur = loueur;
        this.exemplaire = exemplaire;
        this.loueur.getListLocation().add(this);
        this.exemplaire.getListLocation().add(this);
    }

    public Location(Lecteur loueur, Exemplaire exemplaire) {
        this.loueur = loueur;
        this.exemplaire = exemplaire;
        this.dateLocation = LocalDate.now();
    }

    public LocalDate getDateLocation() {
        return dateLocation;
    }

    public void setDateLocation(LocalDate dateLocation) {
        this.dateLocation = dateLocation;
    }

    public LocalDate getDateRestitution() {
        return dateRestitution;
    }

    public void setDateRestitution(LocalDate dateRestitution) {
        this.dateRestitution = dateRestitution;
    }

    public Lecteur getLoueur() {
        return loueur;
    }

    public void setLoueur(Lecteur loueur) {
        this.loueur = loueur;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(dateLocation, location.dateLocation) && Objects.equals(loueur, location.loueur) && Objects.equals(exemplaire, location.exemplaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateLocation, loueur, exemplaire);
    }

    @Override
    public String toString() {
        return "Location{" +
                "dateLocation=" + dateLocation +
                ", dateRestitution=" + dateRestitution +
                ", \nloueur=" + loueur +
                ", \nexemplaire=" + exemplaire +
                '}';
    }

    /* METHODES */
    public double calculerAmende() {
        // calcul amende location sur base dote restitution : la durée du prêt est de 15 jours pour les livres, 3 jours pour les DVD et 7 jours pour les CD
        if (dateRestitution != null) {
            LocalDate dateLimite = dateLocation.plusDays(exemplaire.getOuvrage().njoursLocMax());

            if (dateRestitution.isAfter(dateLimite)) {
                int njretard = (int) ChronoUnit.DAYS.between(dateLimite, dateRestitution);

                return exemplaire.getOuvrage().amendeRetard(njretard);
            }
        }
        return 0;
    }

    public void enregistrerRetour() {
        //TODO enregistrer retour => la date de restitution devient égale à la date actuelle
        List<Location> listLocationRetour = new ArrayList<>();

        for (Location l : exemplaire.getListLocation()) {
            if (l.getDateRestitution() != null && l.getDateRestitution().isEqual(LocalDate.now())) {
                l.setDateRestitution(LocalDate.now()); /* MàJ de la date de restitution */
                listLocationRetour.add(l);
            }
        }
    }

}
