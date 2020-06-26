package question2;

import java.util.*;

/**
 * La classe Memento est responsable du stockage de l'�tat de la 
 * 'liste de String' � chaque action 
 * ('retirer' , 'croissant' , 'decroisssant').
 *
 * @author Agatha Khairallah
 * @version 1.0
 */
public class Memento
{
    // 'liste de String' stock�e � chaque action
    private List mementoListe;

    public Memento(List mementoListe){
        this.mementoListe = mementoListe;
    }

    public List getMementoListe(){
        return mementoListe;
    }

}
