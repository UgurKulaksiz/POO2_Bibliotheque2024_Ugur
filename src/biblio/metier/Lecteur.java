package biblio.metier;

import java.time.LocalDate;
import java.util.*;

public class Lecteur {
    private static int numact = 1;
    private int numlecteur;
    private String nom, prenom;
    private LocalDate dn;
    private String adresse;
    private String mail;
    private String tel;

    /* Enoncé V2 :
        d)Ôtez de la classe Lecteur la liste de ses locations ainsi que le méthodes suivantes : public List<Location> getLloc() {
            public void setLloc(List<Location> lloc) {
            public List<Exemplaire> listerExemplairesEnLocation(){
            public List<Exemplaire> listerExemplairesLoues(){
     */
    /* private List<Location> listLocation = new ArrayList<>(); */

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

    /*
    public List<Location> getListLocation() {
        return listLocation;
    }

    public void setLloc(List<Location> listLocation) {
        this.listLocation = listLocation;
    }
     */

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
    /*
    public List<Exemplaire> listerExemplairesEnLocationLecteur() {
        // lister exemplaires en location lecteur
        List<Exemplaire> listExemplaireLocation = new ArrayList<>();

        if (listLocation != null) {
            for (Location l : listLocation) {
                if (l.getDateRestitution() == null) {
                    listExemplaireLocation.add(l.getExemplaire());
                }
            }
        }

        return listExemplaireLocation;
    }

    public List<Exemplaire> listerExemplairesLouesLecteur() {
        // lister exemplaires loues lecteur
        List<Exemplaire> listExemplaireLoues = new ArrayList<>();

        for (Location l : listLocation) {
            listExemplaireLoues.add(l.getExemplaire());
            //TODO empêcher doublon si exemplaire loué plusieurs fois par même lecteur
            Set<Exemplaire> listSetExemplaire = new HashSet<>();
            if (!listSetExemplaire.contains(l.getExemplaire())){ /* Vérification si l'exemplaire est déjà à la liste
                listExemplaireLoues.add(l.getExemplaire());
                listSetExemplaire.add(l.getExemplaire()); /* Ajout de l'exemplaire à la liste pour éviter les doublons
            }
        }

        return listExemplaireLoues;
    }
    */

}
