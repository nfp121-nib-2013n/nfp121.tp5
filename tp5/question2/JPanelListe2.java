package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

/**
 * Pour la fonctionnalit� annuler, j'ai utilis� le patron Memento.
 * En effet, � chaque action ('retirer' , 'croissant' , 
 * 'decroisssant') la liste est sauvegard�e dans un objet Memento
 * dont celui-ci sera stock� dans la pile du gardien.
 * Suite � l'action 'annuler', le dernier Memento sauvegard� sera 
 * retirer de la pile en restituant l'�tat de la liste appropri�e
 * et puis la table des occurrences est mise � jour.
 */

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("d�croissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private Gardien gardien;

    private List<String> liste;
    private Map<String, Integer> occurrences;

    public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
        gardien = new Gardien();

        this.liste = liste;
        this.occurrences = occurrences;

        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);

        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incompl�te");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        // Listeners pour les boutons
        boutonRechercher.addActionListener(this);
        boutonRetirer.addActionListener(this);
        boutonOccurrences.addActionListener(this);
        boutonAnnuler.addActionListener(this);

        // Listeners pour le TextField
        // La touche Entr�e du clavier a le m�me effet qu'une 
        // action effectu�e sur ce bouton
        saisie.addActionListener(this);

        // Listeners pour les Checkbox
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("r�sultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie
                    .getText());
                afficheur
                .setText("r�sultat du retrait de tous les �l�ments commen�ant par -->  "
                    + saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  ??? ");
            } else if (ae.getSource() == boutonAnnuler) {
                /**
                 * Quand la pile est vide le bouton 'annuler' est
                 * sans effet.
                 */ 
                if(!gardien.getMementos().isEmpty()){
                    liste = gardien.getMemento().getMementoListe();
                    // Mise � jour la table des occurrences
                    occurrences = Chapitre2CoreJava2.occurrencesDesMots(liste);
                }
            }
            texte.setText(liste.toString());
        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        if (liste == null){
            return;
        }

        /**
         * Afin de stocker la 'liste de String', il est pr�f�rable
         * de cr�er une nouvelle liste (new LinkedList<>(liste))
         * qui contient les m�mes �l�ments pour �viter
         * les modifications concurrentes.
         */
        gardien.addMemento(new Memento(new LinkedList<>(liste)));

        if (ie.getSource() == ordreCroissant){
            Collections.sort(liste);
        }
        else if (ie.getSource() == ordreDecroissant){
            Collections.sort(liste, new trieDecroissant());
        }

        texte.setText(liste.toString());
    }

    private class trieDecroissant implements Comparator<String>{
        public int compare(String str1, String str2){ 
            return str2.compareTo(str1);
        }
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
        List<String> listeTemp = new LinkedList<>(liste);
        String elt;

        // Utilisation d'un Iterator
        Iterator<String> it = liste.iterator();
        while(it.hasNext()){
            elt = it.next();
            if(elt != null && elt.startsWith(prefixe)){
                /**
                 * Le retrait d'un �l�ment qui a d�j� �t� pr�sent
                 * dans la table retourne un nombre d'occurrences 
                 * �gal � z�ro
                 */
                occurrences.replace(elt, occurrences.get(elt) - 1);
                resultat = true;
                it.remove();
            }
        }

        if(resultat){

            /**
             * Afin de stocker la 'liste de String', il est pr�f�rable
             * de cr�er une nouvelle liste (new LinkedList<>(listeTemp))
             * qui contient les m�mes �l�ments pour �viter
             * les modifications concurrentes.
             */
            gardien.addMemento(new Memento(new LinkedList<>(listeTemp)));

        }
        return resultat;
    }
}