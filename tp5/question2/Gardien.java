package question2;

import java.util.*;

/**
 * La classe Gardien est responsable du stockage des mementos à 
 * chaque action ('retirer' , 'croissant' , 'decroisssant') dans
 * une Pile (java.util.Stack).
 *
 * @author Agatha Khairallah
 * @version 1.0
 */
public class Gardien
{
    private Stack<Memento> mementos;
    
    public Gardien(){
        mementos = new Stack<>();
    }
    
    public Stack<Memento> getMementos(){
        return mementos;
    }
    
    // Retourner et enlever le dernier memento stocké dans la pile
    public Memento getMemento(){
        return mementos.pop();
    }
    
    // Ajouter un memento dans la pile
    public Memento addMemento(Memento memento){
        return mementos.push(memento);
    }
}
