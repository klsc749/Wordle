/**
 * The {@code GameManager} is used to manage the game and it's singleton Pattern
 * @author Wang Xin
 * @version 1.0 
 */
public class GameManager {
    /**
     * the static instance of {@code GameManager}
     */
    private static GameManager instance;
    /**
     * Store the answer of the game
     */
    private String answer;
    /**
     * Store the instance of {@code WordLists} that reads words from files
     */
    private WordLists wordLists;
    /**
     * Store the instance of {@code WordPanel} that includes {@code CharacterLabel}
     */
    private WordlePanel wPanel;
    /**
     * Store the instance of {@code KeyboardPanel} that includes virtual keyboard
     */
    private KeyboardPanel keyboardPanel;
    /**
     * Store the instance of {@code WordleWindow} that has {@code WordlePanel} and {@code KeyboardPanel}
     */
    private WordleWindow wordleWindow;
    /**
     * Store the instance of {@code StartWindow} that lets player chooses game setting 
     */
    private StartWindow startWindow;
    /**
     * Store the instance of {@code ResultWindow} that shows game result 
     */
    private ResultWindow resultWindow;
    /**
     * Store the instance of {@code HelperWindow} that helps player complete the game 
     */
    private HelperWindow helperWindow;
    /**
     * Store the current row of the {@code CharacterLabel} arrays 
     */
    private int currentRow = 0;
    /**
     * Store the current column of the {@code CharacterLabel} arrays 
     */
    private int currentCol = -1;
    /**
     * Store the difficulty level of the game 
     */
    private GameConfiguration.GameMode gameMode;

    private boolean isEnd = false;

    /**
     * The constructor of {@code GameManager} class is private so that {@code GameManager} class can't be instantiated
     */
    private GameManager(){

    }

    /**
     * Make sure that there only exits one instance of {@code GameManager} 
     * @return the instance of {@code GameManager}
     */
    public static GameManager getInstance(){
        if(GameManager.instance == null){
            GameManager.instance = new GameManager();
        }

        return GameManager.instance;
    }

    /**
     * Start the game
     */
    public void start(){
        init();
    }

    /**
     * Show the wordle window
     */
    public void play(){
        closeStartWindow();
        showWordleWindow();
    }

    /**
     * Restart the game
     */
    public void restart(){
        closeResultWindow();
        start();
    }

    /**
     * Init the game
     */
    private void init(){
        showStartWindow();
        currentRow = 0;
        currentCol = -1;
        isEnd = false;
    }

    /**
     * Create and show wordle window
     */
    private void showWordleWindow(){
        createWordleWindow();
        this.wordleWindow.setVisible(true);
    }

    /**
     * Close wordle window
     */
    private void closeWordleWindow(){
        this.wordleWindow.dispose();
    }

    /**
     * Create and show start window
     */
    private void showStartWindow(){
        createStartWindow();
        this.startWindow.setVisible(true);
    }

    /**
     * Close start window
     */
    private void closeStartWindow(){
        this.startWindow.dispose();
    }

    /**
     * Create and show wordle window
     */
    private void createWordleWindow(){
        this.wordleWindow = new WordleWindow();
        this.wPanel = this.wordleWindow.getWordlePanel();
        this.keyboardPanel = this.wordleWindow.getKeyboardPanel();
        wordLists = new WordLists(this.gameMode);
        this.answer = wordLists.getAnswer();
        System.out.println(this.answer);
        this.wordleWindow.dispose();
    }

    /**
     * Create start window
     */
    private void createStartWindow(){
        this.startWindow = new StartWindow();
        this.startWindow.dispose();
    }

    /**
     * Create and show result window
     * @param result the result of the game success or fail
     */
    private void showResultWindow(String result){
        closeWordleWindow();
        this.resultWindow = new ResultWindow(result);
    }

    /**
     * Close start window
     */
    private void closeResultWindow(){
        this.resultWindow.dispose();
    }

    /**
     * Show helperWindow
     */
    public void showHelperWindow(){
        this.helperWindow = new HelperWindow();
    }

