import javax.swing.*;
public class KeyButton extends JButton {
    private char keyChar;
    public enum KeyButtonState{

    }

    public KeyButton(String key){
        super(key);
        this.keyChar = getChar(key);
        this.setFocusable(false);
        this.setBackground(Gameconfiguration.Key_BUTTON_NOT_PRESS_COLOR);
        this.addActionListener(KeyEventFactory.virtualKeyboardListener(keyChar));
    }

    private char getChar(String key){
        if("Delete".equals(key)){
            return '\b';
        }
        else if("Enter".equals(key)){
            return '\n';
        }
        else{
            return key.toCharArray()[0];
        }
    }

    public void setKeyButtonState(Gameconfiguration.CharState state){
        switch (state) {
            case CONTAIN_AND_RIGHT_POSITION:
                this.setBackground(Gameconfiguration.GREEN);
                break;
            case CONTAIN_BUT_WRONG_POSITION:
                this.setBackground(Gameconfiguration.YELLOW);
                break;
            default:
                this.setBackground(Gameconfiguration.Key_BUTTON_NOT_PRESS_COLOR);
                break;
        }
    }
}
