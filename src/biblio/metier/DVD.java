package biblio.metier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class DVD extends Ouvrage {
    private long code;
    private LocalTime dureeTotale;
    private byte nbreBonus;

    private Set<String> autresLangues = new HashSet<>();
    private Set<String> sousTitres = new HashSet<>();

    public DVD() {
    }

    public DVD(String titre, int ageMin, LocalDate dateParution, TypeOuvrage to, double prixLocation, String langue, String genre, long code, LocalTime dureeTotale, byte nbreBonus) {
        super(titre, ageMin, dateParution, TypeOuvrage.DVD, prixLocation, langue, genre);
        this.code = code;
        this.dureeTotale = dureeTotale;
        this.nbreBonus = nbreBonus;
    }

    public DVD(String titre, int ageMin, LocalDate dateParution, double prixLocation, String langue, String genre, long code, LocalTime dureeTotale, byte nbreBonus) {
        super(titre, ageMin, dateParution, TypeOuvrage.DVD, prixLocation, langue, genre);
        this.code=code;
        this.dureeTotale=dureeTotale;
        this.nbreBonus=nbreBonus;
        this.autresLangues = new HashSet<>();
        this.sousTitres = new HashSet<>();
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public LocalTime getDureeTotale() {
        return dureeTotale;
    }

    public void setDureeTotale(LocalTime dureeTotale) {
        this.dureeTotale = dureeTotale;
    }

    public byte getNbreBonus() {
        return nbreBonus;
    }

    public void setNbreBonus(byte nbreBonus) {
        this.nbreBonus = nbreBonus;
    }

    public Set<String> getAutresLangues() {
        return autresLangues;
    }

    public void setAutresLangues(Set<String> autresLangues) {
        this.autresLangues = autresLangues;
    }

    public Set<String> getSousTitres() {
        return sousTitres;
    }

    public void setSousTitres(Set<String> sousTitres) {
        this.sousTitres = sousTitres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DVD dvd = (DVD) o;
        return code == dvd.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return super.toString()+"\nDVD{" +
                "code=" + code +
                ", dureeTotale=" + dureeTotale +
                ", nbreBonus=" + nbreBonus +
                ", autresLangues=" + autresLangues +
                ", sousTitres=" + sousTitres +
                "}";
    }

    /* METHODES */
    @Override
    public double amendeRetard(int njours) {
        // amendeRetard DVD
        return njours * 1.5;
    }

    @Override public int njoursLocMax(){
        return 3;
    }

}