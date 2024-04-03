package biblio.metier;

import java.util.*;

public class Rayon {
    private String codeRayon;
    private String genre;
    private Set<Exemplaire> listExemplaire = new HashSet<>();
    // remplacer par set

    public Rayon() {
    }

    public Rayon(String codeRayon, String genre) {
        this.codeRayon = codeRayon;
        this.genre = genre;
    }

    public String getCodeRayon() {
        return codeRayon;
    }

    public void setCodeRayon(String codeRayon) {
        this.codeRayon = codeRayon;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Exemplaire> getListExemplaire() {
        return listExemplaire;
    }

    public void setListExemplaire(Set<Exemplaire> listExemplaire) {
        this.listExemplaire = listExemplaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rayon rayon = (Rayon) o;
        return Objects.equals(codeRayon, rayon.codeRayon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeRayon);
    }

    @Override
    public String toString() {
        return "Rayon{" +
                "codeRayon='" + codeRayon + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    /* METHODES */
    public void addExemplaire(Exemplaire e){
        /* Ajout d'un exemplaire au rayon */
        listExemplaire.add(e);
        e.setRayon(this);
    }

    public void remove(Exemplaire e){
        listExemplaire.remove(e);
        e.setRayon(null);
    }

}
