package biblio.mvcold.controller;

import biblio.metier.Exemplaire;
import biblio.metier.Rayon;
import biblio.mvcold.model.DAORayon;
import biblio.mvcold.view.AbstractViewRayon;

import java.util.List;

public class ControllerRayon {
    protected DAORayon modelRayon;
    protected AbstractViewRayon viewRayon;

    public ControllerRayon(DAORayon modelRayon, AbstractViewRayon viewRayon) {
        this.modelRayon = modelRayon;
        this.viewRayon = viewRayon;
        this.viewRayon.setController(this);
    }

    public List<Rayon> getAll() {
        List<Rayon> list = modelRayon.getAll();
        return list;
    }

    public Rayon add(Rayon r) {
        Rayon nRayon = modelRayon.add(r);

        return nRayon;
    }

    public boolean remove(Rayon r) {
        return modelRayon.remove(r);
    }

    public Rayon update(Rayon r) {
        return modelRayon.update(r);
    }

    public Rayon search(Rayon rechRayon) {
        return modelRayon.read(rechRayon);
    }

    public List<Exemplaire> listerExemplaires(Rayon r) {
        return modelRayon.listerExemplaires(r);
    }
}
