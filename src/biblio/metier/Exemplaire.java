package biblio.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exemplaire {
    private String matricule;
    private String descriptionEtat;

    private Ouvrage ouvrage;
    private Rayon rayon;

    private List<Location> listLocation = new ArrayList<>();

    public Exemplaire() {
    }

    public Exemplaire(String matricule, String descriptionEtat, Ouvrage ouvrage) {
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

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
    }

    public Rayon getRayon() {
        return rayon;
    }

    public void setRayon(Rayon rayon) {
        if(this.rayon!=null) this.rayon.getListExemplaire().remove(this);
        this.rayon=rayon;
        this.rayon.getListExemplaire().add(this);
    }

    public List<Location> getListLocation() {
        return listLocation;
    }

    public void setListLocation(List<Location> listLocation) {
        this.listLocation = listLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exemplaire that = (Exemplaire) o;
        return Objects.equals(matricule, that.matricule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule);
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
    public void modifierEtatExemplaire(String etat){
        //TODO modifier etat exemplaire
    }

    public Lecteur lecteurActuelExemplaire(){
        //TODO lecteur actuel exemplaire
        return null;
    }
    public List<Lecteur> lecteursExemplaire(){
        //lecteurs exemplaire
        return null;
    }

    public void envoiMailLecteurActuelExemplaire(Mail mail){
        //TODO envoi mail lecteur exemplaire
    }
    public void envoiMailLecteursExemplaire(Mail mail){
        //TODO envoi mail lecteurs exemplaire
    }

    public boolean enRetard(){
        //TODO enretard exeplaire
        return false;
    }

    public int joursRetard(){
        //TODO jours retard exemplaire
        return 0;
    }


    public boolean enLocation(){
        //TODO en location exemplaires
        return false;
    }

}
