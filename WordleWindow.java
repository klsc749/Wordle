import javax.swing.*;
import java.awt.*;
/**
 * The {@code WordleWindow} class is used to create wordle window that player can play
 * @author Wang Xin
 * @version 1.0
 */
public class WordleWindow extends JFrame {
    /**
     * Store the instance of the {@code WordlePanel}
     */
    private WordlePanel wPanel;
    /**
     * Store the instance of the {@code JLabel}
     */
    private JLabel tipJLabel;
    /**
     * Store the instance of the {@code KeyboardPanel}
     */
    private KeyboardPanel keyboardPanel;
    /**
     * Store the offset of the components
     */
    private final int OFFSET = 20;

    /**
     * Set the text, background color, and size of result window
     */
    public WordleWindow(){
        super("WOREDLE");
        initWindow();
    }

    /**
     * Set the text, background color, and size of result window
     */
    private void initWindow(){
        setLayout();
        this.setResizable(false);
        this.addKeyListener(KeyEventFactory.keyboardListener());
        this.setVisible(true);
        this.setSize(GameConfiguration.WORDLE_WINDOW_WIDTH, GameConfiguration.WORDLE_WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Set the window locating the center
     */
    private void setWindowCenter(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width; 
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2 - GameConfiguration.WORDLE_WINDOW_WIDTH/2, screenHeight/2 - GameConfiguration.WORDLE_WINDOW_HEIGHT/2);
    }

    /**
     * Set layout and components window
     */
    private void setLayout(){
        int currentHeight = 0;

        // Add tip label
        this.tipJLabel = new JLabel("Please input words :)", SwingConstants.CENTER);
        this.tipJLabel.setBounds(0, OFFSET, GameConfiguration.WORDLE_WINDOW_WIDTH, GameConfiguration.WORDLE_WINDOW_HEIGHT / 40);
        this.tipJLabel.setFont(new Font(GameConfiguration.GAME_FONT, Font.BOLD, GameConfiguration.WORDLE_WINDOW_HEIGHT / 40));
        currentHeight = this.tipJLabel.getSize().height + this.tipJLabel.getLocation().y;

        // Add wordle panel
        this.wPanel = new WordlePanel();    
        this.wPanel.setLocation((GameConfiguration.WORDLE_WINDOW_WIDTH - this.wPanel.getSize().width) / 2, currentHeight + OFFSET);
        currentHeight = this.wPanel.getSize().height + this.wPanel.getLocation().y;

        // Add keyboard panel
        keyboardPanel = new KeyboardPanel();
        keyboardPanel.setLocation((GameConfiguration.WORDLE_WINDOW_WIDTH - keyboardPanel.getSize().width) / 2, currentHeight + 4 * OFFSET);

        this.setLayout(null);
        this.getContentPane().add(this.tipJLabel);
        this.getContentPane().add(this.wPanel);
        this.getContentPane().add(this.keyboardPanel);
        this.setFocusable(true);
        setWindowCenter();
    }

    /**
     * get the instance of TipJLabel
     * @return the instance of TipJLabel
     */
    public JLabel getTipJLabel(){
        return this.tipJLabel;
    }

    /**
     * get the instance of WordlePanel
     * @return the instance of WordlePanel
     */
    public WordlePanel getWordlePanel(){
        return this.wPanel;
    }

    /**
     * get the instance of KeyBoardPanel
     * @return the instance of KeyBoardPanel
     */
    public KeyboardPanel getKeyboardPanel(){
        return this.keyboardPanel;
    }

    public enum TipType{
        HINT,
        WARNING
    }

    /**
     * Set the text and type of tip
     * @param tip the text of the tip
     * @param tipType the type of the tip
     */
    public void setTipAndType(String tip, TipType tipType){
        this.tipJLabel.setText(tip);
        switch (tipType) {
            case WARNING:
                this.tipJLabel.setForeground(new Color(192, 158, 3));
                break;
            default:
                this.tipJLabel.setForeground(Color.BLACK);
                break;
        }
    }
}
