import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
/**
 * The class {@code KeyboardPanel} class is used to created a virtual keyboard 
 * @author Wang Xin
 * @version 1.0
 */
public class KeyboardPanel extends JPanel {
    /**
     * Store the width of the keyboard
     */
    public static final int KEYBOARD_WIDTH = GameConfiguration.WORDLE_WINDOW_WIDTH * 6 / 7;
    /**
     * Store the height of the keyboard
     */
    public static final int KEYBOARD_HEIGHT = GameConfiguration.WORDLE_WINDOW_HEIGHT / 7; 
    /**
     * Store the number of key buttons of each row
     */
    private final int[] KEYS_NUM_EACH_ROW = {10, 9, 9};
    /**
     * Store the gap of the key buttons
     */
    private final int KEYS_GAP = 2;
    /**
     * Store the the order of keys
     */
    private final String[] KEYS_NAME = {
        "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
        "A", "S", "D", "F", "G", "H", "J", "K", "L",
        "Delete", "Z", "X", "C", "V", "B", "N", "M", "Enter"
    };
    /**
     * Store a hash map of {@code String} and {@code KeyButton} that can get KeyButton by the key name
     */
    private HashMap<String, KeyButton> keyButtons = new HashMap<>();

    /**
     * Set the layout and size
     */
    public KeyboardPanel(){
        super();
        setKeysLayout();
        this.setSize(KEYBOARD_WIDTH, KEYBOARD_HEIGHT);
    }

    /**
     * Set the layout of keys
     */
    private void setKeysLayout(){
        this.setLayout(new GridLayout(3, 1, 1, 0));
        JPanel[] keys = new JPanel[3];
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

    /**
     * Get the {@code KeyButton} by key letter 
     * @param letter the string of the key
     * @return Instance of {@code KeyButton}
     */
    public KeyButton getKeyButton(String letter){
        return keyButtons.get(letter);
    }
}
