import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.KeyAdapter;

public class KeyEventFactory {
    public static KeyAdapter keyboardListener(){
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    GameManager.getInstance().handleInput(Character.toUpperCase(e.getKeyChar()));
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        };
    }
}
