package biblio.metier;

import java.time.LocalDate;
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
        List<Exemplaire> listExemplaireModifier = new ArrayList<>();

        for (Exemplaire e : listExemplaireModifier){
            e.setDescriptionEtat(etat);
        }
    }

    public Lecteur lecteurActuelExemplaire(){
        //TODO lecteur actuel exemplaire
        if (enLocation()){
            for (Location l : listLocation){
                if (l.getDateRestitution() == null) return l.getLoueur(); /* Retourne le lecteur (loueur) de la location */
            }
        }

        return null;
    }
    public List<Lecteur> lecteursExemplaire(){
        //TODO lecteurs exemplaire
        List<Lecteur> listLecteur = new ArrayList<>();

        for (Location l : listLocation){
            if (l.getDateRestitution() == null) listLecteur.add(l.getLoueur());
        }

        return listLecteur;
    }

    public void envoiMailLecteurActuelExemplaire(Mail mail){
        //TODO envoi mail lecteur exemplaire
    }
    public void envoiMailLecteursExemplaire(Mail mail){
        //TODO envoi mail lecteurs exemplaire
    }

    public boolean enRetard(){
        //TODO enretard exemplaire
        if (enLocation()){
            for (Location l : listLocation){
                if (l.getDateRestitution() != null && l.getDateRestitution().isBefore(LocalDate.now()))
                        return true; /* Si la date de restitution n'est pas null et qu'il est inférieur à la date actuelle, l'exemplaire est en retard */
            }
        }

        return false;
    }

    public int joursRetard(){
        //TODO jours retard exemplaire
        return 0;
    }


    public boolean enLocation(){
        //TODO en location exemplaires
        for (Location l : listLocation){
            if (l.getDateRestitution() == null) return true;  // Si la date de restitution est null --> l'exemplaire est actuellement en location
        }

        return false;
    }

}
