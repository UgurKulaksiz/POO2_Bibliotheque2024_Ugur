package biblio.mvc.view;

import biblio.mvc.controller.Controller;
import biblio.mvc.observer.Observer;

import java.util.List;

public abstract class AbstractView<T> implements Observer {
    protected Controller<T> controller;
    protected List<T> list;

    public void setController(Controller<T> controller) {
        this.controller = controller;
    }

    public abstract void menu();

    public abstract void affMsg(String msg);

    public abstract void affList(List list);

    public List<T> getAll(){
        return list;
    }

    @Override
    public void update(List list) {
        this.list = list;
        affList(list);
    }
}
