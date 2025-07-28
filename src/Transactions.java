
public class Transactions {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSaletime() {
        return saletime;
    }

    public void setSaletime(String saletime) {
        this.saletime = saletime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private int id;
    private String product;
    private String user;
    private String device;
    private String saletime;
    private double total;
    private String status;
    
    public Transactions(int total,String status) {
        this.total = total;
        this.status = status;
    }
    public Transactions(int id, String product, String user, String device, String saletime, double total, String status) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.device = device;
        this.saletime = saletime;
        this.total = total;
        this.status = status;
    }
    
    void Transaction() {
    }
}
