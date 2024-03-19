package biblio.metier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lecteur {
    private static int numact=1;
    private int numlecteur;
    private  String nom,prenom;
    private LocalDate dn;
    private String adresse;
    private String mail;
    private String tel;

    private List<Location> listLocation = new ArrayList<>();

    public Lecteur() {
    }

    public Lecteur(String nom, String prenom, LocalDate dn, String adresse, String mail, String tel) {
        this.numlecteur = numact++; /* Incrémentation de numlecteur à partir de 1 */
        this.nom = nom;
        this.prenom = prenom;
        this.dn = dn;
        this.adresse = adresse;
        this.mail = mail;
        this.tel = tel;
    }

    public int getNumlecteur() {
        return numlecteur;
    }

    public void setNumlecteur(int numlecteur) {
        this.numlecteur = numlecteur;
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

    public LocalDate getDn() {
        return dn;
    }

    public void setDn(LocalDate dn) {
        this.dn = dn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Location> getListLocation() {
        return listLocation;
    }

    public void setLloc(List<Location> listLocation) {
        this.listLocation = listLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecteur lecteur = (Lecteur) o;
        return numlecteur == lecteur.numlecteur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numlecteur);
    }

    @Override
    public String toString() {
        return "Lecteur{" +
                "numlecteur=" + numlecteur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dn=" + dn +
                ", adresse='" + adresse + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    /* METHODES */
    public List<Exemplaire> listerExemplairesEnLocationLecteur(){
        //TODO lister exemplaires en location lecteur
        List<Exemplaire> listExemplaireLocation = new ArrayList<>();

        if (listLocation != null){
            for (Location l : listLocation){
                if (l.getDateRestitution() == null){
                    listExemplaireLocation.add(l.getExemplaire());
                }
            }
        }

        return listExemplaireLocation;
    }

    public List<Exemplaire> listerExemplairesLouesLecteur(){
        //TODO lister exemplaires loues lecteur
        List<Exemplaire> listExemplaireLoues = new ArrayList<>();

        for (Location l : listLocation){
            if (l != null){
                listExemplaireLoues.add(l.getExemplaire());
            }
        }

        return listExemplaireLoues;
    }

}
