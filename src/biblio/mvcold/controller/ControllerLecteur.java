package biblio.mvcold.controller;

import biblio.metier.Lecteur;
import biblio.mvcold.model.DAO;
import biblio.mvcold.view.AbstractView;

public class ControllerLecteur extends Controller<Lecteur> {
    public ControllerLecteur(DAO<Lecteur> model, AbstractView<Lecteur> view) {
        super(model, view);
    }
}
