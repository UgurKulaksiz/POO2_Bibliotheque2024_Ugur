package biblio.mvcold.view;

import biblio.metier.Exemplaire;
import biblio.mvcold.controller.ControllerExemplaire;
import biblio.mvcold.observer.Observer;

import java.util.List;

public abstract class AbstractViewExemplaire implements Observer {
    protected ControllerExemplaire controllerExemplaire;
    protected List<Exemplaire> listExemplaire;

    public void setController(ControllerExemplaire controllerExemplaire) {
        this.controllerExemplaire = controllerExemplaire;
    }


    public abstract void menu();

    public abstract void affList(List listExemplaire);

    public List<Exemplaire> getAll(){
        return listExemplaire;
    }

    @Override
    public void update(List listExemplaire) {
        this.listExemplaire = listExemplaire;
        affList(listExemplaire);
    }
}
