package biblio.mvcold.controller;

import biblio.metier.Exemplaire;
import biblio.metier.Rayon;
import biblio.mvcold.model.DAO;
import biblio.mvcold.model.DAOSpecialRayon;
import biblio.mvcold.view.AbstractView;

import java.util.List;

public class ControllerRayon extends Controller<Rayon> implements ControllerSpecialRayon {
    public ControllerRayon(DAO<Rayon> model, AbstractView<Rayon> view) {
        super(model, view);
    }

    public List<Exemplaire> listerExemplaires(Rayon r) {
        return ((DAOSpecialRayon) model).listerExemplaires(r);
    }
}
