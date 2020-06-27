package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

    protected java.util.Vector<T> table = new java.util.Vector<T>();

    /**
     * Attention:
     * Une classe X qui h�rite de 'Ensemble<T>' peut mettre sa variable 'table'
     * � null (comme �tant une variable 'protected').
     * Lorsque X tant � utiliser la m�thode 'size' par exemple, celle-ci g�n�ra
     * une exception de type 'NullPointerException'.
     */

    public int size() {
        if(table == null)
            return 0;
        return table.size();
    }

    public Iterator<T> iterator() {
        if(table == null)
            return null;
        return table.iterator();
    }

    public boolean add(T t) {
        if(table == null)
            return false;
        if(!this.contains(t)){ // ou m�me on peut utiliser table.contains(t)
            table.add(t); 
            /**
             * Si on utilise this.add(t), la m�thode addAll de la 
             * super-classe d�clenche une exception de type
             * 'java.lang.StackOverflowError' c'est parce qu'on
             * entre dans un appel r�cursive INFINIE de la 
             * m�thode add.
             */ 
            return true;
        }
        return false;
    }

    public Ensemble<T> union(Ensemble<? extends T> e) {
        if(e == null || this.table == null)
            return null;
        Ensemble resultat = new Ensemble();
        resultat.addAll(this.table);
        resultat.addAll(e.table);
        return resultat;
    }

    public Ensemble<T> inter(Ensemble<? extends T> e) {
        if(e == null || this.table == null)
            return null;
        Ensemble resultat = new Ensemble();
        resultat.addAll(this.table);
        resultat.retainAll(e.table);
        return resultat;
    }

    public Ensemble<T> diff(Ensemble<? extends T> e) {
        if(e == null || this.table == null)
            return null;
        Ensemble resultat = new Ensemble();
        resultat.addAll(this.table);
        resultat.removeAll(e.table);
        return resultat;
    }

    public Ensemble<T> diffSym(Ensemble<? extends T> e) {
        Ensemble union = this.union(e);
        if (union == null){
            return null;
        }
        Ensemble resultat = this.union(e).diff(this.inter(e));
        return resultat;
    }

}
