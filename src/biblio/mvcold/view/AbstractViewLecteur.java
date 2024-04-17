package biblio.mvcold.view;

import biblio.metier.Lecteur;
import biblio.mvcold.controller.ControllerLecteur;
import biblio.mvcold.observer.Observer;

import java.util.List;

public abstract class AbstractViewLecteur implements Observer {
    protected ControllerLecteur controllerLecteur;
    protected List<Lecteur> listLecteur;

    public void setController(ControllerLecteur controllerLecteur) {
        this.controllerLecteur = controllerLecteur;
    }

    public abstract void menu();

    public abstract void affList(List listLecteur);

    public List<Lecteur> getAll() {
        return listLecteur;
    }

    @Override
    public void update(List listLecteur) {
        this.listLecteur = listLecteur;
        affList(listLecteur);
    }
}
