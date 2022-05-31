import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
public class KeyboardPanel extends JPanel {
    public static final int KEYBOARD_WIDTH = GameConfiguration.WORDLE_WINDOW_WIDTH * 6 / 7;
    public static final int KEYBOARD_HEIGHT = GameConfiguration.WORDLE_WINDOW_HEIGHT / 7; 
    private JPanel[] keys;
    private final int[] KEYS_NUM_EACH_ROW = {10, 9, 9};
    private final int KEYS_GAP = 2;
    private final String[] KEYS_NAME = {
        "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
        "A", "S", "D", "F", "G", "H", "J", "K", "L",
        "Delete", "Z", "X", "C", "V", "B", "N", "M", "Enter"
    };
    private HashMap<String, KeyButton> keyButtons = new HashMap<>();

    public KeyboardPanel(){
        super();
        setKeysLayout();
        this.setSize(KEYBOARD_WIDTH, KEYBOARD_HEIGHT);
    }

    private void setKeysLayout(){
        this.setLayout(new GridLayout(3, 1, 1, 0));
        keys = new JPanel[3];
        int keyCount = 0;
        for(int i = 0; i < 3; i++){
            keys[i] = new JPanel(new GridLayout(1, KEYS_NUM_EACH_ROW[i], KEYS_GAP, KEYS_GAP));
            int lenth = KEYS_NUM_EACH_ROW[i] + keyCount;
            for(int j = keyCount; j < lenth; j++){
                KeyButton keyButton = new KeyButton(KEYS_NAME[j]);
                keyButton.setFocusable(false);
                keys[i].add(keyButton);
                keyButtons.put(KEYS_NAME[j], keyButton);
                keyCount++;
            }
            this.add(keys[i]);
        }
    }

    public KeyButton getKeyButton(String letter){
        return keyButtons.get(letter);
    }
}
