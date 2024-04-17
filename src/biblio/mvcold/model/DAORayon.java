package biblio.mvcold.model;

import biblio.metier.Exemplaire;
import biblio.metier.Rayon;
import biblio.mvcold.observer.Subject;

import java.util.List;

public abstract class DAORayon extends Subject {
    public abstract Rayon add(Rayon elt) ;

    public abstract boolean remove( Rayon elt) ;

    public abstract Rayon update (Rayon elt) ;

    public abstract Rayon read(Rayon rech) ;

    public abstract List<Rayon> getAll() ;

    public List<Rayon> getNotification(){
        return getAll();
    }

    public abstract List<Exemplaire> listerExemplaires(Rayon r);
}
