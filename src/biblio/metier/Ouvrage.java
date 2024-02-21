package biblio.metier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Ouvrage {
    protected String titre;
    protected int ageMin;
    protected LocalDate dateParution;
    protected biblio.metier.TypeOuvrage to;
    protected double prixLocation;
    protected String langue;
    protected String genre;


    protected List<biblio.metier.Auteur> listAuteur = new ArrayList<>();

    protected List<biblio.metier.Exemplaire> listExemplaire = new ArrayList<>();

    public Ouvrage() {
    }

    public Ouvrage(String titre, int ageMin, LocalDate dateParution, biblio.metier.TypeOuvrage to, double prixLocation, String langue, String genre) {
        this.titre = titre;
        this.ageMin = ageMin;
        this.dateParution = dateParution;
        this.to = to;
        this.prixLocation = prixLocation;
        this.langue = langue;
        this.genre = genre;
    }

    public Ouvrage(String titre, int ageMin, LocalDate dateParution, biblio.metier.TypeOuvrage to, double prixLocation, String langue, String genre, List<biblio.metier.Auteur> listAuteur, List<biblio.metier.Exemplaire> listExemplaire) {
        this.titre = titre;
        this.ageMin = ageMin;
        this.dateParution = dateParution;
        this.to = to;
        this.prixLocation = prixLocation;
        this.langue = langue;
        this.genre = genre;
        this.listAuteur = listAuteur;
        this.listExemplaire = listExemplaire;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public LocalDate getDateParution() {
        return dateParution;
    }

    public void setDateParution(LocalDate dateParution) {
        this.dateParution = dateParution;
    }

    public biblio.metier.TypeOuvrage getTo() {
        return to;
    }

    public void setTo(TypeOuvrage to) {
        this.to = to;
    }

    public double getPrixLocation() {
        return prixLocation;
    }

    public void setPrixLocation(double prixLocation) {
        this.prixLocation = prixLocation;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<biblio.metier.Auteur> getListAuteur() {
        return listAuteur;
    }

    public void setListAuteur(List<Auteur> listAuteur) {
        this.listAuteur = listAuteur;
    }

    public List<biblio.metier.Exemplaire> getListExemplaire() {
        return listExemplaire;
    }

    public void setListExemplaire(List<Exemplaire> listExemplaire) {
        this.listExemplaire = listExemplaire;
    }

    @Override
    public String toString() {
        return "Ouvrage{" +
                "titre='" + titre + '\'' +
                ", ageMin=" + ageMin +
                ", dateParution=" + dateParution +
                ", to=" + to +
                ", prixLocation=" + prixLocation +
                ", langue='" + langue + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    /* METHODES */

}
