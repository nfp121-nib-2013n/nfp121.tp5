package question3;

import java.util.Set;
import java.util.TreeSet;
/**
 * Les �l�ments doivent �tre comparables, alors tout �l�ment
 * de type 'T' doit forcement h�riter de la classe Comparable
 */
public class TreeSetFactory<T extends Comparable<T>> implements Factory<TreeSet<T>>{

    public TreeSet<T> create(){
        return new TreeSet<T>();
    }
}