    /**
     * Handle the input of the player
     * @param c player's input
     */
    public void handleInput(char c){
        // set the text of tip label
        wordleWindow.setTipAndType("Please input words :)", WordleWindow.TipType.HINT);

        //when player inputs ENTER
        if(c == '\n'){
            //if player has already inputted 5 characters
            if(currentCol == 4){
                // if the word exits
                if(checkWord()){
                    compareWithAnswer();
                    currentRow++;
                    currentCol = -1;
                }// if the word doesn't exits
                else{
                    wordleWindow.setTipAndType("Please input correct word", WordleWindow.TipType.WARNING);
                }
            }// if the input length is less than 5
            else{
                wordleWindow.setTipAndType("Please input 5 letters", WordleWindow.TipType.WARNING);
            }
        }//if the input is BACKSPACE
        else if(c == '\b'){
            if(currentCol != -1){
                wPanel.getCharacterLabel(currentRow, currentCol).setLabelState(GameConfiguration.CharState.EMPTY);
                currentCol--;
            }else{
                wordleWindow.setTipAndType("You can't delete now", WordleWindow.TipType.WARNING);
            }
        }// if the input is letter
        else if(c >= 'A' && c <= 'Z'){
            if(currentCol > 3){
                wordleWindow.setTipAndType("You can't input more letters", WordleWindow.TipType.WARNING);
            }else{
                currentCol++;
                wPanel.getCharacterLabel(currentRow, currentCol).setText(Character.toString(c));
            }
        }//if the input is other
        else{
            wordleWindow.setTipAndType("Please input letters", WordleWindow.TipType.WARNING);
        }
        // if user use 6 chances
        if(currentRow == 6 && !isEnd){
            showResultWindow("FAIL");
            isEnd = true;
        }
    }

    /**
     * Check if the word entered by the player exists
     * @return true if the word entered by the player exists
     */
    private boolean checkWord(){
        String word = "";
        for(int i = 0; i < 5; i++){
            word += wPanel.getCharacterLabel(currentRow, i).getText();
        }
        return wordLists.verifyWord(word);
    }

    /**
     * Compare player's answer and the correct answer and set the state of {@code CharacterLabel} objects and {@code KeyButton} objects 
     */
    private void compareWithAnswer(){
        GameConfiguration.CharState state = null;
        
        int correctCNt = 0;

        for(int i = 0; i < 5; i++){
            int index = answer.indexOf(wPanel.getCharacterLabel(currentRow, i).getText());
            if(index == -1){
                state = GameConfiguration.CharState.DO_NOT_CONTAIN;
            }
            else{
                if(answer.charAt(i) == wPanel.getCharacterLabel(currentRow, i).getText().toCharArray()[0]){
                    state = GameConfiguration.CharState.CONTAIN_AND_RIGHT_POSITION;
                    correctCNt++;
                }
                else{
                    state =  GameConfiguration.CharState.CONTAIN_BUT_WRONG_POSITION;
                }
            }
            wPanel.getCharacterLabel(currentRow, i).setLabelState(state);
            keyboardPanel.getKeyButton(wPanel.getCharacterLabel(currentRow, i).getText()).setKeyButtonState(state);;
        }

        if(correctCNt == 5){
            showResultWindow("SUCCESS");
            isEnd = true;
        }
    }

    /**
     * Set the mode of the game
     * @param gameMode the mode of the game
     */
    public void setGameMode(String gameMode){
        switch (gameMode) {
            case "EASY":
                this.gameMode = GameConfiguration.GameMode.EASY;
                break;
            case "MEDIUM":
                this.gameMode = GameConfiguration.GameMode.MEDIUM;
                break;
            case "HARD":
                this.gameMode = GameConfiguration.GameMode.HARD;
                break;
            default:
                this.gameMode = GameConfiguration.GameMode.EASY;
                break;
        }
    }

    /**
     * Get the answer
     * @return the answer
     */
    public String getAnswer(){
        return this.answer;
    }

    /**
     * Search words according to the regex
     * @param regex the search regex
     * @return the result of searching
     */
    public String getSearchResult(String regex){
        return wordLists.getTargetWords(regex).toString();
    }
}
