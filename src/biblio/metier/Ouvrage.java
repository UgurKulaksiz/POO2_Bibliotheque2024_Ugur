package biblio.metier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Ouvrage {
    protected String titre;
    protected int ageMin;
    protected LocalDate dateParution;
    protected TypeOuvrage to;
    protected double prixLocation;
    protected String langue;
    protected String genre;


    protected Set<Auteur> listAuteur = new HashSet<>();
    // remplacer par set

    protected Set<Exemplaire> listExemplaire = new HashSet<>();
    // remplacer par set

    public Ouvrage() {
    }

    public Ouvrage(String titre, int ageMin, LocalDate dateParution, TypeOuvrage to, double prixLocation, String langue, String genre) {
        this.titre = titre;
        this.ageMin = ageMin;
        this.dateParution = dateParution;
        this.to = to;
        this.prixLocation = prixLocation;
        this.langue = langue;
        this.genre = genre;
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

    public TypeOuvrage getTo() {
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

    public Set<Auteur> getListAuteur() {
        return listAuteur;
    }

    public void setListAuteur(Set<Auteur> listAuteur) {
        this.listAuteur = listAuteur;
    }

    public Set<Exemplaire> getListExemplaire() {
        return listExemplaire;
    }

    public void setListExemplaire(Set<Exemplaire> listExemplaire) {
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
    public void addAuteur(Auteur a ){
        listAuteur.add(a);
        a.getListOuvrage().add(this);
    }

    public void removeAuteur(Auteur a){
        listAuteur.remove(a);
        a.getListOuvrage().remove(this);
    }
    public void addExemplaire(Exemplaire e){
        listExemplaire.add(e);
        e.setOuvrage(this);
    }

    public void removeExemplaire(Exemplaire e){
        listExemplaire.remove(e);
        e.setOuvrage(null);
    }
    public Set<Exemplaire>listerExemplairesOuvrage(){
        // lister exemplaires ouvrage
        return listExemplaire;
    }

    public List<Exemplaire>listerExemplairesLocation(boolean enLocation){
        // lister exemplaires ouvrage en location
        List<Exemplaire> listExemplairesLocation = new ArrayList<>();
        for (Exemplaire e : listExemplaire){
            if (e.enLocation() == enLocation) listExemplairesLocation.add(e);
        }

        return listExemplairesLocation;
    }

    public abstract double amendeRetard(int njours);

    public abstract int njoursLocMax();
}
