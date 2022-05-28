import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class KeyEventFactory {
    private KeyEventFactory(){
        
    }

    public static KeyAdapter keyboardListener(){
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                GameManager.getInstance().handleInput(Character.toUpperCase(e.getKeyChar()));
            }
        };
    }

    public static ActionListener virtualKeyboardListener(char keyChar){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.getInstance().handleInput(keyChar);              
            }            
        };
    }
}
