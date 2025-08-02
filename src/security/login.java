
package security;

import Database.Database;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class login {
    static hash c = new hash();
    static Connection con = null;
    static Statement stmt = null;
    
    public static boolean checkPassword(String id,String password) {
        try {
            String sql = " SELECT passwordhash, passwordsalt  FROM staffandadmin   LEFT JOIN staffandadminhash    ON staffandadmin.id = staffandadminhash.id   WHERE staffandadminhash.id = ?;";
            PreparedStatement prep = con.prepareStatement(sql);
            int parsedId = Integer.parseInt(id);
            prep.setInt(1, parsedId);
            ResultSet rs = prep.executeQuery();
            String passwordHash = null;
            byte[] passwordSalt = null;
            while (rs.next()) {
                passwordHash = rs.getString("passwordhash");
                passwordSalt = rs.getBytes("passwordsalt");
            }
            if (!(passwordHash == null) || !(passwordSalt == null)) {
                 String hashedPassword = c.getPassword(password, passwordSalt);
                 if(hashedPassword.equalsIgnoreCase(passwordHash)) {
                     return true;
                 }
               
            }else {
              JOptionPane.showMessageDialog(null, "Invalid Account Information!");
                return false;   
            }
        } catch (Exception e) {

        }

     return false; 
    }
    
    
    
    public static boolean validateLogin(String id,String password,JPanel machinePanel,JComboBox machineComboxBox) throws ClassNotFoundException, SQLException {
        Database b = new Database();
        con = Database.getCon();
        stmt = con.createStatement();
           // TODO add your handling code here:
          
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

          
        String sql = " SELECT passwordhash, passwordsalt  FROM staffandadmin   LEFT JOIN staffandadminhash    ON staffandadmin.id = staffandadminhash.id   WHERE staffandadminhash.id = ?;";
        PreparedStatement prep = con.prepareStatement(sql);
        int parsedId = Integer.parseInt(id);
        prep.setInt(1, parsedId);
        ResultSet rs = prep.executeQuery();
        String passwordHash = null;
        byte[] passwordSalt = null;
        while(rs.next()) {
           passwordHash  = rs.getString("passwordhash");
           passwordSalt  = rs.getBytes("passwordsalt");
        }
        if(passwordHash == null || passwordSalt == null) {
           JOptionPane.showMessageDialog(null, "Invalid Account Information!");
        }
        else{
            String hashedPassword = c.getPassword(password, passwordSalt);
        try {
     
          if(hashedPassword.equals(passwordHash) && isLogged(id) != true) {
            CurrentUser user = new CurrentUser();
            
            String sqlSetCurrentUser = "select username,role from staffandadmin where id = ?;";  
            String sqlSetCurrentMachine = "select name,location from machines where name = ?;";  
            
            PreparedStatement stat = con.prepareCall(sqlSetCurrentUser);
            PreparedStatement stat1 = con.prepareStatement(sqlSetCurrentMachine);
            
            
            stat.setString(1, id);
            ResultSet result = stat.executeQuery();
            String roleString = "";
            while(result.next()) {
                
                String username = result.getString("username");
                String role = result.getString("role");
            user.setCurrentUserName(username);
            user.setCurrentRole(role);
           roleString = role;
           }
            String machineName = "";
            ResultSet rsss = null;
              String sqlComboBox = "Select name from machines where status = 'Offline';";
              String sqlCombox2 = "Select name from machines where status = 'Offline' and type = 'Cash Register';";
              if(roleString.equalsIgnoreCase("Admin") || roleString.equalsIgnoreCase("Staff")) {
                    rsss = stmt.executeQuery(sqlComboBox);
              }
              else if(roleString.equalsIgnoreCase("Employee")) {
                    rsss = stmt.executeQuery(sqlCombox2);
              }
                       

            while (rsss.next()) {
                String names = rsss.getString("name");
                model.addElement(names);
                 }
                 if (model.getSize() <= 0) {
                     JOptionPane.showMessageDialog(null, "No Available Machine!");

                 }
                 machineComboxBox.setModel(model);
                   do {
                  JOptionPane.showMessageDialog(null, machinePanel);
    machineName = machineComboxBox.getSelectedItem().toString();
              } while (machineName.isBlank());

             
                
                 
         

        
             

               

            stat1.setString(1, machineName);
            ResultSet rs1 = stat1.executeQuery();
           // figiure out the error username not found column ahh 
           
           user.setCurrentUserID(id); 

           while(rs1.next()) {
            user.setCurrentMachine(rs1.getString("name"));
            user.setCurrentLocation(rs1.getString("location"));
           }
            String statusSql = "update staffandadmin set status = 'Online', activeCount = 1 where id = ?";
            String setMachine = "update machines set status = 'Online' where name = ?";
          
         
           
            PreparedStatement updateStatus = con.prepareStatement(statusSql);
            PreparedStatement updateMachineStatus = con.prepareStatement(setMachine);
            
            updateMachineStatus.setString(1, machineName);
            
            updateStatus.setString(1, id);
            
            CurrentUser userLog= new CurrentUser(user.getCurrentUserName(),user.getCurrentUserID(),user.getCurrentMachine(),user.getCurrentLocation(),user.getCurrentRole());
            String filename = "log.txt";
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(userLog);
            out.close();
            file.close();
            
            updateMachineStatus.executeUpdate();
            updateStatus.executeUpdate();
            return true;
        }
        else {
            return false;

        }   
        }catch(Exception e) {
        }
       return false;
    }  
        return false;
        }
    public static boolean isLogged(String id) {
        Database b = new Database();
        try{
             con = b.getCon();
             String sqlCheckLogCount = "Select activeCount from staffandadmin where id = ?";
             PreparedStatement prep = con.prepareStatement(sqlCheckLogCount);
             prep.setString(1, id);
             ResultSet rs = prep.executeQuery();
             while(rs.next()) {
                 int count = rs.getInt("activeCount");
                 if(count == 1) {
                     return true;
                 }
             }
        }catch(Exception e) {
        }
      
        return false;
    }
      
}
