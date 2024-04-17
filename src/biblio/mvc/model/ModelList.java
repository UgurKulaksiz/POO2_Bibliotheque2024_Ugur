package biblio.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class ModelList<T> extends DAO<T> {
    private List<T> listDatas = new ArrayList<>();


    @Override
    public T add(T elt) {
        boolean present = listDatas.contains(elt);

        if (!present) {
            listDatas.add(elt);
            notifyObservers();

            return elt;
        } else return null;
    }

    @Override
    public boolean remove(T elt) {
        boolean ok = listDatas.remove(elt);

        notifyObservers();
        return ok;
    }

    @Override
    public T update(T elt) {
        int p = listDatas.indexOf(elt);

        if (p < 0) return null;
        listDatas.set(p, elt);
        notifyObservers();

        return elt;
    }

    @Override
    public T read(T rech) {
        int p = listDatas.indexOf(rech);

        if (p < 0) return null;

        return listDatas.get(p);
    }

    @Override
    public List<T> getAll() {
        return listDatas;
    }
}
