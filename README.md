## Wordle User Guide

This is a game like [Wordle](https://en.wikipedia.org/wiki/Wordle). 

### Game Rules

* Guess a hidden target 5-letter word within 6 attempts.
  * You can input your answer by physical keyboard or virtual keyboard
* When you submit your answer, you'll get feed back
  * Green means the letter is contained in the word and is in that position
  * Yellow means the letter is contained in the word but not in that position
  * Grey means the letter is not contained in the word

### Helper

You can input the letter to find words that include these letters so that you can guess the answer.

<img src="images\HelperWindow.png" alt="image-20220601191200031" style="zoom:33%;" />



### Compile And Run
To play this game, you must compile the program first. Then run `Application` java class.

Open the command line in the project directory and enter the following command:

```shell
cd ./source
javac Application.java
java Application
```

Or just run the script named [`Wordle.bat`](Wordle.bat).

javadoc -locale en_US Application.java CharacterLabel.java GameConfiguration.java GameManager.java HelperInputTextField.java HelperWindow.java KeyboardPanel.java KeyButton.java KeyEventFactory.java ResultWindow.java StartWindow.java WordlePanel.java WordleWindow.java WordLists.java