package biblio.mvc.view;

import biblio.metier.Exemplaire;
import biblio.mvc.controller.ExemplaireController;
import biblio.mvc.observer.Observer;

import java.util.List;

public abstract class AbstractViewExemplaire implements Observer {
    protected ExemplaireController exemplaireController;
    protected List<Exemplaire> listExemplaire;

    public void setController(ExemplaireController exemplaireController) {
        this.exemplaireController = exemplaireController;
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
