package biblio.mvc.model;

import biblio.metier.*;
import biblio.mvc.observer.Subject;

import java.util.List;

public abstract class DAOExemplaire extends Subject {
    public abstract Exemplaire add(Exemplaire e);

    public abstract boolean remove(Exemplaire e);

    public abstract Exemplaire update(Exemplaire e);

    public abstract Exemplaire read(Exemplaire rechExemplaire);

    public abstract List<Exemplaire> getAll();

    @Override
    public List getNotification() {
        return null;
    }
}
