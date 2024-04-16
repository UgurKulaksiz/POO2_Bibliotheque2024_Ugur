package biblio.mvc.controller;

import biblio.metier.Exemplaire;
import biblio.mvc.model.DAOExemplaire;
import biblio.mvc.view.AbstractViewExemplaire;

import java.util.List;

public class ExemplaireController {
    protected DAOExemplaire modelExemplaire;
    protected AbstractViewExemplaire viewExemplaire;

    public ExemplaireController(DAOExemplaire modelExemplaire, AbstractViewExemplaire viewExemplaire) {
        this.modelExemplaire = modelExemplaire;
        this.viewExemplaire = viewExemplaire;
        this.viewExemplaire = setController(this);
    }

    public List<Exemplaire> getAll() {
        List<Exemplaire> listExemplaire = modelExemplaire.getAll();
        return listExemplaire;
    }

    public Exemplaire add(Exemplaire e) {
        Exemplaire nExemplaire = modelExemplaire.add(e);
        return nExemplaire;
    }


    public boolean remove(Exemplaire e) {
        return modelExemplaire.remove(e);
    }

    public Exemplaire update(Exemplaire e) {
        return modelExemplaire.update(e);
    }

    public Exemplaire search(Exemplaire rechEx) {
        return modelExemplaire.read(rechEx);
    }
}
