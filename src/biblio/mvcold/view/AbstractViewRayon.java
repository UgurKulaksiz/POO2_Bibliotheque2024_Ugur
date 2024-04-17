package biblio.mvcold.view;

import biblio.metier.Rayon;
import biblio.mvcold.controller.ControllerRayon;
import biblio.mvcold.observer.Observer;

import java.util.List;

public abstract class AbstractViewRayon implements Observer {
    protected ControllerRayon controllerRayon;
    protected List<Rayon> listRayon;

    public void setController(ControllerRayon controllerRayon) {
        this.controllerRayon = controllerRayon;
    }

    public abstract void menu();

    public abstract void affList(List la);

    public List<Rayon> getAll(){
        return listRayon;
    }
    @Override
    public void update(List listRayon) {
        this.listRayon = listRayon;
        affList(listRayon);
    }
}
