package security;

import Database.Database;
import java.io.Serializable;
import java.sql.*;
import javax.swing.JOptionPane;
public class CurrentUser implements Serializable {
    

    public CurrentUser(String currentUserName, String currentUserID, String currentMachine, String currentLocation) {
        this.currentUserName = currentUserName;
        this.currentUserID = currentUserID;
        this.currentMachine = currentMachine;
        this.currentLocation = currentLocation;
    }
    private String currentUserName;
    private String currentUserID;
    private String currentMachine;
    private String currentLocation;
    

    public CurrentUser() {
        
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public String getCurrentUserID() {
        return currentUserID;
    }

    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }

    public String getCurrentMachine() {
        return currentMachine;
    }

    public void setCurrentMachine(String currentMachine) {
        this.currentMachine = currentMachine;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    
   
      
    
    
    
}
