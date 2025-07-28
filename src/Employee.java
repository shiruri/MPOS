


class Employee {

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    private int employeeID;
    private String username;
    private String status;
    private String role;

    public int getCurrentId() {    
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    // current
    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }
    private int currentId;
    private String currentUsername;
    private String currentStatus;
    private String currentRole;
    
    public Employee(int employeeID, String username, String status, String role) {
        this.employeeID = employeeID;
        this.username = username;
        this.status = status;
        this.role = role;
    }
    Employee() {
        
    }

    
}
