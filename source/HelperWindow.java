import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The {@code HelperWindow} class is used to create a helper window that can help player complete the game
 * @author Wang Xin
 * @version 1.0
 */
public class HelperWindow extends JFrame{
    /**
     * Store the offset the components
     */
    private final int OFFSET = 20;
    /**
     * Store the instances of the {@code HelperInputTextField} class
     */
    private HelperInputTextField[] hTextFields;
    /**
     * Store the instance of the {@code JTextField} class that show search results
     */
    private JTextArea wordsTextArea;

    /**
     * Set the layout and window property
     */
    public HelperWindow(){
        super("Helper");
        initLayout();
        this.setSize(GameConfiguration.HELPER_WINDOW_WIDTH, GameConfiguration.HELPER_WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        //set helper window at the left of the wordle window
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width; 
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth/2 - GameConfiguration.WORDLE_WINDOW_WIDTH/2 - GameConfiguration.HELPER_WINDOW_WIDTH, screenHeight/2 - GameConfiguration.HELPER_WINDOW_HEIGHT/2);
    }

    /**
     * Init the layout
     */
    private void initLayout()
    {
        int currentHeight = 0;
        hTextFields = new HelperInputTextField[5];
        this.setLayout(null);

        //Add label showing "Wordle Helper"
        JLabel helperLabel = new JLabel("Wordle Helper", SwingConstants.CENTER);
        helperLabel.setFont(new Font(GameConfiguration.GAME_FONT, Font.PLAIN, GameConfiguration.HELPER_WINDOW_HEIGHT / 12));
        helperLabel.setLocation(0, OFFSET);
        helperLabel.setSize(GameConfiguration.HELPER_WINDOW_WIDTH, GameConfiguration.HELPER_WINDOW_HEIGHT / 12);
        this.add(helperLabel);
        currentHeight = helperLabel.getSize().height + helperLabel.getLocation().y;

        //Add JPanel and HelperInputTextFields
        JPanel inputPanel = new JPanel(new GridLayout(1, 4, 5, 0));
        int inputPanelWidth = GameConfiguration.HELPER_WINDOW_WIDTH * 5 / 6;
        inputPanel.setSize(inputPanelWidth , inputPanelWidth / 6);
        inputPanel.setLocation(GameConfiguration.HELPER_WINDOW_WIDTH / 2 - inputPanel.getSize().width / 2, currentHeight + OFFSET);
        this.add(inputPanel);

        for(int i = 0; i < 5; i++){
            hTextFields[i] = new HelperInputTextField();
            inputPanel.add(hTextFields[i]);
        }

        currentHeight = inputPanel.getSize().height + inputPanel.getLocation().y;

        //Add button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font(GameConfiguration.GAME_FONT, Font.PLAIN, GameConfiguration.HELPER_WINDOW_HEIGHT / 20));
        searchButton.setSize(GameConfiguration.HELPER_WINDOW_WIDTH / 3, GameConfiguration.HELPER_WINDOW_HEIGHT / 15);
        searchButton.setLocation(GameConfiguration.HELPER_WINDOW_WIDTH / 2 - searchButton.getSize().width / 2, currentHeight + OFFSET);
        this.add(searchButton);
        searchButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String regex = new String();
                for (HelperInputTextField helperInputTextField : hTextFields) {
                    regex += helperInputTextField.getText().isEmpty() ? "." : helperInputTextField.getText();
                }
                if(!".....".equals(regex)){
                    wordsTextArea.setText(GameManager.getInstance().getSearchResult(regex));
                }
            }
            
        });
        currentHeight = searchButton.getSize().height + searchButton.getLocation().y;

        //Add JTextArea
        wordsTextArea = new JTextArea();
        wordsTextArea.setEditable(false);
        wordsTextArea.setFont(new Font(GameConfiguration.GAME_FONT, Font.PLAIN, GameConfiguration.HELPER_WINDOW_HEIGHT / 20));
        JScrollPane wordsJScrollPane = new JScrollPane(wordsTextArea);
        wordsJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        wordsJScrollPane.setSize(GameConfiguration.HELPER_WINDOW_WIDTH * 4 / 5, GameConfiguration.HELPER_WINDOW_HEIGHT / 2);
        wordsJScrollPane.setLocation(GameConfiguration.HELPER_WINDOW_WIDTH / 2 - wordsJScrollPane.getSize().width / 2, currentHeight + OFFSET);
        this.add(wordsJScrollPane);
    }

}

