package biblio.mvcold.controller;

import biblio.metier.Exemplaire;
import biblio.metier.Ouvrage;
import biblio.mvcold.model.DAO;
import biblio.mvcold.model.DAOSpecialOuvrage;
import biblio.mvcold.view.AbstractView;

import java.util.List;

public class ControllerOuvrage extends Controller<Ouvrage> implements ControllerSpecialOuvrage {
    public ControllerOuvrage(DAO<Ouvrage> model, AbstractView<Ouvrage> view) {
        super(model, view);
    }

    @Override
    public List<Exemplaire> listerExemplaireOuvrage(Ouvrage o) {
        return ((DAOSpecialOuvrage) model).listerExemplaire(o);
    }

    @Override
    public List<Exemplaire> listerExemplaireOuvrageLocation(Ouvrage o, boolean enLocation) {
        return ((DAOSpecialOuvrage) model).listerExemplaire(o, enLocation);
    }

    @Override
    public double amendeRetard(Ouvrage o, int nj) {
        return ((DAOSpecialOuvrage) model).amendeRetard(o, nj);
    }
}
