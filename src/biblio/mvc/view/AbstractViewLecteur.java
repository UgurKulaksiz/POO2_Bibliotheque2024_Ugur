package biblio.mvc.view;

import biblio.metier.Lecteur;
import biblio.mvc.controller.LecteurController;
import biblio.mvc.observer.Observer;

import java.util.List;

public abstract class AbstractViewLecteur implements Observer {
    protected LecteurController lecteurController;
    protected List<Lecteur> listLecteur;

    public void setController(LecteurController lecteurController) {
        this.lecteurController = lecteurController;
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
