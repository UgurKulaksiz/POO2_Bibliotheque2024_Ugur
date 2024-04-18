package biblio.mvcold.model;

import biblio.metier.Exemplaire;
import biblio.metier.Lecteur;
import biblio.metier.Mail;

import java.util.ArrayList;
import java.util.List;

public class ModelExemplaire extends DAO<Exemplaire> implements DAOSpecialExemplaire {
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

        if (p < 0) return null;

        return listExemplaireDatas.get(p);
    }

    @Override
    public List<Exemplaire> getAll() {
        return listExemplaireDatas;
    }

    /* METHODES */
    public void modifierEtat(Exemplaire ex, String etat) {
        ex.setDescriptionEtat(etat);
    }

    @Override
    public Lecteur lecteurActuel(Exemplaire ex) {
        return ex.lecteurActuelExemplaire();
    }


    @Override
    public void envoiMailLecteurActuel(Exemplaire ex, Mail m) {
        ex.envoiMailLecteurActuelExemplaire(m);
    }

    @Override
    public boolean enLocation(Exemplaire ex) {
        return ex.enLocation();
    }
}
