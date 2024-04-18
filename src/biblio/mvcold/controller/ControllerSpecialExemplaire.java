package biblio.mvcold.controller;

import biblio.metier.Exemplaire;
import biblio.metier.Lecteur;
import biblio.metier.Mail;

public interface ControllerSpecialExemplaire {
    void modifierEtat(Exemplaire ex, String etat);

    Lecteur LecteurActuel(Exemplaire ex);

    void envoiMailLecteurActuel(Exemplaire ex, Mail m);

    boolean enLocation(Exemplaire ex);
}
