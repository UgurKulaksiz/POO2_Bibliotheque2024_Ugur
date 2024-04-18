package biblio.mvcold.model;

import biblio.metier.Exemplaire;
import biblio.metier.Lecteur;
import biblio.metier.Mail;

public interface DAOSpecialExemplaire {
    public void modifierEtat(Exemplaire ex, String etat);

    public Lecteur lecteurActuel(Exemplaire ex);

    public void envoiMailLecteurActuel(Exemplaire ex, Mail m);

    public boolean enLocation(Exemplaire ex);
}
