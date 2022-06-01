import javax.swing.*;
import java.awt.*;
/**
 * The {@code WordlePanel} class is used to create wordle panel that includes {@code CharacterLabel}
 * @author Wang Xin
 * @version 1.0
 */
public class WordlePanel extends JPanel{
    /**
     * width of wordle panel
     */
    private final int WIDTH = 550;
    /**
     * rows of {@code CharacterLabel}
     */
    private final int ROWS = 6;
    /**
     * columns of {@code CharacterLabel}
     */
    private final int COLS = 5;
    /**
     * gap of components
     */
    private final int GAP = 10; 
    /**
     * Store the instances of {@code CharacterLabel}
     */
    private CharacterLabel[] cLabels;

    /**
     * Set the text, background color, and size of wordle window
     */
    public WordlePanel(){
        super();
        this.setLayout(new GridLayout(ROWS, COLS, GAP, GAP));
        cLabels = new CharacterLabel[ROWS * COLS];
        this.setSize(new Dimension(WIDTH, ROWS * WIDTH / COLS));
        for(int i = 0; i < ROWS * COLS; i++){

            CharacterLabel cl = new CharacterLabel();
            cLabels[i] = cl;
            this.add(cl);
        }
    }

    /**
     * get the index  of {@code CharacterLabel}
     * @param row row of {@code CharacterLabel}
     * @param col row of {@code CharacterLabel}
     * @return the index  of {@code CharacterLabel}
     */
    public CharacterLabel getCharacterLabel(int row, int col){
        return cLabels[row * COLS + col];
    }
}
