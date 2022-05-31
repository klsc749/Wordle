import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * The {@code HelperInputTextField} class is used to get player's input at helper window and limit its length to 1
 * @author Wang Xin
 * @version 1.0
 */
public class HelperInputTextField extends JTextField{
  /**
   * The constructor set the alignment, background color and font
   */
    public HelperInputTextField(){
        super();
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setDocument(new LengthRestrictedDocument(1));
        this.setFont(new Font(GameConfiguration.GAME_FONT, Font.PLAIN, GameConfiguration.HELPER_WINDOW_HEIGHT / 20));
        this.setBackground(GameConfiguration.Key_BUTTON_NOT_PRESS_COLOR);
    }
}

/**
 * The {@code LengthRestrictDocument} class is used to limit the length of document 
 */
final class LengthRestrictedDocument extends PlainDocument {

    private final int limit;
  
    public LengthRestrictedDocument(int limit) {
      this.limit = limit;
    }
  
    @Override
    public void insertString(int offs, String str, AttributeSet a)
        throws BadLocationException {
      if (str == null)
        return;
  
      if ((getLength() + str.length()) <= limit) {
        char c = str.trim().toUpperCase().charAt(0);
        if(c >= 'A' && c <= 'Z')
            super.insertString(offs, Character.toString(c), a);
        else
            super.insertString(offs, "", a);
      }
    }
}
