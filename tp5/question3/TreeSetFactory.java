package question3;

import java.util.Set;
import java.util.TreeSet;
/**
 * Les éléments doivent être comparables, alors tout élément
 * de type 'T' doit forcement hériter de la classe Comparable
 */
public class TreeSetFactory<T extends Comparable<T>> implements Factory<TreeSet<T>>{

    public TreeSet<T> create(){
        return new TreeSet<T>();
    }
}
