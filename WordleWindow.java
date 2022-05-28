import javax.swing.*;
import java.awt.*;
public class WordleWindow extends JFrame {
    private WordlePanel wPanel;
    private JLabel tipJLabel;
    private KeyboardPanel keyboardPanel;

    public WordleWindow(){
        super("WOREDLE");
        initWindow();
    }

    private void initWindow(){
        setLayout();
        this.addKeyListener(KeyEventFactory.keyboardListener());
        this.setVisible(true);
        this.setSize(Gameconfiguration.WINDOW_WIDTH, Gameconfiguration.WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setWindowCenter(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        this.setLocation(screenWidth/2 - Gameconfiguration.WINDOW_WIDTH/2, screenHeight/2 - Gameconfiguration.WINDOW_HEIGHT/2);//设置窗口居中显示
    }

    private void setLayout(){
        this.tipJLabel = new JLabel("Please input words :)", SwingConstants.CENTER);
        this.tipJLabel.setBounds(0, 0, Gameconfiguration.WINDOW_WIDTH, Gameconfiguration.WINDOW_HEIGHT / 40);
        this.tipJLabel.setFont(new Font("宋体", Font.BOLD, Gameconfiguration.WINDOW_HEIGHT / 50));

        this.wPanel = new WordlePanel();
        this.wPanel.setLocation((Gameconfiguration.WINDOW_WIDTH - this.wPanel.getSize().width) / 2, this.tipJLabel.getSize().height + 50);

        keyboardPanel = new KeyboardPanel();
        keyboardPanel.setLocation((Gameconfiguration.WINDOW_WIDTH - keyboardPanel.getSize().width) / 2, this.wPanel.getLocation().y + this.wPanel.getSize().height + 50);

        this.setLayout(null);
        this.getContentPane().add(this.tipJLabel);
        this.getContentPane().add(this.wPanel);
        this.getContentPane().add(keyboardPanel);
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
