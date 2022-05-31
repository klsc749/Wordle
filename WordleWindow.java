import javax.swing.*;
import java.awt.*;
public class WordleWindow extends JFrame {
    private WordlePanel wPanel;
    private JLabel tipJLabel;
    private KeyboardPanel keyboardPanel;
    private final int OFFSET = 20;

    public WordleWindow(){
        super("WOREDLE");
        initWindow();
    }

    private void initWindow(){
        setLayout();
        this.addKeyListener(KeyEventFactory.keyboardListener());
        this.setVisible(true);
        this.setSize(Gameconfiguration.WORDLE_WINDOW_WIDTH, Gameconfiguration.WORDLE_WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setWindowCenter(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width; 
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2 - Gameconfiguration.WORDLE_WINDOW_WIDTH/2, screenHeight/2 - Gameconfiguration.WORDLE_WINDOW_HEIGHT/2);
    }

    private void setLayout(){
        int currentHeight = 0;

        this.tipJLabel = new JLabel("Please input words :)", SwingConstants.CENTER);
        this.tipJLabel.setBounds(0, OFFSET, Gameconfiguration.WORDLE_WINDOW_WIDTH, Gameconfiguration.WORDLE_WINDOW_HEIGHT / 40);
        this.tipJLabel.setFont(new Font("宋体", Font.BOLD, Gameconfiguration.WORDLE_WINDOW_HEIGHT / 40));
        currentHeight = this.tipJLabel.getSize().height + this.tipJLabel.getLocation().y;

        this.wPanel = new WordlePanel();
        this.wPanel.setLocation((Gameconfiguration.WORDLE_WINDOW_WIDTH - this.wPanel.getSize().width) / 2, currentHeight + OFFSET);
        currentHeight = this.wPanel.getSize().height + this.wPanel.getLocation().y;

        keyboardPanel = new KeyboardPanel();
        keyboardPanel.setLocation((Gameconfiguration.WORDLE_WINDOW_WIDTH - keyboardPanel.getSize().width) / 2, currentHeight + 4 * OFFSET);

        this.setLayout(null);
        this.getContentPane().add(this.tipJLabel);
        this.getContentPane().add(this.wPanel);
        this.getContentPane().add(this.keyboardPanel);
        this.setFocusable(true);
        setWindowCenter();
    }

    public JLabel getTipJLabel(){
        return this.tipJLabel;
    }

    public WordlePanel getWordlePanel(){
        return this.wPanel;
    }

    public KeyboardPanel getKeyboardPanel(){
        return this.keyboardPanel;
    }

    public enum TipType{
        HINT,
        WARNING
    }

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
