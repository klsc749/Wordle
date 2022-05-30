import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ResultWindow extends JFrame {
    private final int OFFSET = 22;

    public ResultWindow(String result){
        setLayerout(result);
        this.setResizable(false);
        this.setSize(Gameconfiguration.RESULT_DIALOG_WIDTH, Gameconfiguration.RESULT_DIALOG_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2 - Gameconfiguration.RESULT_DIALOG_WIDTH/2, screenHeight/2 - Gameconfiguration.RESULT_DIALOG_HEIGHT/2);
    }

    private void setLayerout(String result){
        this.setLayout(null);
        int currentHeight = 0;

        JLabel resultJLabel = new JLabel(result + "!", SwingConstants.CENTER);
        resultJLabel.setLocation(0, this.OFFSET / 2);
        resultJLabel.setSize(Gameconfiguration.RESULT_DIALOG_WIDTH, Gameconfiguration.RESULT_DIALOG_HEIGHT / 5);
        resultJLabel.setFont(new Font(Gameconfiguration.GAME_FONT, Font.BOLD, Gameconfiguration.RESULT_DIALOG_HEIGHT / 10));
        this.add(resultJLabel);
        currentHeight = resultJLabel.getLocation().y + resultJLabel.getSize().height;

        JLabel answerJLabel = new JLabel("The answer is",SwingConstants.CENTER);
        answerJLabel.setLocation(0, currentHeight+ this.OFFSET);
        answerJLabel.setSize(Gameconfiguration.RESULT_DIALOG_WIDTH, Gameconfiguration.RESULT_DIALOG_HEIGHT / 10);
        answerJLabel.setFont(new Font(Gameconfiguration.GAME_FONT, Font.PLAIN, Gameconfiguration.RESULT_DIALOG_HEIGHT / 20));
        this.add(answerJLabel);
        currentHeight = answerJLabel.getLocation().y + answerJLabel.getSize().height;

        JLabel answerWordJLabel = new JLabel(GameManager.getInstance().getAnswer(), SwingConstants.CENTER);
        answerWordJLabel.setLocation(0, currentHeight);
        answerWordJLabel.setSize(Gameconfiguration.RESULT_DIALOG_WIDTH, Gameconfiguration.RESULT_DIALOG_HEIGHT / 15);
        answerWordJLabel.setFont(new Font(Gameconfiguration.GAME_FONT, Font.BOLD, Gameconfiguration.RESULT_DIALOG_HEIGHT / 15));
        answerWordJLabel.setForeground(Gameconfiguration.GREEN);
        this.add(answerWordJLabel);
        currentHeight = answerWordJLabel.getLocation().y + answerWordJLabel.getSize().height;


        JButton restartButton = new JButton("Play Again!");
        restartButton.setFont(new Font(Gameconfiguration.GAME_FONT, Font.PLAIN, Gameconfiguration.RESULT_DIALOG_HEIGHT / 20));
        restartButton.setSize(Gameconfiguration.RESULT_DIALOG_WIDTH / 2, Gameconfiguration.RESULT_DIALOG_HEIGHT / 10);
        restartButton.setLocation(Gameconfiguration.RESULT_DIALOG_WIDTH / 2 - restartButton.getSize().width / 2, currentHeight + 2 * this.OFFSET);
        restartButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.getInstance().restart();
            }

        });
        this.add(restartButton);
        currentHeight = restartButton.getLocation().y + restartButton.getSize().height;

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font(Gameconfiguration.GAME_FONT, Font.PLAIN, Gameconfiguration.RESULT_DIALOG_HEIGHT / 20));
        quitButton.setSize(Gameconfiguration.RESULT_DIALOG_WIDTH / 2, Gameconfiguration.RESULT_DIALOG_HEIGHT / 10);
        quitButton.setLocation(Gameconfiguration.RESULT_DIALOG_WIDTH / 2 - quitButton.getSize().width / 2, currentHeight + this.OFFSET);
        quitButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        this.add(quitButton);
    }
}
