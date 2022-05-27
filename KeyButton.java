import javax.swing.*;
import java.awt.*;
public class KeyButton extends JButton {

    public KeyButton(String key){
        super(key);
        this.setFocusable(false);
    }

}
