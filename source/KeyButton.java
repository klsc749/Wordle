import javax.swing.*;
/**
 * The {@code KeyButton} is used to created key button
 * @author Wang Xin
 * @version 1.0
 */
public class KeyButton extends JButton {
    /**
     * The char of the key button
     */
    private char keyChar;

    /**
     * set the text, background, action of the key button
     * @param key the string of the key
     */
    public KeyButton(String key){
        super(key);
        this.keyChar = getChar(key);
        this.setFocusable(false);
        this.setBackground(GameConfiguration.Key_BUTTON_NOT_PRESS_COLOR);
        this.addActionListener(KeyEventFactory.virtualKeyboardListener(keyChar));
    }

    /**
     * get chat of the key
     * @param key the string of the key
     * @return the char of the key
     */
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

    /**
     * Set the state of the object of {@code KeyButton}
     * @param state the state of the char of the key
     */
    public void setKeyButtonState(GameConfiguration.CharState state){
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
                this.setBackground(GameConfiguration.Key_BUTTON_NOT_PRESS_COLOR);
                break;
        }
    }
}
