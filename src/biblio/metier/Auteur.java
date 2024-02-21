package biblio.metier;

import java.util.ArrayList;
import java.util.List;

public class Auteur {
    private String nom;
    private String prenom;
    private String nationalite;

    private List<biblio.metier.Ouvrage> listOuvrage = new ArrayList<>();

    public Auteur() {
    }

    public Auteur(String nom, String prenom, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
    }

    public Auteur(String nom, String prenom, String nationalite, List<biblio.metier.Ouvrage> listOuvrage) {
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
        this.listOuvrage = listOuvrage;
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

    public List<biblio.metier.Ouvrage> getListOuvrage() {
        return listOuvrage;
    }

    public void setListOuvrage(List<Ouvrage> listOuvrage) {
        this.listOuvrage = listOuvrage;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", \nlistOuvrage=" + listOuvrage +
                '}';
    }

    /* METHODES */

}
