package biblio.metier;

import java.util.ArrayList;
import java.util.List;

public class Exemplaire {
    private String matricule;
    private String descriptionEtat;

    private biblio.metier.Ouvrage ouvrage;
    private biblio.metier.Rayon rayon;

    private List<biblio.metier.Location> listLocation = new ArrayList<>();

    public Exemplaire() {
    }

    public Exemplaire(String matricule, String descriptionEtat) {
        this.matricule = matricule;
        this.descriptionEtat = descriptionEtat;
    }

    public Exemplaire(String matricule, String descriptionEtat, biblio.metier.Ouvrage ouvrage) {
        this.matricule = matricule;
        this.descriptionEtat=descriptionEtat;
        this.ouvrage = ouvrage;
        this.ouvrage.getListExemplaire().add(this);
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getDescriptionEtat() {
        return descriptionEtat;
    }

    public void setDescriptionEtat(String descriptionEtat) {
        this.descriptionEtat = descriptionEtat;
    }

    public biblio.metier.Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public biblio.metier.Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }

    public List<biblio.metier.Location> getListLocation() {
        return listLocation;
    }

    public void setListLocation(List<Location> listLocation) {
        this.listLocation = listLocation;
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "matricule='" + matricule + '\'' +
                ", descriptionEtat='" + descriptionEtat + '\'' +
                ", \nouvrage=" + ouvrage +
                ", rayon=" + rayon +
                '}';
    }

    /* METHODES */


}
