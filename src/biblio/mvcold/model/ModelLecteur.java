package biblio.mvcold.model;

import biblio.metier.Lecteur;

import java.util.ArrayList;
import java.util.List;

public class ModelLecteur extends DAO<Lecteur>{
    private List<Lecteur> listLecteurDatas = new ArrayList<>();

    @Override
    public Lecteur add(Lecteur l) {
        boolean present = listLecteurDatas.contains(l);

        if (!present) {
            listLecteurDatas.add(l);
            notifyObservers();

            return l;
        } else return null;
    }

    @Override
    public boolean remove(Lecteur l) {
        boolean ok = listLecteurDatas.remove(l);
        notifyObservers();

        return ok;
    }

    @Override
    public Lecteur update(Lecteur l) {
        int p = listLecteurDatas.indexOf(l);

        if (p < 0) return null;

        listLecteurDatas.set(p, l);
        notifyObservers();

        return l;
    }

    @Override
    public Lecteur read(Lecteur rechLecteur) {
        int p = listLecteurDatas.indexOf(rechLecteur);

        if(p<0) return null;

        return listLecteurDatas.get(p);
    }

    @Override
    public List<Lecteur> getAll() {
        return listLecteurDatas;
    }
}
