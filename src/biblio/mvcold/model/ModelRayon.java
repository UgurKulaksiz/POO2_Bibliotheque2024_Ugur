package biblio.mvcold.model;

import biblio.metier.Exemplaire;
import biblio.metier.Rayon;

import java.util.ArrayList;
import java.util.List;

public class ModelRayon extends DAO<Rayon> implements DAOSpecialRayon {
    private List<Rayon> listRayonDatas = new ArrayList<>();


    @Override
    public Rayon add(Rayon r) {
        boolean present = listRayonDatas.contains(r);

        if (!present) {
            listRayonDatas.add(r);
            notifyObservers();

            return r;
        } else return null;
    }

    @Override
    public boolean remove(Rayon r) {
        boolean ok = listRayonDatas.remove(r);
        notifyObservers();

        return ok;
    }

    @Override
    public Rayon update(Rayon r) {
        int p = listRayonDatas.indexOf(r);

        if (p < 0) return null;
        listRayonDatas.set(p, r);
        notifyObservers();

        return r;
    }

    @Override
    public Rayon read(Rayon rechRayon) {
        int p = listRayonDatas.indexOf(rechRayon);
        if (p < 0) return null;

        return listRayonDatas.get(p);
    }

    @Override
    public List<Rayon> getAll() {
        return listRayonDatas;
    }


    @Override
    public List<Exemplaire> listerExemplaires(Rayon r) {
        return new ArrayList<>(r.listerExemplaires());
    }
}
