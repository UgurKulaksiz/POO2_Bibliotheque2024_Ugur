package biblio.metier;

import java.time.LocalDate;

public class CD extends Ouvrage {
    private long code;
    private byte nbrePlages;
    private String dureeTotale;


    public CD() {
    }

    public CD(long code, byte nbrePlages, String dureeTotale) {
        this.code = code;
        this.nbrePlages = nbrePlages;
        this.dureeTotale = dureeTotale;
    }

    public CD(String titre, byte ageMin, LocalDate dateParution, TypeOuvrage to, double prixLocation, String langue, String genre, long code, byte nbrePlages, String dureeTotale) {
        super(titre, ageMin, dateParution, to, prixLocation, langue, genre);
        this.code = code;
        this.nbrePlages = nbrePlages;
        this.dureeTotale = dureeTotale;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public byte getNbrePlages() {
        return nbrePlages;
    }

    public void setNbrePlages(byte nbrePlages) {
        this.nbrePlages = nbrePlages;
    }

    public String getDureeTotale() {
        return dureeTotale;
    }

    public void setDureeTotale(String dureeTotale) {
        this.dureeTotale = dureeTotale;
    }

    @Override
    public String toString() {
        return super.toString()+"CD{" +
                "code=" + code +
                ", nbrePlages=" + nbrePlages +
                ", dureeTotale='" + dureeTotale + '\'' +
                "} \n" + super.toString();
    }
}
