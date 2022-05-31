import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * The {@code KeyEVentFactory} class is used to get the event of key 
 * @author Wang Xin
 * @version 1.0
 */
public class KeyEventFactory {
    private KeyEventFactory(){
        
    }
    /**
     * Get the {@code KeyAdapter} of the keyboard
     * @return {@code KeyAdapter} happens when player presses the key button
     */
    public static KeyAdapter keyboardListener(){
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                GameManager.getInstance().handleInput(Character.toUpperCase(e.getKeyChar()));
            }
        };
    }

    /**
     * Get the {@code ActionListener} of the keyboard
     * @param keyChar player's input
     * @return {@code ActionListener} happens when player presses the virtual key button
     */
    public static ActionListener virtualKeyboardListener(char keyChar){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.getInstance().handleInput(keyChar);              
            }            
        };
    }
}
