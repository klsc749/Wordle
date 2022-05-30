import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame{
    private int offset = 20;

    public StartWindow(){
        super("WORDLE");
        setLayerout();
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(Gameconfiguration.START_WINDOW_WIDTH, Gameconfiguration.START_WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2 - Gameconfiguration.START_WINDOW_WIDTH/2, screenHeight/2 - Gameconfiguration.START_WINDOW_HEIGHT/2);
    }

    private void setLayerout(){
        this.setLayout(null);
        int currentHeight = 0;

        JLabel wordle = new JLabel("WORDLE", SwingConstants.CENTER);
        wordle.setFont(new Font("宋体", Font.BOLD, Gameconfiguration.START_WINDOW_HEIGHT / 5));
        wordle.setSize(Gameconfiguration.START_WINDOW_WIDTH, Gameconfiguration.START_WINDOW_HEIGHT / 5);
        this.getContentPane().add(wordle);
        currentHeight = wordle.getSize().height + wordle.getLocation().y;

        JLabel levelLabel = new JLabel("Difficulty Level", SwingConstants.CENTER);
        levelLabel.setFont(new Font("宋体", Font.BOLD, Gameconfiguration.START_WINDOW_HEIGHT / 25));
        levelLabel.setBounds(0, currentHeight + offset * 2, Gameconfiguration.START_WINDOW_WIDTH, Gameconfiguration.START_WINDOW_HEIGHT / 20);
        currentHeight = levelLabel.getSize().height + levelLabel.getLocation().y;
        this.getContentPane().add(levelLabel);   

        String[] difficultLeve = {"EASY", "MEDIUM", "HARD"};
        JComboBox<String> difficultLevelComboBox = new JComboBox<>(difficultLeve);
        difficultLevelComboBox.setFont(new Font("宋体", Font.BOLD, Gameconfiguration.START_WINDOW_HEIGHT / 25));
        difficultLevelComboBox.setSize(Gameconfiguration.START_WINDOW_WIDTH * 2 / 3, Gameconfiguration.START_WINDOW_HEIGHT / 15);
        difficultLevelComboBox.setLocation(Gameconfiguration.START_WINDOW_WIDTH / 2 - difficultLevelComboBox.getSize().width/2, currentHeight + offset / 2);
        currentHeight = difficultLevelComboBox.getSize().height + difficultLevelComboBox.getLocation().y;
        this.getContentPane().add(difficultLevelComboBox);

        JCheckBox helperCheckBox = new JCheckBox("Open Helper");
        helperCheckBox.setFont(new Font("宋体", Font.BOLD, Gameconfiguration.START_WINDOW_HEIGHT / 25));        
        helperCheckBox.setSize(Gameconfiguration.START_WINDOW_WIDTH / 2, Gameconfiguration.START_WINDOW_HEIGHT / 15);
        helperCheckBox.setLocation(Gameconfiguration.START_WINDOW_WIDTH / 2  - helperCheckBox.getSize().width / 2, currentHeight + offset * 2);
        currentHeight = helperCheckBox.getSize().height + helperCheckBox.getLocation().y;
        this.getContentPane().add(helperCheckBox);

        JButton playButton = new JButton("PLAY!");
        playButton.setFont(new Font("宋体", Font.BOLD, Gameconfiguration.START_WINDOW_HEIGHT / 20));
        playButton.setSize(Gameconfiguration.START_WINDOW_WIDTH / 2, Gameconfiguration.START_WINDOW_HEIGHT / 10);
        playButton.setLocation(Gameconfiguration.START_WINDOW_WIDTH / 2  - playButton.getSize().width/2, currentHeight + offset * 3);
        playButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO add Helper window
                GameManager.getInstance().setGameMode(difficultLevelComboBox.getItemAt(difficultLevelComboBox.getSelectedIndex()));
                GameManager.getInstance().play();
            }
            
        });
        this.getContentPane().add(playButton);

        
    }
}
