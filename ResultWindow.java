import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ResultWindow extends JFrame {
    private final int OFFSET = 22;

    public ResultWindow(String result){
        setLayerout(result);
        this.setResizable(false);
        this.setSize(GameConfiguration.RESULT_WINDOW_WIDTH, GameConfiguration.RESULT_WINDOW_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2 - GameConfiguration.RESULT_WINDOW_WIDTH/2, screenHeight/2 - GameConfiguration.RESULT_WINDOW_HEIGHT/2);
    }

    private void setLayerout(String result){
        this.setLayout(null);
        int currentHeight = 0;

        JLabel resultJLabel = new JLabel(result + "!", SwingConstants.CENTER);
        resultJLabel.setLocation(0, this.OFFSET / 2);
        resultJLabel.setSize(GameConfiguration.RESULT_WINDOW_WIDTH, GameConfiguration.RESULT_WINDOW_HEIGHT / 5);
        resultJLabel.setFont(new Font(GameConfiguration.GAME_FONT, Font.BOLD, GameConfiguration.RESULT_WINDOW_HEIGHT / 10));
        this.add(resultJLabel);
        currentHeight = resultJLabel.getLocation().y + resultJLabel.getSize().height;

        JLabel answerJLabel = new JLabel("The answer is",SwingConstants.CENTER);
        answerJLabel.setLocation(0, currentHeight+ this.OFFSET);
        answerJLabel.setSize(GameConfiguration.RESULT_WINDOW_WIDTH, GameConfiguration.RESULT_WINDOW_HEIGHT / 10);
        answerJLabel.setFont(new Font(GameConfiguration.GAME_FONT, Font.PLAIN, GameConfiguration.RESULT_WINDOW_HEIGHT / 20));
        this.add(answerJLabel);
        currentHeight = answerJLabel.getLocation().y + answerJLabel.getSize().height;

        JLabel answerWordJLabel = new JLabel(GameManager.getInstance().getAnswer(), SwingConstants.CENTER);
        answerWordJLabel.setLocation(0, currentHeight);
        answerWordJLabel.setSize(GameConfiguration.RESULT_WINDOW_WIDTH, GameConfiguration.RESULT_WINDOW_HEIGHT / 15);
        answerWordJLabel.setFont(new Font(GameConfiguration.GAME_FONT, Font.BOLD, GameConfiguration.RESULT_WINDOW_HEIGHT / 15));
        answerWordJLabel.setForeground(GameConfiguration.GREEN);
        this.add(answerWordJLabel);
        currentHeight = answerWordJLabel.getLocation().y + answerWordJLabel.getSize().height;


        JButton restartButton = new JButton("Play Again!");
        restartButton.setFont(new Font(GameConfiguration.GAME_FONT, Font.PLAIN, GameConfiguration.RESULT_WINDOW_HEIGHT / 20));
        restartButton.setSize(GameConfiguration.RESULT_WINDOW_WIDTH / 2, GameConfiguration.RESULT_WINDOW_HEIGHT / 10);
        restartButton.setLocation(GameConfiguration.RESULT_WINDOW_WIDTH / 2 - restartButton.getSize().width / 2, currentHeight + 2 * this.OFFSET);
        restartButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.getInstance().restart();
            }

        });
        this.add(restartButton);
        currentHeight = restartButton.getLocation().y + restartButton.getSize().height;

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font(GameConfiguration.GAME_FONT, Font.PLAIN, GameConfiguration.RESULT_WINDOW_HEIGHT / 20));
        quitButton.setSize(GameConfiguration.RESULT_WINDOW_WIDTH / 2, GameConfiguration.RESULT_WINDOW_HEIGHT / 10);
        quitButton.setLocation(GameConfiguration.RESULT_WINDOW_WIDTH / 2 - quitButton.getSize().width / 2, currentHeight + this.OFFSET);
        quitButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        this.add(quitButton);
    }
}
