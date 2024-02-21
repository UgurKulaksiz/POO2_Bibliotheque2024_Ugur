package biblio.metier;

import java.time.LocalDate;

public class Location {
    private LocalDate dateLocation;
    private LocalDate dateRestitution;
    private biblio.metier.Lecteur loueur;
    private biblio.metier.Exemplaire exemplaire;

    public Location(LocalDate dateLocation, LocalDate dateRestitution, biblio.metier.Lecteur loueur, biblio.metier.Exemplaire exemplaire) {
        this.dateLocation = dateLocation;
        this.dateRestitution = dateRestitution;
        this.loueur = loueur;
        this.exemplaire = exemplaire;
    }

    public Location(biblio.metier.Lecteur loueur, biblio.metier.Exemplaire exemplaire) {
        this.loueur = loueur;
        this.exemplaire = exemplaire;
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

    public biblio.metier.Lecteur getLoueur() {
        return loueur;
    }

    public void setLoueur(Lecteur loueur) {
        this.loueur = loueur;
    }

    public biblio.metier.Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
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

}
