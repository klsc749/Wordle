import javax.swing.*;
import java.awt.*;

/**
 * The {@code CharacterLabel} class is a label that displays the input of the player 
 * and sets its background color according to player's answer.
 * The {@code CharacterLabel} class inherits {@link JLabel} class 
 * @author Wang Xin
 * @version 1.0
 */
public class CharacterLabel extends JLabel {
    
    /**
     * The constructor inits the label. 
     * Set the text center, the font and default background color.  
     */
    public CharacterLabel(){
        super("",SwingConstants.CENTER);
        this.setFont(new Font(GameConfiguration.GAME_FONT, Font.BOLD, 60));
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        this.setLabelState(GameConfiguration.CharState.EMPTY);
    }

    /**
     * Change the background color of the label according to the state of the label
     * @param state a enum marks the state of label
     */
    public void setLabelState(GameConfiguration.CharState state){
        switch (state) {
            case CONTAIN_AND_RIGHT_POSITION:
                this.setBackground(GameConfiguration.GREEN);
                break;
            case CONTAIN_BUT_WRONG_POSITION:
                this.setBackground(GameConfiguration.YELLOW);
                break;
            case DO_NOT_CONTAIN:
                this.setBackground(GameConfiguration.GRAY);
                break;
            default:
                this.setBackground(GameConfiguration.LABEL_EMPTY);
                this.setText("");
                break;
        }
    }

}
