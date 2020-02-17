import javax.swing.JButton;

@SuppressWarnings("serial")
/**
 * Constructors.
 */
public class Card extends JButton{
    private int id;
    private boolean matched = false;


    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }


    void setMatched(){
        this.matched = true;
    }

    boolean getMatched(){
        return this.matched;
    }
}
