package biblio.mvc.controller;

import biblio.metier.Lecteur;
import biblio.mvc.model.DAOLecteur;
import biblio.mvc.view.AbstractViewLecteur;

import java.util.List;

public class LecteurController {
    protected DAOLecteur modelLecteur;
    protected AbstractViewLecteur viewLecteur;

    public LecteurController(DAOLecteur modelLecteur, AbstractViewLecteur viewLecteur) {
        this.modelLecteur = modelLecteur;
        this.viewLecteur = viewLecteur;
        this.viewLecteur.setController(this); /* Méthode setController() -> Voir classe 'AbstractViewLecteur' */
    }

    public List<Lecteur> getAll() {
        List<Lecteur> l = modelLecteur.getAll();
        return l;
    }

    public Lecteur add(Lecteur l) {
        Lecteur nLecteur = modelLecteur.add(l);
        return nLecteur;
    }


    public boolean remove(Lecteur l) {
        return modelLecteur.remove(l);
    }

    public Lecteur update(Lecteur l) {
        return modelLecteur.update(l);
    }

    public Lecteur search(Lecteur rechL) {
        return modelLecteur.read(rechL);
    }
}
