
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class emailVerifier  extends InputVerifier{
    
    @Override
    public boolean verify(JComponent input) {
         String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
         String emailAddress = ((JTextField) input).getText();
         boolean check =Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
         if(check == true) {
             return true;
         }
         else {
             JOptionPane.showMessageDialog(null, "Error Email");
             return false;
         }
        }
    }
    

