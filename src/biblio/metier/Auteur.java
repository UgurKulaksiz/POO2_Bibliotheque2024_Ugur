package biblio.metier;

import java.util.*;

import static biblio.metier.TypeOuvrage.LIVRE;

public class Auteur {
    private String nom;
    private String prenom;
    private String nationalite;

    private Set<Ouvrage> listOuvrage = new HashSet<>();
    // remplacer par set

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

    public Set<Ouvrage> getListOuvrage() {
        return listOuvrage;
    }

    public void setListOuvrage(Set<Ouvrage> listOuvrage) {
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

    public Set<Ouvrage> listerOuvrages(){
        // lister ouvrages
        return listOuvrage;
    }

    public List<Ouvrage> listerOuvrages(TypeOuvrage to){
        // lister ouvrages d'un type
        List<Ouvrage> listOuvrageType = new ArrayList<>();

        /* Parcourir les ouvrages */
        for (Ouvrage o : listOuvrage){
            if (o.getTo().equals(to)) listOuvrageType.add(o);
        }

        return listOuvrageType;
    }
    public List<Livre> listerLivres(TypeLivre tl){
        // lister livres d'un type
        List<Livre> listLivreType = new ArrayList<>();

        for (Ouvrage o : listOuvrage){
            if(o.getTo().equals(LIVRE)) {
                Livre l = (Livre)o;
                if(l.getTl().equals(tl)) listLivreType.add(l);
            }
        }

        return listLivreType;
    }
    public List<Ouvrage> listerOuvrages(String genre){
        // lister ouvrages d'un genre
        List<Ouvrage> listOuvrageGenre = new ArrayList<>();

        for (Ouvrage o : listOuvrage){
            if (o.getGenre().equals(genre)) listOuvrageGenre.add(o);
        }

        return listOuvrageGenre;
    }

}
