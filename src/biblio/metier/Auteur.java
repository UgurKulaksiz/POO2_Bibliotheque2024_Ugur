package biblio.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Auteur {
    private String nom;
    private String prenom;
    private String nationalite;

    private List<Ouvrage> listOuvrage = new ArrayList<>();

    public Auteur() {
    }

    public Auteur(String nom, String prenom, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public List<Ouvrage> getListOuvrage() {
        return listOuvrage;
    }

    public void setListOuvrage(List<Ouvrage> listOuvrage) {
        this.listOuvrage = listOuvrage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auteur auteur = (Auteur) o;
        return Objects.equals(nom, auteur.nom) && Objects.equals(prenom, auteur.prenom) && Objects.equals(nationalite, auteur.nationalite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, nationalite);
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nationalite='" + nationalite + '\'' +
                '}';
    }

    /* METHODES */
    public void addOuvrage(Ouvrage o ){
        listOuvrage.add(o);
        o.getListAuteur().add(this);
    }

    public void removeOuvrage(Ouvrage o){
        listOuvrage.remove(o);
        o.getListAuteur().remove(this);
    }

    public List<Ouvrage> listerOuvrages(){
        //TODO lister ouvrages
        return listOuvrage;
    }

    public List<Ouvrage> listerOuvragesType(TypeOuvrage to){
        //TODO lister ouvrages d'un type
        return null;
    }
    public List<Livre> listerLivres(TypeLivre tl){
        //TODO lister livres d'un type
        return null;
    }
    public List<Ouvrage> listerOuvragesGenre(String genre){
        //TODO lister ouvrages d'un genre
        return null;
    }

}
