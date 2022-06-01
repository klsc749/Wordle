import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@code StartWindow} class is used to create start window and lets player choose difficulty level and if needs helper
 * @author Wang Xin
 * @version 1.0
 */
public class StartWindow extends JFrame{
    /**
     * Store the offset of the components
     */
    private final int OFFSET = 20;
    /**
     * Store the instance of JCheckBox
     */
    private JCheckBox helperCheckBox;

    /**
     * Set the text, background color, and size of start window
     */
    public StartWindow(){
        super("WORDLE");
        setLayerout();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(GameConfiguration.START_WINDOW_WIDTH, GameConfiguration.START_WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2 - GameConfiguration.START_WINDOW_WIDTH/2, screenHeight/2 - GameConfiguration.START_WINDOW_HEIGHT/2);
    }

    /**
     * Set the layout of the result window 
     */
    private void setLayerout(){
        this.setLayout(null);
        int currentHeight = 0;
        // Add JLabel
        JLabel wordle = new JLabel("WORDLE", SwingConstants.CENTER);
        wordle.setFont(new Font(GameConfiguration.GAME_FONT, Font.BOLD, GameConfiguration.START_WINDOW_HEIGHT / 7));
        wordle.setSize(GameConfiguration.START_WINDOW_WIDTH, GameConfiguration.START_WINDOW_HEIGHT / 5);
        this.getContentPane().add(wordle);
        currentHeight = wordle.getSize().height + wordle.getLocation().y;

        // Add JLabel
        JLabel levelLabel = new JLabel("Difficulty Level", SwingConstants.CENTER);
        levelLabel.setFont(new Font(GameConfiguration.GAME_FONT, Font.PLAIN, GameConfiguration.START_WINDOW_HEIGHT / 25));
        levelLabel.setBounds(0, currentHeight + OFFSET * 2, GameConfiguration.START_WINDOW_WIDTH, GameConfiguration.START_WINDOW_HEIGHT / 20);
        currentHeight = levelLabel.getSize().height + levelLabel.getLocation().y;
        this.getContentPane().add(levelLabel);   

        // Add ComboBox
        String[] difficultLeve = {"EASY", "MEDIUM", "HARD"};
        JComboBox<String> difficultLevelComboBox = new JComboBox<>(difficultLeve);
        difficultLevelComboBox.setFont(new Font(GameConfiguration.GAME_FONT, Font.BOLD, GameConfiguration.START_WINDOW_HEIGHT / 25));
        difficultLevelComboBox.setSize(GameConfiguration.START_WINDOW_WIDTH * 2 / 3, GameConfiguration.START_WINDOW_HEIGHT / 15);
        difficultLevelComboBox.setLocation(GameConfiguration.START_WINDOW_WIDTH / 2 - difficultLevelComboBox.getSize().width/2, currentHeight + OFFSET / 2);
        currentHeight = difficultLevelComboBox.getSize().height + difficultLevelComboBox.getLocation().y;
        this.getContentPane().add(difficultLevelComboBox);

        // Add CheckBox
        helperCheckBox = new JCheckBox("Open Helper");
        helperCheckBox.setFont(new Font(GameConfiguration.GAME_FONT, Font.BOLD, GameConfiguration.START_WINDOW_HEIGHT / 25));        
        helperCheckBox.setSize(GameConfiguration.START_WINDOW_WIDTH / 2, GameConfiguration.START_WINDOW_HEIGHT / 15);
        helperCheckBox.setLocation(GameConfiguration.START_WINDOW_WIDTH / 2  - helperCheckBox.getSize().width / 2, currentHeight + OFFSET * 2);
        currentHeight = helperCheckBox.getSize().height + helperCheckBox.getLocation().y;
        this.getContentPane().add(helperCheckBox);

        // Add JButton
        JButton playButton = new JButton("PLAY!");
        playButton.setFont(new Font(GameConfiguration.GAME_FONT, Font.BOLD, GameConfiguration.START_WINDOW_HEIGHT / 20));
        playButton.setSize(GameConfiguration.START_WINDOW_WIDTH / 2, GameConfiguration.START_WINDOW_HEIGHT / 10);
        playButton.setLocation(GameConfiguration.START_WINDOW_WIDTH / 2  - playButton.getSize().width/2, currentHeight + OFFSET * 3);
        playButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(helperCheckBox.isSelected()){
                    GameManager.getInstance().showHelperWindow();
                }
                GameManager.getInstance().setGameMode(difficultLevelComboBox.getItemAt(difficultLevelComboBox.getSelectedIndex()));
                GameManager.getInstance().play();
            }
            
        });
        this.getContentPane().add(playButton);

        
    }
}
