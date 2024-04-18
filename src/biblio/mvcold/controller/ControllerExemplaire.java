package biblio.mvcold.controller;

import biblio.metier.Exemplaire;
import biblio.metier.Lecteur;
import biblio.metier.Mail;
import biblio.mvcold.model.DAO;
import biblio.mvcold.model.DAOSpecialExemplaire;
import biblio.mvcold.view.AbstractView;

import java.util.List;

public class ControllerExemplaire extends Controller<Exemplaire> implements ControllerSpecialExemplaire {
    public ControllerExemplaire(DAO<Exemplaire> model, AbstractView<Exemplaire> view) {
        super(model, view);
    }

    @Override
    public void modifierEtat(Exemplaire ex, String etat) {
        ((DAOSpecialExemplaire) model).modifierEtat(ex, etat);
    }


    @Override
    public Lecteur LecteurActuel(Exemplaire ex) {
        return ((DAOSpecialExemplaire) model).lecteurActuel(ex);

    }


    @Override
    public void envoiMailLecteurActuel(Exemplaire ex, Mail m) {
        ((DAOSpecialExemplaire) model).envoiMailLecteurActuel(ex, m);
    }


    @Override
    public boolean enLocation(Exemplaire ex) {
        return ((DAOSpecialExemplaire) model).enLocation(ex);

    }
}
