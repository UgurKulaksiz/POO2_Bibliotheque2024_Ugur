package biblio.metier;

import java.time.LocalDate;
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
        //TODO calcul amende location sur base dote restitution : la durée du prêt est de 15 jours pour les livres, 3 jours pour les DVD et 7 jours pour les CD
        double amende = 0.0;

        int joursMaxLivres = 15;
        int joursMaxDVD = 3;
        int joursMaxCD = 7;

        for (Location l : exemplaire.getListLocation()) {
            if (l.getDateRestitution() == null) {
                LocalDate dateLocation = l.getDateLocation();
                LocalDate dateActuelle = LocalDate.now();

                int joursRetard = dateLocation.until(dateActuelle).getDays();

                int joursMaxRetard = 0;

                TypeOuvrage to = l.getExemplaire().getOuvrage().getTo();
                switch (to) {
                    case LIVRE:
                        joursMaxRetard = joursMaxLivres;
                        break;

                    case DVD:
                        joursMaxRetard = joursMaxDVD;
                        break;

                    case CD:
                        joursMaxRetard = joursMaxCD;
                        break;
                }

                if (joursRetard > joursMaxRetard){
                    amende += (joursRetard - joursMaxRetard) * 0.5;
                }
            }
        }


        return amende;
    }

    public void enregistrerRetour() {
        //TODO enregistrer retour => la date de restitution devient égale à la date actuelle
        List<Location> listLocationRetour = new ArrayList<>();

        for (Location l : exemplaire.getListLocation()){
            if (l.getDateRestitution() != null && l.getDateRestitution().isEqual(LocalDate.now())){
                l.setDateRestitution(LocalDate.now()); /* MàJ de la date de restitution */
                listLocationRetour.add(l);
            }
        }
    }

}
