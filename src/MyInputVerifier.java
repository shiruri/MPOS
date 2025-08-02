
import java.math.BigDecimal;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyInputVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
          Integer.parseInt(text);
          return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Input!");
            return false;
        }
    }
}