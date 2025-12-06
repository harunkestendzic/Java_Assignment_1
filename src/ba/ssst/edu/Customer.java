package ba.ssst.edu;
import java.util.ArrayList;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
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
