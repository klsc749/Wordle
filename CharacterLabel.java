import javax.swing.*;
import java.awt.*;

public class CharacterLabel extends JLabel {
    public enum LabelState {
        CONTAIN_AND_RIGHT_POSITION,
        CONTAIN_BUT_WRONG_POSITION,
        DO_NOT_CONTAIN,
        EMPTY
    }
    
    private static final Color GRAY = new Color(120,124,126);
    private static final Color YELLOW = new Color(201,180,88);
    private static final Color GREEN = new Color(106,170,100);
    private static final Color EMPTY = new Color(213, 213, 213);

    public CharacterLabel(){
        super("",SwingConstants.CENTER);
        this.setFont(new Font("宋体", Font.BOLD, 60));
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        this.setLabelState(LabelState.EMPTY);
    }

    public CharacterLabel(String text){
        super(text,SwingConstants.CENTER);
        this.setFont(new Font("宋体", Font.BOLD, 60));
        this.setForeground(Color.WHITE);
        this.setOpaque(true);
        this.setLabelState(LabelState.EMPTY);
    }

    public void setLabelState(LabelState state){
        switch (state) {
            case CONTAIN_AND_RIGHT_POSITION:
                this.setBackground(CharacterLabel.GREEN);
                break;
            case CONTAIN_BUT_WRONG_POSITION:
                this.setBackground(CharacterLabel.YELLOW);
                break;
            case DO_NOT_CONTAIN:
                this.setBackground(CharacterLabel.GRAY);
                break;
            default:
                this.setBackground(CharacterLabel.EMPTY);
                this.setText("");
                break;
        }
    }

}
