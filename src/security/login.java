
package security;

import Database.Database;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class login {
    static hash c = new hash();
    static Connection con = null;
    static Statement stmt = null;
    
    public static boolean validateLogin(String id,String password,JPanel parentPane,String machineName) throws ClassNotFoundException, SQLException {
        Database b = new Database();
        con = Database.getCon();
        stmt = con.createStatement();
        
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
           JOptionPane.showMessageDialog(parentPane, "Invalid Account Information!");
        }
        else{
            String hashedPassword = c.getPassword(password, passwordSalt);
        try {
          if(hashedPassword.equals(passwordHash)) {
            CurrentUser user = new CurrentUser();
            
            String sqlSetCurrentUser = "select username from staffandadmin where id = ?;";  
            String sqlSetCurrentMachine = "select name,location from machines where name = ?;";  
            
            PreparedStatement stat = con.prepareCall(sqlSetCurrentUser);
            PreparedStatement stat1 = con.prepareStatement(sqlSetCurrentMachine);
            
            
            stat.setString(1, id);
            ResultSet result = stat.executeQuery();
            stat1.setString(1, machineName);
            
            while(result.next()) {
                
                String username = result.getString("username");
            user.setCurrentUserName(username);
            
           }
            
           
            
            
            ResultSet rs1 = stat1.executeQuery();
           // figiure out the error username not found column ahh 
           
           user.setCurrentUserID(id); 

           while(rs1.next()) {
            user.setCurrentMachine(rs1.getString("name"));
            user.setCurrentLocation(rs1.getString("location"));
           }
            String statusSql = "update staffandadmin set status = 'Online' where id = ?";
            String setMachine = "update machines set status = 'Online' where name = ?";
          
         
           
            PreparedStatement updateStatus = con.prepareStatement(statusSql);
            PreparedStatement updateMachineStatus = con.prepareStatement(setMachine);
            
            updateMachineStatus.setString(1, machineName);
            
            updateStatus.setString(1, id);
            
            CurrentUser userLog= new CurrentUser(user.getCurrentUserName(),user.getCurrentUserID(),user.getCurrentMachine(),user.getCurrentLocation());
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
            JOptionPane.showMessageDialog(parentPane, "Error Loggin In: "+e);
            System.out.println("Error: "+e);
        }
       return false;
    }  
        return false;
        }
      
}
