package biblio.mvc.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> listObservers = new ArrayList<>();

    public void addObserver(Observer o){
        listObservers.add(o);
    }

    public void removeObserver(Observer o){
        listObservers.remove(o);
    }

    public void notifyObservers(){
        List l =getNotification();
        for(Observer o : listObservers)
            o.update(l);
    }

    public abstract List getNotification();
}
