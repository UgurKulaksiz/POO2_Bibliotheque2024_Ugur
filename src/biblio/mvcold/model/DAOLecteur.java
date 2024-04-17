package biblio.mvcold.model;

import biblio.metier.Lecteur;
import biblio.mvcold.observer.Subject;

import java.util.List;

public abstract class DAOLecteur extends Subject {
    public abstract Lecteur add(Lecteur l);

    public abstract boolean remove(Lecteur l);

    public abstract Lecteur update(Lecteur l);

    public abstract Lecteur read(Lecteur rechLecteur);

    public abstract List<Lecteur> getAll();

    @Override
    public List getNotification() {
        return null;
    }
}
