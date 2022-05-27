import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Wordle extends JFrame{
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 600;

    private WordlePanel wPanel;

    public Wordle(){
        super();
        initWindow();
    }

    public static void main(String[] args) throws IOException {
        Wordle w = new Wordle();
        
    }

    private void initWindow(){
        setLayout();
        GameManager.getInstance().init(wPanel);

        this.addKeyListener(KeyEventFactory.keyboardListener());
        this.setVisible(true);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setWindowCenter();
    }

    private void setWindowCenter(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        this.setLocation(screenWidth/2 - WINDOW_WIDTH/2, screenHeight/2 - WINDOW_HEIGHT/2);//设置窗口居中显示
    }

    private void setLayout(){
        JLabel welcomeJLabel = new JLabel("Welcome to Wordle :-)", SwingConstants.CENTER);
        welcomeJLabel.setBounds(0, 0, Wordle.WINDOW_WIDTH, Wordle.WINDOW_HEIGHT / 7);


        wPanel = new WordlePanel();
        wPanel.setLocation((Wordle.WINDOW_WIDTH - wPanel.getSize().width) / 2, 0);

        this.getContentPane().add(welcomeJLabel);
        this.getContentPane().add(wPanel);
    }
}
