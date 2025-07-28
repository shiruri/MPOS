
import java.util.ArrayList;
import java.util.HashMap;


public class Product {
    Product(){
        
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getProductTax() {
        return productTax;
    }

    public void setProductTax(double productTax) {
        this.productTax = productTax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    private int productID;
    private String productName;
    private String productCategory;
    private double productTax;
    private int quantity;
    private double price;

    public Product(int productID, String productName, String productCategory, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.quantity = quantity;
        this.price = price;
    }
    public Product(String productName,int quantity,double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    
    
    static ArrayList<Double> totals;
    static ArrayList<Integer> quantityProds;
    
    public void resetQuantitProds() {
        this.quantityProds.clear();
    }
    public void resetTotals() {
        this.totals.clear();
    }
    public int getQuantityTotal() {

        if (!quantityProds.isEmpty()) {
            for (double item : quantityProds) {
                quantities += item;

            }
        }
        else {
             System.out.println("Empty Array");
        }
       
        return quantities;
    }
    public void setTotalQuantity(ArrayList<Integer> quantityProds) {
        this.quantityProds = quantityProds;
    }
    
    int quantities;
    double subTotal = 0;
    static HashMap<Integer,Integer> map = new HashMap<>();
    
    public HashMap<Integer,Integer> getMap() {
        return map;
    }
    
    public void addMap(int id,int quantity) {
        if(map.containsKey(id)) {
           int sum = map.get(id) + quantity;
           map.remove(id);
           map.put(id, sum);
        }
        else {
            map.put(id, quantity);
        }
    }
    public double getTotal() {

        if (!totals.isEmpty()) {
            for (double total : totals) {
                                System.out.println("Moew 2 "+ total);

                subTotal += total;

            }
            return subTotal;
        }
        return subTotal;

    }

    public void setTotal(ArrayList<Double> totals) {
        System.out.println("Moew 1");
        this.totals = totals;
        
    }
}
