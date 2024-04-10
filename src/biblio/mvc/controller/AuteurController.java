package biblio.mvc.controller;

import biblio.metier.Auteur;
import biblio.metier.Livre;
import biblio.metier.Ouvrage;
import biblio.metier.TypeLivre;
import biblio.mvc.model.DAOAuteur;
import biblio.mvc.view.AbstractViewAuteur;

import java.util.List;
import java.util.Set;

public class AuteurController {
    protected DAOAuteur modelAuteur;
    protected AbstractViewAuteur viewAuteur;

    public AuteurController(DAOAuteur modelAuteur, AbstractViewAuteur viewAuteur) {
        this.modelAuteur = modelAuteur;
        this.viewAuteur = viewAuteur;
        this.viewAuteur.setController(this); /* Méthode setController() -> Voir classe 'AbstractViexAuteur' */
    }

    public List<Auteur> getAll() {
        List<Auteur> l = modelAuteur.getAll();
        return l;
    }

    public Auteur add(Auteur a) {
        Auteur nAuteur = modelAuteur.add(a);
        return nAuteur;
    }


    public boolean remove(Auteur a) {
        return modelAuteur.remove(a);
    }

    public Auteur update(Auteur a) {
        return modelAuteur.update(a);
    }

    public Auteur search(Auteur rechA) {
        return modelAuteur.read(rechA);
    }

    public Set<Ouvrage> listerOuvrages(Auteur a) {
        return modelAuteur.listerOuvrages(a);
    }

    public List<Livre> listerLivre(Auteur a, TypeLivre typeLivre) {

        return modelAuteur.listerLivre(a, typeLivre);
    }

    public List<Ouvrage> listerOuvrages(Auteur a, String genre) {
        return modelAuteur.listerOuvrages(a, genre);
    }
}