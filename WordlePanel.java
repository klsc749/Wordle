import javax.swing.*;
import java.awt.*;

public class WordlePanel extends JPanel{
    private final int WIDTH = 500;
    private final int ROWS = 6;
    private final int COLS = 5;
    private final int GAP = 10; 
    private CharacterLabel[] cLabels;

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

    public CharacterLabel getCharacterLabel(int row, int col){
        return cLabels[row * COLS + col];
    }
}
