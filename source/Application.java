/** 
 * The {@code Application} class is used to launch the game by its main method
 * @author Wang Xin
 * @version 1.0
*/
public class Application {
    /**
     * The entry point of the game
     * @param args
     */
    public static void main(String[] args) {
        GameManager.getInstance().start();
    }
}
