package ba.ssst.edu;
import java.util.ArrayList;

public class Customer {
    public Customer(String name, String email, ArrayList<Purchase> purchases) {
        this.name = name;
        this.email = email;
        this.purchases = purchases;
    }
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }
    private String name;
    private String email;

    //uSE THIS
    public void AddPurchase(Purchase p) {purchases.add(p);}

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public String getName() {return this.name;}

    private ArrayList<Purchase> purchases = new ArrayList<>();


}
