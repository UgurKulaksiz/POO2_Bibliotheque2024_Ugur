package biblio.mvcold.view;

import biblio.metier.Auteur;
import biblio.mvcold.controller.ControllerAuteur;
import biblio.mvcold.observer.Observer;

import java.util.List;

public abstract class AbstractViewAuteur implements Observer {
    protected ControllerAuteur controllerAuteur;
    protected List<Auteur> listAuteur;

    public void setController(ControllerAuteur controllerAuteur) {
        this.controllerAuteur = controllerAuteur;
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
