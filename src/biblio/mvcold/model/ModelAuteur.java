package biblio.mvcold.model;

import biblio.metier.Auteur;
import biblio.metier.Livre;
import biblio.metier.Ouvrage;
import biblio.metier.TypeLivre;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModelAuteur extends DAO<Auteur> implements DAOSpecialAuteur {
    private List<Auteur> listAuteurDatas = new ArrayList<>();

    @Override
    public Auteur add(Auteur a) {
        boolean present = listAuteurDatas.contains(a);

        if (!present) {
            listAuteurDatas.add(a);
            notifyObservers();

            return a;
        } else return null;
    }

    @Override
    public boolean remove(Auteur a) {
        boolean ok = listAuteurDatas.remove(a);
        notifyObservers();

        return ok;
    }

    @Override
    public Auteur update(Auteur a) {
        int p = listAuteurDatas.indexOf(a);

        if (p < 0) return null;

        listAuteurDatas.set(p, a);
        notifyObservers();

        return a;
    }

    @Override
    public Auteur read(Auteur rechAuteur) {
        int p = listAuteurDatas.indexOf(rechAuteur);

        if(p<0) return null;

        return listAuteurDatas.get(p);
    }

    @Override
    public List<Auteur> getAll() {
        return listAuteurDatas;
    }

    @Override
    public Set<Ouvrage> listerOuvrages(Auteur a) {
        return a.listerOuvrages();
    }

    @Override
    public List<Livre> listerLivre(Auteur a, TypeLivre typeLivre) {
        return a.listerLivres(typeLivre);
    }

    @Override
    public List<Ouvrage> listerOuvrages(Auteur a, String genre) {
        return a.listerOuvrages(genre);
    }
}
