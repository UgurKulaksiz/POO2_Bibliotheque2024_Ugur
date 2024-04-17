package biblio.mvc.controller;

import biblio.metier.Auteur;
import biblio.metier.Livre;
import biblio.metier.Ouvrage;
import biblio.metier.TypeLivre;
import biblio.mvc.model.DAO;
import biblio.mvc.model.DAOSpecialAuteur;
import biblio.mvc.view.AbstractView;

import java.util.List;
import java.util.Set;

public class ControllerAuteur extends Controller<Auteur> {
    public ControllerAuteur(DAO<Auteur> model, AbstractView<Auteur> view) {
        super(model, view);
    }

    public Set<Ouvrage> listerOuvrages(Auteur a) {

        return ((DAOSpecialAuteur) model).listerOuvrages(a);
    }

    public List<Livre> listerLivre(Auteur a, TypeLivre tl) {

        return ((DAOSpecialAuteur) model).listerLivre(a, tl);
    }

    public List<Ouvrage> listerOuvrages(Auteur a, String genre) {

        return ((DAOSpecialAuteur) model).listerOuvrages(a, genre);
    }
}
