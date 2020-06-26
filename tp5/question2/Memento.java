package question2;

import java.util.*;

/**
 * La classe Memento est responsable du stockage de l'état de la 
 * 'liste de String' à chaque action 
 * ('retirer' , 'croissant' , 'decroisssant').
 *
 * @author Agatha Khairallah
 * @version 1.0
 */
public class Memento
{
    // 'liste de String' stockée à chaque action
    private List mementoListe;

    public Memento(List mementoListe){
        this.mementoListe = mementoListe;
    }

    public List getMementoListe(){
        return mementoListe;
    }

}
