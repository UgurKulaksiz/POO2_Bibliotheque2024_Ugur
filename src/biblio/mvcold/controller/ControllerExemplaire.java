package biblio.mvcold.controller;

import biblio.metier.Exemplaire;
import biblio.mvcold.model.DAOExemplaire;
import biblio.mvcold.view.AbstractViewExemplaire;

import java.util.List;

public class ControllerExemplaire {
    protected DAOExemplaire modelExemplaire;
    protected AbstractViewExemplaire viewExemplaire;

    public ControllerExemplaire(DAOExemplaire modelExemplaire, AbstractViewExemplaire viewExemplaire) {
        this.modelExemplaire = modelExemplaire;
        this.viewExemplaire = viewExemplaire;
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
