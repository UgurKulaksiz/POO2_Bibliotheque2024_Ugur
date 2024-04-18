package biblio.mvcold.model;

import biblio.metier.Exemplaire;
import biblio.metier.Ouvrage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelOuvrage extends DAO<Ouvrage> implements DAOSpecialOuvrage {
    private List<Ouvrage> listOuvrageDatas = new ArrayList<>();


    @Override
    public Ouvrage add(Ouvrage o) {
        boolean present = listOuvrageDatas.contains(o);

        if (!present) {
            listOuvrageDatas.add(o);
            notifyObservers();

            return o;
        } else return null;
    }

    @Override
    public boolean remove(Ouvrage o) {
        boolean ok = listOuvrageDatas.remove(o);
        notifyObservers();

        return ok;
    }

    @Override
    public Ouvrage update(Ouvrage o) {
        int p = listOuvrageDatas.indexOf(o);

        if (p < 0) return null;
        listOuvrageDatas.set(p, o);
        notifyObservers();

        return o;
    }

    @Override
    public Ouvrage read(Ouvrage rechOuvrage) {
        int p = listOuvrageDatas.indexOf(rechOuvrage);
        if (p < 0) return null;

        return listOuvrageDatas.get(p);
    }

    @Override
    public List<Ouvrage> getAll() {
        return listOuvrageDatas;
    }

    @Override
    public List<Exemplaire> listerExemplaire(Ouvrage o) {
        return new ArrayList<>(o.listerExemplairesOuvrage());
    }

    @Override
    public List<Exemplaire> listerExemplaire(Ouvrage o, boolean enLocation) {
        List<Exemplaire> l = listerExemplaire(o);
        Iterator<Exemplaire> it = l.iterator();

        while (it.hasNext()) {
            if (it.next().enLocation() != enLocation) it.remove();
        }

        return l;
    }

    @Override
    public double amendeRetard(Ouvrage o, int nj) {
        return o.amendeRetard(nj);
    }
}
