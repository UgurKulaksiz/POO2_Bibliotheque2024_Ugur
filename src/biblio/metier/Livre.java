package biblio.metier;

import java.time.LocalDate;

public class Livre extends Ouvrage {
    private String isbn;
    private int nbrePages;
    private biblio.metier.TypeLivre tl;
    private String resume;


    public Livre() {
    }

    public Livre(String isbn, int nbrePages, biblio.metier.TypeLivre tl, String resume) {
        this.isbn = isbn;
        this.nbrePages = nbrePages;
        this.tl = tl;
        this.resume = resume;
    }

    public Livre(String titre, int ageMin, LocalDate dateParution, TypeOuvrage to, double prixLocation, String langue, String genre, String isbn, int nbrePages, biblio.metier.TypeLivre tl, String resume) {
        super(titre, ageMin, dateParution, to, prixLocation, langue, genre);
        this.isbn = isbn;
        this.nbrePages = nbrePages;
        this.tl = tl;
        this.resume = resume;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNbrePages() {
        return nbrePages;
    }

    public void setNbrePages(int nbrePages) {
        this.nbrePages = nbrePages;
    }

    public biblio.metier.TypeLivre getTl() {
        return tl;
    }

    public void setTl(TypeLivre tl) {
        this.tl = tl;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return super.toString()+ "Livre{" +
                "isbn='" + isbn + '\'' +
                ", nbrePages=" + nbrePages +
                ", tl=" + tl +
                ", resume='" + resume + '\'' +
                "} \n" + super.toString();
    }

    /* METHODES */

}
