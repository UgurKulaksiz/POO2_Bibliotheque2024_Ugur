package biblio.mvcold.model;

import biblio.metier.Exemplaire;

import java.util.ArrayList;
import java.util.List;

public class ModelExemplaire extends DAO<Exemplaire>{
    private List<Exemplaire> listExemplaireDatas = new ArrayList<>();

    @Override
    public Exemplaire add(Exemplaire e) {
        boolean present = listExemplaireDatas.contains(e);

        if (!present) {
            listExemplaireDatas.add(e);
            notifyObservers();

            return e;
        } else return null;
    }

    @Override
    public boolean remove(Exemplaire e) {
        boolean ok = listExemplaireDatas.remove(e);
        notifyObservers();

        return ok;
    }

    @Override
        public Exemplaire update(Exemplaire e) {
        int p = listExemplaireDatas.indexOf(e);

        if (p < 0) return null;

        listExemplaireDatas.set(p, e);
        notifyObservers();

        return e;
    }

    @Override
    public Exemplaire read(Exemplaire rechEx) {
        int p = listExemplaireDatas.indexOf(rechEx);

        if(p<0) return null;

        return listExemplaireDatas.get(p);
    }

    @Override
    public List<Exemplaire> getAll() {
        return listExemplaireDatas;
    }
}
