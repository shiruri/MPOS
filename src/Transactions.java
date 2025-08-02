
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
    private String orderID;
  
    private int id;
    private String product;
    private String user;
    private String device;
    private String saletime;
    private double total;
    private String paymentMethod;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    private String status;
    
    public Transactions(int total,String status) {
        this.total = total;
        this.status = status;
    }
    public Transactions(String orderID,int id, String product, String user, String device, String saletime, double total,String paymentMethod, String status) {
        this.orderID = orderID;
        this.id = id;
        this.product = product;
        this.user = user;
        this.device = device;
        this.saletime = saletime;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
    
    void Transaction() {
    }
}
