package biblio.mvc.view;

import biblio.metier.Auteur;
import biblio.mvc.controller.AuteurController;
import biblio.mvc.observer.Observer;

import java.util.List;

public abstract class AbstractViewAuteur implements Observer {
    protected AuteurController auteurController;
    protected List<Auteur> listAuteur;

    public void setController(AuteurController auteurController) {
        this.auteurController = auteurController;
    }

    public abstract void menu();

    public abstract void affList(List listAuteur);

    public List<Auteur> getAll(){
        return listAuteur;
    }

    @Override
    public void update(List listAuteur) {
        this.listAuteur = listAuteur;
        affList(listAuteur);
    }
}
