import java.io.*;

public class GameManager {
    private static GameManager instance;
    private String answer = "APPLE";
    private WordlePanel wPanel;
    private int currentRow = 0;
    private int currentCol = -1;


    private GameManager(){

    }

    public void init(WordlePanel wPanel){
        this.wPanel = wPanel;
    }

    public static GameManager getInstance(){
        if(GameManager.instance == null){
            GameManager.instance = new GameManager();
        }

        return GameManager.instance;
    }

    public void handleInput(char c) throws IOException{
        if(c == '\n'){
            if(currentCol == 4){
                if(checkWord("")){
                    compareWithAnswer();
                    currentRow++;
                    currentCol = -1;
                }
                else{
                    //Todo: word is wrong.
                }
            }
            else{
                //Todo: please input 5 letters
            }
        }
        else if(c == '\b'){
            if(currentCol != -1){
                wPanel.getCharacterLabel(currentRow, currentCol).setLabelState(CharacterLabel.LabelState.EMPTY);
                currentCol--;
            }
            else{
                //Todo : you can't delete
            }
        }
        else{
            if(currentCol > 3){
                //You can't more
            }
            else{
                currentCol++;
                wPanel.getCharacterLabel(currentRow, currentCol).setText(Character.toString(c));
            }
        }
        System.out.println(currentRow + "," + currentCol);
    }

    private boolean checkWord(String word){

        return true;
    }

    private void compareWithAnswer(){
        CharacterLabel.LabelState state = CharacterLabel.LabelState.DO_NOT_CONTAIN;
        
        for(int i = 0; i < 5; i++){
            int index = answer.indexOf(wPanel.getCharacterLabel(currentRow, i).getText());
            if(index == -1){
                state = CharacterLabel.LabelState.DO_NOT_CONTAIN;
            }
            else{
                if(answer.charAt(i) == wPanel.getCharacterLabel(currentRow, i).getText().toCharArray()[0]){
                    state = CharacterLabel.LabelState.CONTAIN_AND_RIGHT_POSITION;
                }
                else{
                    state =  CharacterLabel.LabelState.CONTAIN_BUT_WRONG_POSITION;
                }
            }
            wPanel.getCharacterLabel(currentRow, i).setLabelState(state);
        }
    }
}
