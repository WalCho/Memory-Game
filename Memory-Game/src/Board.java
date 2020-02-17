import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

@SuppressWarnings("serial")
/**
 * This class contains the logic of modes: solo normal,solo duo
 */ class Board extends JFrame{


    private List<Card> cards;
    private Card selectedCard;
    private Card c1;
    private Card c2;
    private Timer t;
    private int score=0;

    /**
     * @param pairs card pairs
     * @param rows  rows of array
     * @param columns clomumns of array
     */
    Board(int pairs, int rows, int columns){


        List<Card> cardsList = new ArrayList<>();
        List<Integer> cardVals = new ArrayList<>();

        for (int i = 0; i < pairs; i++){
            cardVals.add(i);
            cardVals.add(i);
        }
       Collections.shuffle(cardVals);

        for (int val : cardVals){
            Card c = new Card();
            c.setId(val);
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    selectedCard = c;
                    doTurn();
                }
            });
            cardsList.add(c);
        }
        this.cards = cardsList;
        //set up the timer
        t = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                checkCards();
            }
        });

        t.setRepeats(false);

        //set up the board itself
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(rows , columns));
        for (Card c : cards){
            pane.add(c);
        }
        setTitle("Memory Match");
    }

    private void doTurn(){
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            c1.setText(String.valueOf(c1.getId()));
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setText(String.valueOf(c2.getId()));
            t.start();

        }
    }

    /**
     * Se auth thn methodo elegxontai an oi duo kartes einai idies kai katametrountai oi prospatheies tou xrhsth.An oi dyo kartes einai idies tis kanei flag kai o xrhsths dn mporei na tis
     * ksanapathsei alliws krubontai kai oi duo kartes kai o xrhsths sunexizei tis prospatheies tou. Otan oles oi kartes einai gurismenes to paixnidi teleiwnei kai emfanizontai dyo parathura
     * sta opoia anefontai oti o xrhsths nikhse kai tis prospatheies pou ekane mexri na oloklhrwsei to paixnidi.
     */

    private void checkCards(){

        if (c1.getId() == c2.getId()){
            c1.setEnabled(false);
            c2.setEnabled(false);
            c1.setMatched();
            c2.setMatched();
            score=score+1;
            if (this.isGameWon()){
                JOptionPane.showMessageDialog(this, "You win!");
                JOptionPane.showMessageDialog(null, "Your score : " + score);
                System.exit(0);
            }
        }

        else{
            c1.setText("");
            c2.setText("");
            score=score+1;
        }
        c1 = null;
        c2 = null;
    }

    private boolean isGameWon(){
        for(Card c: this.cards){
            if (!c.getMatched()){
                return false;
            }
        }
        return true;
    }

}
